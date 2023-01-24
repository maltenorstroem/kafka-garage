package com.avismatutina.kafka.boundedcontext.client.services.config;

import com.avismatutina.kafka.boundedcontext.client.services.TopicProducerClient;
import com.avismatutina.kafka.boundedcontext.client.services.impl.TopicProducerClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicProducerClientConfiguration {

    @Bean
    public TopicProducerClient topicProducerClient() {
        return new TopicProducerClientImpl();
    }
}
