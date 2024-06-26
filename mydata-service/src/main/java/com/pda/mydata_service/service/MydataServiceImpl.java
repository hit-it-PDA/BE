package com.pda.mydata_service.service;//package com.pda.mydata_service.legacy.service;


import com.pda.mydata_service.dto.*;
import com.pda.mydata_service.jpa.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class MydataServiceImpl implements MydataService{


    private final MydataUserRepository mydataUserRepository;

    private final BankAccountRepository bankAccountRepository;
    private final CardRepository cardRepository;
    private final SecurityAccountRepository securityAccountRepository;
    private final LoanRepository loanRepository;
    private final PensionRepository pensionRepository;
    private final SecurityTransactionRepository securityTransactionRepository;
    private final SecurityStockRepository securityStockRepository;

    @Override
    public BankAccount convertToEntity(BankAccountDto bankAccountDto) {
        MydataUser user = mydataUserRepository.findById(bankAccountDto.getUserId()).orElseThrow();
        return BankAccount.builder()
                .accountNo(bankAccountDto.getAccountNo())
                .bankName(bankAccountDto.getBankName())
                .accountType(bankAccountDto.getAccountType())
                .name(bankAccountDto.getName())
                .balance(bankAccountDto.getBalance())
                .createdAt(bankAccountDto.getCreatedAt())
                .mydataUser(user)
                .build();
    }

    @Override
    public BankAccountDto convertToDto(BankAccount bankAccount) {
        return BankAccountDto.builder()
                .accountNo(bankAccount.getAccountNo())
                .bankName(bankAccount.getBankName())
                .accountType(bankAccount.getAccountType())
                .name(bankAccount.getName())
                .balance(bankAccount.getBalance())
                .createdAt(bankAccount.getCreatedAt())
                .userId(bankAccount.getMydataUser().getId())
                .build();
    }

    @Override
    public Optional<List<BankAccountDto>> getAllBankAccounts(int userId) {
        Optional<List<BankAccount>> bankAccounts = bankAccountRepository.findByMydataUser_Id(userId);
        if (bankAccounts.isPresent()) {
            List<BankAccountDto> bankAccountDtos = bankAccounts.get().stream()
                    .map(this::convertToDto)
                    .toList();
            return Optional.of(bankAccountDtos);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<BankAccountDto>> getBankAccountsByUserIdAndBankName(int userId, String bankName) {

        log.info("mydata service");

        Optional<List<BankAccount>> bankAccounts = bankAccountRepository.findByMydataUser_IdAndBankName(userId, bankName);
        log.info("bank accounts = {}", bankAccounts.get());
        if (bankAccounts.isPresent()) {
            List<BankAccountDto> bankAccountDtos = bankAccounts.get().stream()
                    .map(this::convertToDto)
                    .toList();
            log.info("bank accounts bankAccountDtos = {}", bankAccountDtos);
            return Optional.of(bankAccountDtos);
        } else {
            return Optional.empty();
        }
    }
    @Override
    public Optional<List<LoanDto>> getLoansByUserIdAndCompanyName(int userId, String companyName) {
        Optional<List<Loan>> loans = loanRepository.findByMydataUser_IdAndCompanyName(userId, companyName);
        if (loans.isPresent()) {
            List<LoanDto> loanDtos = loans.get().stream()
                    .map(this::convertToDto)
                    .toList();
            return Optional.of(loanDtos);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<CardDto>> getCardsByUserIdAndCompanyName(int userId, String companyName) {
        Optional<List<Card>> cards = cardRepository.findByMydataUser_IdAndCompanyName(userId, companyName);
        if (cards.isPresent()) {
            List<CardDto> cardDtos = cards.get().stream()
                    .map(this::convertToDto)
                    .toList();
            return Optional.of(cardDtos);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<PensionDto>> getPensionsByUserIdAndCompanyName(int userId, String companyName) {
        Optional<List<Pension>> pensions = pensionRepository.findByUserIdAndCompanyName(userId, companyName);
        if (pensions.isPresent()) {
            List<PensionDto> pensionDtos = pensions.get().stream()
                    .map(this::convertToDto)
                    .toList();
            return Optional.of(pensionDtos);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<SecurityAccountDto>> getSecurityAccountsByUserIdAndSecurityName(int userId, String securityName) {
        Optional<List<SecurityAccount>> securityAccounts = securityAccountRepository.findByMydataUser_IdAndSecurityName(userId, securityName);
        if (securityAccounts.isPresent()) {
            List<SecurityAccountDto> securityAccountDtos = securityAccounts.get().stream()
                    .map(this::convertToDto)
                    .toList();
            return Optional.of(securityAccountDtos);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<RetirementAccountDto>> getUnclaimedRetirementAccounts(int userId) {
        Optional<List<Pension>> unclaimedRetirementAccounts = pensionRepository.findByUserIdAndRetirementPensionClaimed(userId, "0");;
        log.info("FROM REPO = {}", unclaimedRetirementAccounts.get());

        if (unclaimedRetirementAccounts.isPresent()) {
            List<RetirementAccountDto> pensionDtoList = new ArrayList<>();

            for (Pension pension : unclaimedRetirementAccounts.get()) {
                Optional<BankAccount> bankAccount = bankAccountRepository.findByAccountNo(pension.getAccountNo());
                Integer balance = 0;

                if (bankAccount.isPresent()) {
                    balance = bankAccount.get().getBalance();
                } else {
                    Optional<SecurityAccount> securityAccount = securityAccountRepository.findByAccountNo(pension.getAccountNo());
                    if (securityAccount.isPresent()) {
                        balance = securityAccount.get().getBalance();
                    }
                }

                RetirementAccountDto dto = RetirementAccountDto.builder()
                        .accountNo(pension.getAccountNo())
                        .pensionName(pension.getPensionName())
                        .companyName(pension.getCompanyName())
                        .pensionType(pension.getPensionType())
                        .expirationDate(pension.getExpirationDate())
                        .interestRate(pension.getInterestRate())
                        .evaluationAmount(pension.getEvaluationAmount())
                        .retirementPensionClaimed(pension.getRetirementPensionClaimed())
                        .userId(pension.getUserId())
                        .balance(balance).build();
                pensionDtoList.add(dto);
            }

            log.info("미청구 퇴직연금 리스트 = {}", pensionDtoList);
            return Optional.of(pensionDtoList);
        } else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<List<SecurityTransactionDto>> getSecurityTransactions(String accountNo) {
        Optional<List<SecurityTransaction>> securityTransactions = securityTransactionRepository.findBySecurityAccount_AccountNo(accountNo);
        if (securityTransactions.isPresent()) {
            List<SecurityTransactionDto> securityTransactionDtos = securityTransactions.get().stream()
                    .map(this::convertToDto)
                    .toList();
            return Optional.of(securityTransactionDtos);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<SecurityStockDto>> getSecurityStocks(String accountNo) {
        Optional<List<SecurityStock>> securityStocks = securityStockRepository.findByAccountNo(accountNo);
        if (securityStocks.isPresent()) {
            log.info("MydataServiceImpl = {}", securityStocks.get());
            List<SecurityStockDto> securityStockDtos = securityStocks.get().stream()
                    .map(this::convertToDto)
                    .toList();
            return Optional.of(securityStockDtos);
        } else {
            return Optional.empty();
        }
    }






    private LoanDto convertToDto(Loan loan) {
        return LoanDto.builder()
                .companyName(loan.getCompanyName())
                .loanType(loan.getLoanType())
                .loanAmount(loan.getLoanAmount())
                .interestRate(loan.getInterestRate())
                .totalPayments(loan.getTotalPayments())
                .accountNo(loan.getAccountNo())
                .userId(loan.getMydataUser().getId())
                .build();
    }

    private CardDto convertToDto(Card card) {
        return CardDto.builder()
                .cardNo(card.getCardNo())
                .companyName(card.getCompanyName())
                .cardName(card.getCardName())
                .cardType(card.getCardType())
                .createdAt(card.getCreatedAt())
                .expiredAt(card.getExpiredAt())
                .accountNo(card.getAccountNo())
                .userId(card.getMydataUser().getId())
                .build();
    }

    private PensionDto convertToDto(Pension pension) {
        return PensionDto.builder()
                .companyName(pension.getCompanyName())
                .pensionName(pension.getPensionName())
                .pensionType(pension.getPensionType())
                .interestRate(pension.getInterestRate())
                .evaluationAmount(pension.getEvaluationAmount())
                .expirationDate(pension.getExpirationDate())
                .accountNo(pension.getAccountNo())
                .userId(pension.getUserId())
                .retirementPensionClaimed(pension.getRetirementPensionClaimed())
                .build();
    }

    private SecurityAccountDto convertToDto(SecurityAccount securityAccount) {
        return SecurityAccountDto.builder()
                .accountNo(securityAccount.getAccountNo())
                .securityName(securityAccount.getSecurityName())
                .accountType(securityAccount.getAccountType())
                .balance(securityAccount.getBalance())
                .createdAt(securityAccount.getCreatedAt())
                .userId(securityAccount.getMydataUser().getId())
                .build();
    }

    private SecurityStockDto convertToDto(SecurityStock securityStock) {
        return SecurityStockDto.builder()
                .id(securityStock.getId())
                .accountNo(securityStock.getAccountNo())
                .stockCode(securityStock.getStockCode())
                .build();
    }

    private SecurityTransactionDto convertToDto(SecurityTransaction securityTransaction) {
        return SecurityTransactionDto.builder()
                .id(securityTransaction.getId())
                .txDatetime(securityTransaction.getTxDatetime())
                .txType(securityTransaction.getTxType())
                .txAmount(securityTransaction.getTxAmount())
                .balAfterTx(securityTransaction.getBalAfterTx())
                .txQty(securityTransaction.getTxQty())
                .accountNo(securityTransaction.getSecurityAccount().getAccountNo())
                .stockCode(securityTransaction.getStockCode())
                .build();
    }




}
