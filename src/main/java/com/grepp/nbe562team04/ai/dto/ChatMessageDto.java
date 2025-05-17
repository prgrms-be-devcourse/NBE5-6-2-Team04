package com.grepp.nbe562team04.ai.dto;

import com.grepp.nbe562team04.ai.dto.GeminiResponseDto.Candidate;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//AI와 나눈 대화 내용 저장
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {


    private String role;
    private String message;
    private LocalDate timestamp;


}
