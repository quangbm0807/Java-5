package com.quangbui.controller;

import com.quangbui.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    /**
     * Xử lý khi một người dùng tham gia vào chat.
     * 
     * @param chatMessage Tin nhắn chat nhận từ client
     * @param headerAccessor Đối tượng để truy cập vào các thuộc tính của phiên (session)
     * @return Tin nhắn sau khi được xử lý, gửi lại đến tất cả các client đăng ký
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Thêm tên người dùng vào WebSocket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
