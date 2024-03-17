package com.bugratasdemir.graduationprojectlogger.service;

import com.bugratasdemir.graduationprojectlogger.entity.Log;
import com.bugratasdemir.graduationprojectlogger.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class KafkaConsumerService {

    private final LogRepository logRepository;
    private final String topicName = "USER_LOG_START_COMMAND";
    public void consume(String message){

        log.info("Consume log started");

        Log log = new Log();
        log.setDate(LocalDateTime.now());
        log.setMessage(message);
        log.setDescription("Kafka error logs");

        logRepository.save(log);
    }
}
