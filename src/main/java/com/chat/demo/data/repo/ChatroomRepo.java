package com.chat.demo.data.repo;

import com.chat.demo.data.entity.Chatroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ChatroomRepo extends MongoRepository<Chatroom, String> {
    Optional<Chatroom> findByName(String name); // Restituisce Optional
}