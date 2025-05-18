package com.grepp.nbe562team04.model.goal.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalResponseDto {

    private Long goalId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDone;
    private LocalDate createdAt;
    private String status;
    private int progress;
    //진행 률 추가 필요
}
