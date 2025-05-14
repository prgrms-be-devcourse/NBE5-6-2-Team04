package com.grepp.nbe562team04.model.goal.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalRequestDto {

    private Long companyId; // 어떤 기업의 목표인지 지정
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDone;
}