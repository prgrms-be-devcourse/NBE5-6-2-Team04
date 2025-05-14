package com.grepp.nbe562team04.model.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TodoResponseDto {

    private Long todoId;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDone;
    private Long goalId;   // 프론트에서 필요할 수 있음
}