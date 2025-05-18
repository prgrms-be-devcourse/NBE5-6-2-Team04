package com.grepp.nbe562team04.ai.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//AI와 나눈 대화 내용 저장
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDto {


    private String role; // 기존 타입을 String -> Role 로 변경
    private String message;
    private LocalDate timestamp;


}
