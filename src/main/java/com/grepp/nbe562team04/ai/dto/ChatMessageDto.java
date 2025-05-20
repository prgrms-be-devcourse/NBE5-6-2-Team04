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
    private Role role;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate timestamp;
}

