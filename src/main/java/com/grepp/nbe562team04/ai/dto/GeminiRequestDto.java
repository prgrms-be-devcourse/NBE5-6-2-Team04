package com.grepp.nbe562team04.ai.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeminiRequestDto {

    private List<Content> contents;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content {
        private String role;
        private List<Part> parts;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Part {
        private String text;
    }
}