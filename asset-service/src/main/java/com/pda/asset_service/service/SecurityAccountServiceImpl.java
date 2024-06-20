package com.pda.asset_service.service;

import com.pda.asset_service.dto.MydataInfoDto;
import com.pda.asset_service.dto.SecurityAccountDto;
import com.pda.asset_service.dto.SecurityAccountResponseDto;
import com.pda.asset_service.feign.MydataServiceClient;
import com.pda.asset_service.jpa.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SecurityAccountServiceImpl implements SecurityAccountService{

    private final SecurityAccountRepository securityAccountRepository;
    private final AssetUserRepository assetUserRepository;
    private final MydataInfoRepository mydataInfoRepository;
    private final MydataServiceClient mydataServiceClient;
    @Override
    public SecurityAccount convertToEntity(SecurityAccountResponseDto securityAccountResponseDto) {
        AssetUser assetUser = assetUserRepository.findById(securityAccountResponseDto.getUserId()).orElseThrow();
        return SecurityAccount.builder()
                .accountNo(securityAccountResponseDto.getAccountNo())
                .securityName(securityAccountResponseDto.getSecurityName())
                .accountType(securityAccountResponseDto.getAccountType())
                .balance(securityAccountResponseDto.getBalance())
                .createdAt(securityAccountResponseDto.getCreatedAt())
                .assetUser(assetUser)
                .build();
    }

    @Override
    public SecurityAccountDto convertToDto(SecurityAccount securityAccount) {
        return SecurityAccountDto.builder()
                .accountNo(securityAccount.getAccountNo())
                .securityName(securityAccount.getSecurityName())
                .accountType(securityAccount.getAccountType())
                .balance(securityAccount.getBalance())
                .createdAt(securityAccount.getCreatedAt())
                .userId(securityAccount.getAssetUser().getId())
                .build();
    }

    @Override
    public List<MydataInfoDto> linkMyDataAccount(int userId, List<String> securityAccounts) {
        List<MydataInfoDto> securityAccountsLinkInfo = new ArrayList<>();

        // 증권 계좌 정보 처리

        for (String securityName : securityAccounts) {
            if( !securityName.isEmpty() ) {
                log.info("userAccount = {}", securityName);
                Optional<List<SecurityAccountResponseDto>> securityAccountsResponse = mydataServiceClient.getSecurityAccountsByUserIdAndSecurityName(userId, securityName);
//            log.info("securityAccounts Response From Mydata-service = {}", securityAccountsResponse);
                if (securityAccountsResponse.isPresent()) {
                    for (SecurityAccountResponseDto securityAccountResponseDto : securityAccountsResponse.get()) {
                        log.info("security account convert Entity = {}", securityAccountResponseDto);
                        SecurityAccount securityAccount = convertToEntity(securityAccountResponseDto);
                        securityAccountRepository.save(securityAccount);

                        mydataInfoRepository.save(MydataInfo.builder()
                                .assetType("security_accounts")
                                .userId(securityAccount.getAssetUser().getId())
                                .companyName(securityAccount.getSecurityName())
                                .accountType(securityAccount.getAccountType())
                                .accountNo(securityAccount.getAccountNo())
                                .build());

                        MydataInfo savedInfo = mydataInfoRepository.findSecurityByUserIdAndAssetTypeAndCompanyNameAndAccountNo(
                                securityAccount.getAssetUser().getId(),
                                "security_accounts",
                                securityAccount.getSecurityName(),
                                securityAccount.getAccountNo()
                        );
                        MydataInfoDto mydataInfoDto = MydataInfoDto.builder()
                                .assetType(savedInfo.getAssetType())
                                .userId(savedInfo.getUserId())
                                .companyName(savedInfo.getCompanyName())
                                .accountType(savedInfo.getAccountType())
                                .accountNo(securityAccount.getAccountNo())
                                .build();

                        securityAccountsLinkInfo.add(mydataInfoDto);
                    }
                }
            }else{
                log.info("요청된 증권 계좌 없음");
            }
        }
        return securityAccountsLinkInfo;
    }

    @Override
    public List<SecurityAccountDto> getSecurityAccounts(int userId) {

        List<SecurityAccount> securityAccounts = securityAccountRepository.findByAssetUserId(userId).orElse(null);

        List<SecurityAccountDto> securityAccountDtos = new ArrayList<>();
        if (securityAccounts != null) {
            for (SecurityAccount securityAccount : securityAccounts) {
                SecurityAccountDto securityAccountDto = convertToDto(securityAccount);
                log.info("find security account = {}", securityAccountDto);
                securityAccountDtos.add(securityAccountDto);
            }
        }
        return securityAccountDtos;
    }

    @Override
    public Integer getSecurityAccountsBalance(int userId) {
        Integer securityAccountsTotalBalance = 0;
        List<SecurityAccount> securityAccounts = securityAccountRepository.findByAssetUserId(userId).orElse(null);

        if(securityAccounts != null){
            for (SecurityAccount securityAccount : securityAccounts){
                securityAccountsTotalBalance += securityAccount.getBalance();
            }
        }
        return securityAccountsTotalBalance;
    }


}