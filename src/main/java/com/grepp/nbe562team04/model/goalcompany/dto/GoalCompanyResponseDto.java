package com.grepp.nbe562team04.model.goalcompany.dto;

import com.grepp.nbe562team04.model.goalcompany.code.GoalStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalCompanyResponseDto {

    private String companyName;
    private String content;
    private String status;
    private LocalDate endDate;

    //추후 추가 기능 개발 시 추가 필요


}
