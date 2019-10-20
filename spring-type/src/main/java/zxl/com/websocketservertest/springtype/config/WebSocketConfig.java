package zxl.com.websocketservertest.springtype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import zxl.com.websocketservertest.springtype.handler.ChatMessageHandler;
import zxl.com.websocketservertest.springtype.interceptor.ChatHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatMessageHandler(),"/webSocketServer").addInterceptors(new ChatHandshakeInterceptor());
        registry.addHandler(chatMessageHandler(), "/sockjs/webSocketServer").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();
    }

    @Bean
    public TextWebSocketHandler chatMessageHandler(){
        return new ChatMessageHandler();
    }

}