package com.pda.utils.api_utils;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CustomStringUtilsTest {

    @Test
    void 생년월일_성별_2000년대생_여자() {
        String residentNumber = "040201-4";
        assertThat(CustomStringUtils.getBirthDate( residentNumber)).isEqualTo("20040201");
        assertThat(CustomStringUtils.getGender( residentNumber)).isEqualTo("female");
    }

    @Test
    void 생년월일_성별_2000년대생_남자() {
        String residentNumber = "040201-3";
        assertThat(CustomStringUtils.getBirthDate( residentNumber)).isEqualTo("20040201");
        assertThat(CustomStringUtils.getGender( "040201-3")).isEqualTo("male");
    }

    @Test
    void 생년월일_성별_1900년대생_여자() {
        String residentNumber = "910203-2";
        assertThat(CustomStringUtils.getBirthDate( residentNumber)).isEqualTo("19910203");
        assertThat(CustomStringUtils.getGender( residentNumber)).isEqualTo("female");
    }

    @Test
    void 생년월일_성별_1900년대생_남자() {
        String residentNumber = "910203-1";
        assertThat(CustomStringUtils.getBirthDate( residentNumber)).isEqualTo("19910203");
        assertThat(CustomStringUtils.getGender( residentNumber)).isEqualTo("male");
    }

    @Test
    void 전화번호_포맷_8자리() {
        assertThat(CustomStringUtils.formatPhone( "+82 10-1234-5678")).isEqualTo("010-1234-5678");
    }

    @Test
    void 토큰해석() {
        String bearerToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzE4NzY3MDc2LCJleHAiOjE3MTg3Njg4NzZ9.9izDaaeTfD1C1i7e9gXvuchR6cG-9tAR3SmufoymdTc";
        assertThat(CustomStringUtils.getToken(bearerToken)).isEqualTo("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNzE4NzY3MDc2LCJleHAiOjE3MTg3Njg4NzZ9.9izDaaeTfD1C1i7e9gXvuchR6cG-9tAR3SmufoymdTc");
    }

    @Test
    void 화폐포맷_3자리() {
        assertThat(CustomStringUtils.formatMoney(483)).isEqualTo("483");
    }

    @Test
    void 화폐포맷_4자리() {
        assertThat(CustomStringUtils.formatMoney(1483)).isEqualTo("1,483");
    }

    @Test
    void 생년월일로_만나이계산() {
        assertThat(CustomStringUtils.calculateAge("20000311")).isEqualTo(24);
    }

    @Test
    void 생년월일로_만나이계산2() {
        assertThat(CustomStringUtils.calculateAge("19940621")).isEqualTo(30);
    }
}