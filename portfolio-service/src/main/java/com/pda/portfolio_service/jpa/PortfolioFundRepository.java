package com.pda.portfolio_service.jpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioFundRepository extends JpaRepository<PortfolioFund, PortfolioFundId>{
    List<PortfolioFund> findByIdPortfolioId(Integer portfolioId);
}
