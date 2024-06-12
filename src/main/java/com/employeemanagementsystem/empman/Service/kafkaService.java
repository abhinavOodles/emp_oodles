package com.employeemanagementsystem.empman.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.employeemanagementsystem.empman.Constants.AppConstants.Location_Topic_Name;

@Slf4j
@Service
public class kafkaService  {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public boolean updateLocation(String location) {

        kafkaTemplate.send(Location_Topic_Name, location);
        log.info("message produced");
        return true;
    }

}
