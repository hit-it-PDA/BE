package com.pda.asset_service.jpa;

import com.pda.asset_service.dto.MydataInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MydataInfoRepository extends JpaRepository<MydataInfo, Integer> {

    MydataInfo findByAccountNo(String accountNo);
    MydataInfo findBankAccountByUserIdAndAssetTypeAndCompanyNameAndAccountNo(int id, String bankAccounts, String bankName, String accountNo);

    MydataInfo findLoanByUserIdAndAssetTypeAndCompanyNameAndAccountNo(int id, String loans, String companyName, String accountNo);

    MydataInfo findPensionByUserIdAndAssetTypeAndCompanyNameAndAccountNo(int id, String pensions, String companyName, String accountNo);

    
    List<MydataInfo> findByUserId(int userId);

    MydataInfo findCardByUserIdAndAssetTypeAndCompanyNameAndAccountNo(int id, String cards, String companyName, String cardNo);

    MydataInfo findSecurityByUserIdAndAssetTypeAndCompanyNameAndAccountNo(int id, String securityAccounts, String securityName, String accountNo);
}
