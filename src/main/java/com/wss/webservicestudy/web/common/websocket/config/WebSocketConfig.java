package com.wss.webservicestudy.web.common.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .withSockJS();  // 버전 낮은 브라우저에서도 적용
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트에서 보낸 메세지를 받을 prefix 설정
        registry.setApplicationDestinationPrefixes("/send");
        
        // 해당 주소를 구독하고 있는 Client들에게 메세지 전달
        registry.enableSimpleBroker("/room");
    }
}
