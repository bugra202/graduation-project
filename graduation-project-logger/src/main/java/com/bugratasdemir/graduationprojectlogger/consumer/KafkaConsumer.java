package com.bugratasdemir.graduationprojectlogger.consumer;

import com.bugratasdemir.graduationprojectlogger.service.KafkaConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class KafkaConsumer {

    private final KafkaConsumerService service;
    private final String taskTitle = "Catch User Errors";
    private final String topicName = "USER_LOG_START_COMMAND";
    private final String topicNameDLT = "USER_LOG_START_COMMAND_DLT";
    @KafkaListener(topics = topicName, groupId = "log-consumer-group")
    public void consume(String message){
        log.debug("{0} -> A pending job was found", taskTitle);
        if ("error".contains(message)){
            log.info("Consume failed");
            throw new RuntimeException();
        }
        service.consume(message);
    }

    @KafkaListener(topics = topicNameDLT,groupId = "log-consumer-group")
    public void consumeDLT(String message){
        log.error("Received message from DLT" + message);
    }
}
