package com.github.andregpereira.springbootprojects.kafka.app.resource;

import com.github.andregpereira.springbootprojects.kafka.app.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaResource {

    private final StreamOperations operations;

    @PostMapping()
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDto dto) {
        log.info("Enviando DTO");
        operations.send("supplyDto-out-0", MessageBuilder.withPayload(dto).build());
        return ResponseEntity.ok().build();
    }

}
