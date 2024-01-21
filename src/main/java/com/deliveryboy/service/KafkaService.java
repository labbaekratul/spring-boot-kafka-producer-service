package com.deliveryboy.service;

import com.deliveryboy.config.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void updateLocation(String location) {
        try {
            this.kafkaTemplate.send(AppConstants.LOCATION_TOPIC_NAME, location);
            logger.info("Location update message sent successfully.");
        } catch (Exception e) {
            logger.error("Error sending location update message: {}", e.getMessage(), e);
        }
    }
}
