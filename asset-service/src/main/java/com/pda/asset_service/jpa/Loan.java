package com.pda.asset_service.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "loans")
public class Loan {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "loan_amount")
    private Integer loanAmount;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "total_payments")
    private Integer totalPayments;

    @Id
    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "user_id")
    private int userId;
}
