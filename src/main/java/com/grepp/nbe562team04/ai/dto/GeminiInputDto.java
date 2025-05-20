package com.grepp.nbe562team04.ai.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 프론트엔드에서 사용자 입력을 받을 때 사용하는 전용 DTO입니다.
 * GeminiRequestDto와는 다르게 간단한 prompt 필드만 포함되어 있습니다.
 */
@Getter
@Setter
public class GeminiInputDto {
    private String prompt;
}
