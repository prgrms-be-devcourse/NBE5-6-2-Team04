package com.grepp.nbe562team04.ai.controller;

import com.grepp.nbe562team04.ai.dto.ChatMessageDto;
import com.grepp.nbe562team04.ai.service.ChatHistoryService;
import com.grepp.nbe562team04.ai.service.GeminiService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;
    private final ChatHistoryService chatHistoryService;

    //(사용자 입력 -> 응답 -> 저장 -> 출력)
    @PostMapping("/ai/feedback")
    public String getGeminiReply(@RequestParam("userMessage") String userMessage,
        HttpSession session,
        Model model) {

        // 사용자 입력 (긍정 / 부정) DTO 저장
        ChatMessageDto userChat = new ChatMessageDto("user",userMessage, LocalDateTime.now());
        chatHistoryService.saveMessage(session,userChat); //세션에 메세지 저장

        // Gemini로 질문을 받음 (입력)
        String reply = geminiService.getGeminiReply(userMessage);


        ChatMessageDto aiChat = new ChatMessageDto("ai", reply, LocalDateTime.now());
        chatHistoryService.saveMessage(session, aiChat);


        // 응답결과를 HTMl에 전달
        model.addAttribute("userMessage", userMessage); // (긍정,부정)전달
        model.addAttribute("reply", reply); // LLM의 답변 ex) 자소서 준비하세요
        return "todo"; //redirect?
    }
}