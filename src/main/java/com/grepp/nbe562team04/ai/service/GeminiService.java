package com.grepp.nbe562team04.ai.service;

import com.grepp.nbe562team04.ai.dto.ChatMessageDto;
import com.grepp.nbe562team04.ai.dto.GeminiRequestDto;
import com.grepp.nbe562team04.ai.dto.GeminiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api-key}")
    private String geminiApiKey;

    // WebClient 초기화
    private final WebClient webClient = WebClient.create();

    // 전체 대화 히스토리를 기반으로 Gemini 응답 받기
    public String getGeminiReply(List<ChatMessageDto> history) {
        // 1. ChatMessageDto 리스트 → GeminiRequestDto.Content 리스트로 변환
        List<GeminiRequestDto.Content> contents = history.stream()
            .map(chat -> new GeminiRequestDto.Content(
                chat.getRole(),  // "user" or "ai"
                List.of(new GeminiRequestDto.Part(chat.getMessage()))
            ))
            .collect(Collectors.toList());

        // 2. 요청 객체 생성
        GeminiRequestDto request = new GeminiRequestDto(contents);

        // 3. API 호출
        return webClient.post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("generativelanguage.googleapis.com")
                .path("/v1/models/gemini-1.5-flash:generateContent")
                .queryParam("key", geminiApiKey)
                .build())
            .bodyValue(request)
            .retrieve()
            // 예외 처리
            .onStatus(
                status -> status.isError(),
                response -> response.bodyToMono(String.class)
                    .flatMap(errorBody ->
                        Mono.error(new RuntimeException("Gemini API 호출 실패: " + errorBody))
                    )
            )
            .bodyToMono(GeminiResponseDto.class)
            .map(res -> res.getCandidates().get(0).getContent().getParts().get(0).getText())
            .block();
    }
}
