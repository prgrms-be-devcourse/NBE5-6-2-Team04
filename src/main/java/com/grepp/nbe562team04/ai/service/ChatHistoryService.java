package com.grepp.nbe562team04.ai.service;


import com.grepp.nbe562team04.ai.dto.ChatMessageDto;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatHistoryService {

    public void saveMessage(HttpSession session, ChatMessageDto message) {

        List<ChatMessageDto> history = (List<ChatMessageDto>) session.getAttribute("chatHistory");

        if (history == null) {
            history = new ArrayList<>();
        }

        history.add(message);

        session.setAttribute("chatHistory", history);

        for (int i = 0; i < history.size(); i++) {
            ChatMessageDto msg = history.get(i);
            System.out.println("[" + msg.getRole() + "] " + msg.getMessage());
        }
    }
}
