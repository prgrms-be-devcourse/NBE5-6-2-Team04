package com.grepp.nbe562team04.ai.service;

import com.grepp.nbe562team04.ai.dto.GeminiRequestDto;
import com.grepp.nbe562team04.ai.dto.GeminiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api-key}")
    private String geminiApiKey;


    // WebClient 초기화
    private final WebClient webClient = WebClient.create();

    // 사용자 메시지에 대한 Gemini 응답 받기
    public String getGeminiReply(String userMessage) {

        // 요청 객체 생성
        GeminiRequestDto request = new GeminiRequestDto(
            List.of(
                // 제미나이 제한조건 하나씩 늘려가며 주면됨
                new GeminiRequestDto.Content(
                    "user",
                    List.of(
                        new GeminiRequestDto.Part("역할을 부여할게 너는 나의 mental케어 해주는 친구이며 나의 취업을 도와주는 관리사야"),
                        new GeminiRequestDto.Part("너의 성격은 ENFP와 같고 반말해"),
                        new GeminiRequestDto.Part("너의 대답은 50글자를 초과하지 않게 제한할게"),
                        new GeminiRequestDto.Part("너의 질문은 내가  '긍정', '부정' 두 가지로 답변 가능한 질문만 해줘 그리고 긍정/부정 이건 말하지마"),
                        new GeminiRequestDto.Part("만약 질문을 한다면 한 가지만 할 것"),
                        new GeminiRequestDto.Part("내가 무엇을 해야 취업에 도움이 될지 조언을 해줘"),
                        new GeminiRequestDto.Part("질문만 하지말고 내가 더 성장 할 수 있는 방식도 추천해줘"),
                        new GeminiRequestDto.Part("했던 질문은 하지마")


                    )
                ),
                // 사용자의 입력 but! 우린 "긍정" "부정" 버튼으로 처리
                new GeminiRequestDto.Content(
                    "user",
                    List.of(
                    new GeminiRequestDto.Part(userMessage)//userMessage에 입력값 담김
                    )
                )
            )
        );

        // API 호출
        return webClient.post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .host("generativelanguage.googleapis.com")
                .path("/v1/models/gemini-1.5-flash:generateContent")
                .queryParam("key", geminiApiKey)
                .build())
            .bodyValue(request)
            .retrieve()
            // 예외처리
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