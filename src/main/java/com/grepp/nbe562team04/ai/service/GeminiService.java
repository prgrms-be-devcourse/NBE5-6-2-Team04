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
                          new GeminiRequestDto.Part("너가 대답 할 땐 답장 맨앞에 항상 몇번째 답장인지 숫자를 사용한 순번을 꼭 붙일 것 ")
//                        new GeminiRequestDto.Part("다음 조건을 반드시 지킬 것"),
//                        new GeminiRequestDto.Part("1.너의 역할은 취업가이드이자 동료"),
//                        new GeminiRequestDto.Part("2.한번 했던 질문은 하지말 것"),
//                        new GeminiRequestDto.Part("3. 대답은 최대 50글자(공백 미 포함)"),
//                        new GeminiRequestDto.Part("4.너의 질문은 내가  '긍정', '부정' 두 가지로 답변 가능한 질문만 할 것"),
//                        new GeminiRequestDto.Part("5.만약 질문을 한다면 한 가지만 할 것"),
//                        new GeminiRequestDto.Part("6.내가 무엇을 해야 취업에 도움이 될지 조언을 해줄것"),
//                        new GeminiRequestDto.Part("7.질문만 하지말고 내가 더 성장 할 수 있는 방식도 추천해줘"),
//                        new GeminiRequestDto.Part("8.했던 질문은 하지마")
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
// 2025-05-17(토) History 전송기능 구현



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