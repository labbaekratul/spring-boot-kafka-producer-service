package com.deliveryboy.controller;

import com.deliveryboy.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final KafkaService kafkaService;

    @Autowired
    public LocationController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateLocation() {
        for (int i = 1; i <= 10; i++) {
            double latitude = Math.round(Math.random() * 100);
            double longitude = Math.round(Math.random() * 100);
            String location = "(" + latitude + ", " + longitude + ")";
            this.kafkaService.updateLocation(location);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Locations updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
