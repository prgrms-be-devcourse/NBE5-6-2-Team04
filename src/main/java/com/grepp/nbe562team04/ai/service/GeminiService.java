package com.grepp.nbe562team04.ai.service;

import com.grepp.nbe562team04.ai.dto.ChatMessageDto;
import com.grepp.nbe562team04.ai.dto.GeminiRequestDto;
import com.grepp.nbe562team04.ai.dto.GeminiResponseDto;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
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

    // 전체 대화 히스토리를 기반으로 Gemini 응답 받기
    public String getGeminiReply(List<ChatMessageDto> history) {
        List<GeminiRequestDto.Content> contents = new ArrayList<>();

        GeminiRequestDto.Content prompt = new GeminiRequestDto.Content(
            "user",
            List.of(
                    new GeminiRequestDto.Part("다음 조건 사항을 반드시 지킬 것"),
                    new GeminiRequestDto.Part("너에게 역할을 부여할게 너는 취업 조력자 이며 친구야."),
                    new GeminiRequestDto.Part("답변은 간결하고 실용적이며 50글자를 넘기지 말 것."),
                    new GeminiRequestDto.Part("질문은 '예' 또는 '아니오'로만 대답가능한 질문을 할 것 (단 너의 대답엔 예/아니오는 표시하지 말것 )."),
                    new GeminiRequestDto.Part("격려와 응원을 반드시 해주며 너의 성격은 ENFJ일것."),
                    new GeminiRequestDto.Part("했던 질문은 하지 않을 것"),
                    new GeminiRequestDto.Part("답변 할 때 몇번 째 답변인지 앞에 번호를 달 것"),
                    new GeminiRequestDto.Part("취업을 준비를 잘하고 있는지 물어보고 관심을 가져주고 물어볼 것"),
                    new GeminiRequestDto.Part("대화를 계속 이어나가려고 할 것")
                )

        );
        contents.add(prompt);


        // 1. ChatMessageDto 리스트 → GeminiRequestDto.Content 리스트로 변환
        contents.addAll(
            history.stream()
                .map(chat -> new GeminiRequestDto.Content(
                    chat.getRole(),
                    List.of(new GeminiRequestDto.Part(chat.getMessage()))
                ))
                .toList()
        );

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
                HttpStatusCode::isError,
                response -> response.bodyToMono(String.class)
                    .flatMap(errorBody ->
                        Mono.error(new RuntimeException("Gemini API 호출 실패: " + errorBody))
                    )
            )
            .bodyToMono(GeminiResponseDto.class)
            .map(res -> res.getCandidates().getFirst().getContent().getParts().getFirst().getText())
            .block();
    }
}

