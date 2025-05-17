package com.chat.demo.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chatrooms")
public class Chatroom {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> participantIds; // Usa gli ID invece degli oggetti User completi
}