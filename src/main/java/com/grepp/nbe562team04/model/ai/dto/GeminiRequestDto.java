package com.grepp.nbe562team04.model.ai.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeminiRequestDto {
    private List<Content> contents;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Content {
        private String role;
        private List<Part> parts;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Part {
        private String text;
    }
}
