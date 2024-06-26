package com.pda.mydata_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SecurityStockRepository extends JpaRepository<SecurityStock, Integer> {
    Optional<List<SecurityStock>> findByAccountNo(String accountNo);
}


