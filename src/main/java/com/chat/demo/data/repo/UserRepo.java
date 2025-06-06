package com.chat.demo.data.repo;

import com.chat.demo.data.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean existsByUsername(String username);
    
}