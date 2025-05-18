package com.grepp.nbe562team04.ai.controller;

import com.grepp.nbe562team04.ai.dto.ChatMessageDto;
import com.grepp.nbe562team04.ai.dto.Role;
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

        // 사용자가 입력한 메세지
        List<ChatMessageDto> history = chatHistoryService.saveMessage(session, // chatHistory를 통해 session에 저장
            ChatMessageDto.builder()
                .message(userMessage) //유저가 보낸 메시지
                .role(String.valueOf(Role.USER)) //역할 USER
                .timestamp(LocalDate.now()) // 현재날짜
                .build()
        );

        // 세션에 저장된 대화내용 history(전체) 꺼냄
//        List<ChatMessageDto> history = (List<ChatMessageDto>) session.getAttribute("chatHistory");

        String reply;
        if (history == null || history.isEmpty()) {
            // 대화 맥락이 없는 상태 → 초기 질문을 만들어줄 수도 있음
            reply = "안녕? 반가워 무슨 고민을 가지고 있니?";
        } else {
            reply = geminiService.getGeminiReply(history);
        }

        // 유저의 응답만 보냄 즉 대화 맥락은 파악못함
        // 유저 메세지를 받고 그에 해당하는 대답이 담김

//        세션에 저장된 제미나이의 답변 = reply
//        String reply = geminiService.getGeminiReply(history);

        chatHistoryService.saveMessage(session,
            ChatMessageDto.builder()
                .message(reply)
                .role(String.valueOf(Role.MODEL))
                .timestamp(LocalDate.now())
                .build()
        );
//
//
//        ChatMessageDto aiChat = new ChatMessageDto("model", reply, LocalDate.now());
//        chatHistoryService.saveMessage(session, aiChat);


        // 응답결과를 HTMl에 전달
        model.addAttribute("userMessage", userMessage); // (긍정,부정)전달
        model.addAttribute("reply", reply); // LLM의 답변 ex) 자소서 준비하세요
        return "todo";
    }
}