package com.grepp.nbe562team04.goalcompany.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalCompanyRequestDto {
    private Long userId;
    private String companyName;
    private String content;
}
