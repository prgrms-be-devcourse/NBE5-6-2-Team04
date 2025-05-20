package com.grepp.nbe562team04.ai.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {

    private String message;
    private Role role; // ✅ 문자열 말고 Enum으로!

    @JsonFormat(pattern = "yyyy-MM-dd") // ✅ 날짜 포맷 지정
    private LocalDate timestamp;
}


/**
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
**/
