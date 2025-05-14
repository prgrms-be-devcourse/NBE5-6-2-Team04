package com.grepp.nbe562team04.model.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoRequestDto {

    private Long goalId;           // 어떤 목표(goal)에 속하는 투두인지
    private String content;        // 투두 내용
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean isDone;        // 기본값 false로 서버에서 처리 가능
}