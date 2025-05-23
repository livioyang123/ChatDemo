package com.chat.demo.webSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.chat.demo.data.entity.Message;
import com.chat.demo.data.service.MessageService;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;

    public ChatController(SimpMessagingTemplate messagingTemplate, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload Message message) {

        messageService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/chat-room/" + message.getChatRoomId(), message);
    }

}
