package com.employeemanagementsystem.empman.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import static com.employeemanagementsystem.empman.Constants.AppConstants.Location_Topic_Name;

public class KafkaConfig {

    @Bean
    public NewTopic newTopic () {

        return TopicBuilder
                .name(Location_Topic_Name)
                .build() ;

    }
}
