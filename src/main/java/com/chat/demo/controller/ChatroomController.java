package com.chat.demo.controller;

import com.chat.demo.data.entity.Chatroom;
import com.chat.demo.data.service.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/chatrooms")
@RequiredArgsConstructor
public class ChatroomController {
    private final ChatroomService chatroomService;

    @PostMapping
    public ResponseEntity<Chatroom> createChatRoom(@RequestBody Chatroom chatroom) {
        return ResponseEntity.ok(chatroomService.createRoom(chatroom));
    }

    @GetMapping
    public List<Chatroom> getAllChatRooms() {
        return chatroomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chatroom> getChatRoomById(@PathVariable String id) {
        return chatroomService.getRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Chatroom> getChatRoomByName(@PathVariable String name) {
        return chatroomService.getRoomByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable String id) {
        chatroomService.deleteRoomById(id);
        return ResponseEntity.noContent().build();
    }

    
}