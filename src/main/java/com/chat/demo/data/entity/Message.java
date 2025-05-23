package com.chat.demo.data.entity;
import java.time.LocalDateTime; 
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatRoomId;
    private String senderId;
    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();
    private MessageType type; 

    public Message(Message message) {
        this.id = message.id;
        this.chatRoomId = message.chatRoomId;
        this.content = message.content;
        this.timestamp = message.timestamp;
        this.type = message.type;
    }
}
