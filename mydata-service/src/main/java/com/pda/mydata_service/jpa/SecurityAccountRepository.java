package com.pda.mydata_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SecurityAccountRepository extends JpaRepository<SecurityAccount, String> {

    Optional<List<SecurityAccount>> findByMydataUser_Id(int userId);

    Optional<List<SecurityAccount>> findByMydataUser_IdAndSecurityName(int userId, String securityAccountName);

    Optional<SecurityAccount> findByAccountNo(String accountNo);
}
