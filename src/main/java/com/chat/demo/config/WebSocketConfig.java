package com.chat.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.mongodb.lang.NonNull;

@Configuration
@EnableWebSocketMessageBroker  // Abilita supporto WebSocket con STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Definisce l'endpoint WebSocket che i client useranno per connettersi
    @Override
    public void registerStompEndpoints(@NonNull StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat")    // Endpoint WS, es. ws://localhost:8080/ws-chat
                .setAllowedOriginPatterns("http://localhost:3000")
                .withSockJS();             // Abilita SockJS per fallback su browser non compatibili
    }

    // Configura il message broker
    @Override
    public void configureMessageBroker(@NonNull MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue"); // Broker interno che inoltra messaggi ai client iscritti
        registry.setApplicationDestinationPrefixes("/app"); // Prefisso per i messaggi in ingresso dal client
        registry.setUserDestinationPrefix("/user");
    }
}
