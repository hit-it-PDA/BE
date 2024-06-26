package com.pda.asset_service.service;


import com.pda.asset_service.dto.*;
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
public class PensionServiceImpl implements PensionService{

    private final PensionRepository pensionRepository;
    private final MydataInfoRepository mydataInfoRepository;
    private final MydataServiceClient mydataServiceClient;

    @Override
    public Pension convertToEntity(PensionResponseDto pensionResponseDto) {
        return Pension.builder()
                .companyName(pensionResponseDto.getCompanyName())
                .pensionId(new PensionId(pensionResponseDto.getAccountNo(), pensionResponseDto.getPensionName()))
                .pensionType(pensionResponseDto.getPensionType())
                .userId(pensionResponseDto.getUserId())
                .interestRate(pensionResponseDto.getInterestRate())
                .evaluationAmount(pensionResponseDto.getEvaluationAmount())
                .expirationDate(pensionResponseDto.getExpirationDate())
                .retirementPensionClaimed(pensionResponseDto.getRetirementPensionClaimed())
                .build();
    }

    @Override
    public PensionDto convertToDto(Pension pension) {
        return PensionDto.builder()
                .companyName(pension.getCompanyName())
                .pensionName(pension.getPensionId().getPensionName())
                .pensionType(pension.getPensionType())
                .userId(pension.getUserId())
                .interestRate(pension.getInterestRate())
                .evaluationAmount(pension.getEvaluationAmount())
                .expirationDate(pension.getExpirationDate())
                .accountNo(pension.getPensionId().getAccountNo())
                .retirementPensionClaimed(pension.getRetirementPensionClaimed())
                .build();
    }


    @Override
    public List<MydataInfoDto> linkMyDataAccount(int userId, List<String> pensions) {
        List<MydataInfoDto> pensionLinkInfo = new ArrayList<>();

        // 연금 정보 처리

        for (String pensionName : pensions) {
            if (!pensionName.isEmpty()) {
                log.info("userPension = {}", pensionName);
                Optional<List<PensionResponseDto>> pensionResponse = mydataServiceClient.getPensionsByUserIdAndCompanyName(userId, pensionName);
                if (pensionResponse.isPresent()) {
                    log.info("==========================================================================");
                    for (PensionResponseDto pensionResponseDto : pensionResponse.get()) {
                        log.info("pension convert Entity = {}", pensionResponseDto);
                        Pension pension = convertToEntity(pensionResponseDto);
                        pensionRepository.save(pension);
                        mydataInfoRepository.save(MydataInfo.builder()
                                .assetType("pensions")
                                .userId(pension.getUserId())
                                .companyName(pension.getCompanyName())
                                .accountType(pension.getPensionType())
                                .accountNo(pension.getPensionId().getAccountNo())
                                .build());

                        MydataInfo savedInfo = mydataInfoRepository.findPensionByUserIdAndAssetTypeAndCompanyNameAndAccountNo(
                                pension.getUserId(),
                                "pensions",
                                pension.getCompanyName(),
                                pension.getPensionId().getAccountNo()
                        );
                        MydataInfoDto mydataInfoDto = MydataInfoDto.builder()
                                .assetType(savedInfo.getAssetType())
                                .userId(savedInfo.getUserId())
                                .companyName(savedInfo.getCompanyName())
                                .accountType(savedInfo.getAccountType())
                                .accountNo(savedInfo.getAccountNo())
                                .build();

                        pensionLinkInfo.add(mydataInfoDto);
                    }
                }
            }else{
                log.info("요청된 연금 계좌 없음");
            }
        }
        return pensionLinkInfo;
    }

    @Override
    public List<PensionDto> getPensions(int userId) {
        List<Pension> pensions = pensionRepository.findByUserId(userId).orElse(null);

        List<PensionDto> pensionDtos = new ArrayList<>();
        if (pensions != null) {
            for (Pension pension : pensions) {
                PensionDto pensionDto = convertToDto(pension);
                log.info("find pension account = {}", pensionDto);
                pensionDtos.add(pensionDto);
            }
        }
        return pensionDtos;
    }

    @Override
    public List<RetirementAccountDto> getUnclaimedRetirementAccounts(int userId) {
        List<RetirementAccountResponseDto> unclaimedRetirementAccounts = mydataServiceClient.getUnclaimedRetirementAccounts(userId).orElse(null);

        List<RetirementAccountDto> unclaimedRetirementAccountDtos = new ArrayList<>();
        if (unclaimedRetirementAccounts != null) {
            for (RetirementAccountResponseDto unclaimedRetirementAccount : unclaimedRetirementAccounts) {
                RetirementAccountDto pensionDto = RetirementAccountDto.builder()
                        .accountNo(unclaimedRetirementAccount.getAccountNo())
                        .companyName(unclaimedRetirementAccount.getCompanyName())
                        .pensionName(unclaimedRetirementAccount.getPensionName())
                        .pensionType(unclaimedRetirementAccount.getPensionType())
                        .interestRate(unclaimedRetirementAccount.getInterestRate())
                        .evaluationAmount(unclaimedRetirementAccount.getEvaluationAmount())
                        .expirationDate(unclaimedRetirementAccount.getExpirationDate())
                        .userId(userId)
                        .balance(unclaimedRetirementAccount.getBalance())
                        .retirementPensionClaimed(unclaimedRetirementAccount.getRetirementPensionClaimed())
                        .build();
                log.info("find pension account = {}", pensionDto);
                unclaimedRetirementAccountDtos.add(pensionDto);
            }
        }
        return unclaimedRetirementAccountDtos;
    }

}
