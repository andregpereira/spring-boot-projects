package com.github.andregpereira.springbootprojects.kafka;

import com.github.andregpereira.springbootprojects.kafka.app.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Bean
    public Supplier<Message<String>> supplyOut() {
        return () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("Enviando String");
            return MessageBuilder.withPayload("Teste").build();
        };
    }

    @Bean
    public Function<Message<String>, Message<String>> processString() {
        return m -> {
            String payload = m.getPayload();
            log.info("Processando String: {}", payload);
            return MessageBuilder.withPayload(payload.toUpperCase()).build();
        };
    }

    @Bean
    public Function<Message<MessageDto>, Message<MessageDto>> processDto() {
        return m -> {
            MessageDto payload = m.getPayload();
            log.info("Processando DTO: {}", payload);
            return MessageBuilder.withPayload(new MessageDto(payload.message().toUpperCase())).build();
        };
    }

    @Bean
    public Consumer<Message<String>> consumeString() {
        return m -> log.info("String recebida: {}", m.getPayload());
    }

    @Bean
    public Consumer<Message<MessageDto>> consumeDto() {
        return m -> log.info("DTO recebida: {}", m.getPayload());
    }

}
