package com.avismatutina.kafka.boundedcontext.client.services;

import com.avismatutina.type.MessageMeta;
import com.avismatutina.type.MessageRequest;

public interface TopicProducerClient {

     MessageMeta produceMessage(MessageRequest msg);
}
