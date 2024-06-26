package com.pda.mydata_service.jpa;

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
@Table(name = "security_accounts")
public class SecurityAccount {

    @Id
    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "security_name")
    private String securityName;

    @Column(name = "account_type")
    private String accountType;

    private Integer balance;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private MydataUser mydataUser;
}
