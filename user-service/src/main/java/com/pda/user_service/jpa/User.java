package com.pda.user_service.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private int id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String birthdate;
    private String gender;
    private String investmentType;
    private Integer investmentTestScore;
    private String mydata;
    private Boolean portfolio;

    public User(String email, String name, String password, String phone, String birthdate, String gender) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public void setInvestmentTestScore(int investmentTestScore) {
        this.investmentTestScore = investmentTestScore;
    }

}
