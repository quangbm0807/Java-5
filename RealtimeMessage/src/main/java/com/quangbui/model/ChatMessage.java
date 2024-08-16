package com.quangbui.model;

public class ChatMessage {

    private String content; // Nội dung tin nhắn
    private String sender;  // Người gửi tin nhắn
    private MessageType type; // Loại tin nhắn

    public enum MessageType {
        CHAT,   // Tin nhắn thông thường
        JOIN,   // Tin nhắn thông báo người dùng tham gia
        LEAVE   // Tin nhắn thông báo người dùng rời đi
    }

    // Constructor không tham số
    public ChatMessage() {
    }

    // Constructor có tham số
    public ChatMessage(String content, String sender, MessageType type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
    }

    // Getter và Setter
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
