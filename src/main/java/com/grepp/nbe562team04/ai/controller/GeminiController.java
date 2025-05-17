package com.grepp.nbe562team04.ai.controller;

import com.grepp.nbe562team04.ai.dto.ChatMessageDto;
import com.grepp.nbe562team04.ai.service.ChatHistoryService;
import com.grepp.nbe562team04.ai.service.GeminiService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
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
        ChatMessageDto userChat = new ChatMessageDto("user",userMessage, LocalDate.now());
        chatHistoryService.saveMessage(session,userChat); //세션에 메세지 저장

        // 세션에 저장된 대화내용 history(전체) 꺼냄
        List<ChatMessageDto> history = (List<ChatMessageDto>) session.getAttribute("chatHistory");


        // 유저의 응답만 보냄 즉 대화 맥락은 파악못함
        // 유저 메세지를 받고 그에 해당하는 대답이 담김

        String reply = geminiService.getGeminiReply(history);


        ChatMessageDto aiChat = new ChatMessageDto("model", reply, LocalDate.now());
        chatHistoryService.saveMessage(session, aiChat);


        // 응답결과를 HTMl에 전달
        model.addAttribute("userMessage", userMessage); // (긍정,부정)전달
        model.addAttribute("reply", reply); // LLM의 답변 ex) 자소서 준비하세요
        return "todo";
    }
}