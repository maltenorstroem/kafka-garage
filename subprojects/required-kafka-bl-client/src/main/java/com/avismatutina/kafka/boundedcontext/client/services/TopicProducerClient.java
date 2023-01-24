package com.avismatutina.kafka.boundedcontext.client.services;

public interface TopicProducerClient {

    void produceMessage(String msg);
}
