package com.pda.mydata_service.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pensions")
public class Pension {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "pension_name")
    private String pensionName;

    @Column(name = "pension_type")
    private String pensionType;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "evaluation_amount")
    private String evaluationAmount;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "retirement_pension_claimed")
    private String retirementPensionClaimed;

    @Id
    @Column(name = "account_no")
    private String accountNo;
}
