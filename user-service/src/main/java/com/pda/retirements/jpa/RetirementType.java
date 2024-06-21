package com.pda.retirements.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RetirementType {
    LEVEL_4("노후준비충분 공적자산형", "가장 이상적인 유형으로 노후필요생활비를 충당할 수 있는 자산 규모를 갖고 있고 공적자산의 비중이 높은 유형"),
    LEVEL_3("노후준비부족 공적자산형", "노후필요생활비를 충당하기 부족한 자산 규모를 갖고 있고 공적 자산의 비중이 높은 유형으로 노후생활비에 비해서 보유한 은퇴자산의 규모가 적고, 개인연금, 금융자산 등의 선택자산에 관심이 낮음"),
    LEVEL_2("노후준비충분 사적자산형", "노후필요생활비를 충당할 수 있는 자산 규모를 갖고 있지만 사적자산 비중이 높은 유형"),
    LEVEL_1("노후준비부족 사적자산형", "노후필요생활비를 충당하기 부족한 자산 규모를 갖고 있고 사적자산 비중이 높은 유형");

    private final String name;
    private final String description;
}