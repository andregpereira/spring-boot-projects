package com.github.andregpereira.springbootprojects.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;
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
            log.info("Enviando mensagem");
            return MessageBuilder.withPayload("Teste").build();
        };
    }

    @Bean
    public Consumer<Message<String>> consumeIn() {
        return m -> log.info("Mensagem recebida {}", m.getPayload());
    }

}
