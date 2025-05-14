package com.grepp.nbe562team04.model.goal.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalResponseDto {

    private Long goalId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDone;
    private LocalDateTime createdAt;
}
