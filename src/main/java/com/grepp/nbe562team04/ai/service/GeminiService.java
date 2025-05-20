package com.grepp.nbe562team04.ai.service;

import com.grepp.nbe562team04.ai.dto.ChatMessageDto;
import com.grepp.nbe562team04.ai.dto.GeminiRequestDto;
import com.grepp.nbe562team04.ai.dto.GeminiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeminiService {

    @Value("${gemini.api-key}")
    private String geminiApiKey;

    private final WebClient webClient = WebClient.create();

    public String getGeminiReply(List<ChatMessageDto> history) {

        // ✅ 1. 프롬프트 엔지니어링: 역할, 말투, 목적 지시
        GeminiRequestDto.Content systemPrompt = new GeminiRequestDto.Content(
            "user",
            List.of(new GeminiRequestDto.Part(
                "너는 사용자의 멘탈을 케어하고 취업 준비를 도와주는 AI 친구야. " +
                    "말투는 다정하고 따뜻하게 해줘. 너무 딱딱하거나 차갑게 굴지 마. " +
                    "사용자가 힘들어하면 위로하고, 취업 준비에 도움이 되는 실질적인 조언을 해줘. 😊"
            ))
        );

        // ✅ 2. 대화 히스토리를 Gemini 형식으로 변환
        List<GeminiRequestDto.Content> messageContents = history.stream()
            .map(msg -> new GeminiRequestDto.Content(
                msg.getRole().name().toLowerCase(), // "user" or "model"
                List.of(new GeminiRequestDto.Part(msg.getMessage()))
            ))
            .collect(Collectors.toList());

        // ✅ 3. 요청 메시지 조합
        List<GeminiRequestDto.Content> contents = new ArrayList<>();
        contents.add(systemPrompt); // 프롬프트 삽입
        contents.addAll(messageContents); // 대화 히스토리

        GeminiRequestDto request = new GeminiRequestDto(contents);

        // ✅ 4. Gemini API 호출
        GeminiResponseDto response = webClient.post()
            .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + geminiApiKey)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(GeminiResponseDto.class)
            .onErrorResume(e -> {
                e.printStackTrace();
                return Mono.just(new GeminiResponseDto());
            })
            .block();

        // ✅ 5. 응답 파싱
        try {
            assert response != null;
            return response.getCandidates()
                .getFirst()
                .getContent()
                .getParts()
                .getFirst()
                .getText();
        } catch (Exception e) {
            e.printStackTrace(); // 디버깅에 도움됨
            return "앗! AI 응답을 처리하는 데 문제가 발생했어요 😢 다시 시도해볼래요?";
        }
    }
}
