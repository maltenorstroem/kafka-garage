package com.avismatutina.kafka.boundedcontext.client.services.impl;

import com.avismatutina.kafka.boundedcontext.client.services.TopicProducerClient;
import com.avismatutina.type.MessageMeta;
import com.avismatutina.type.MessageRequest;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class TopicProducerClientImpl implements TopicProducerClient {

    @Override
    public MessageMeta produceMessage(MessageRequest msg) {

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a producer record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>(msg.getTopic(), msg.getMessage());

        // send data - asynchronous
        MessageMeta.Builder metaBuilder = MessageMeta.newBuilder();

        producer.send(producerRecord, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    // executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {
                        // the record was successfully sent
                        metaBuilder.setTopic(recordMetadata.topic());
                        metaBuilder.setPartition(recordMetadata.partition());
                        metaBuilder.setOffset(recordMetadata.offset());
                        metaBuilder.setTimestamp(recordMetadata.timestamp());
                    } else {
                        // LOG.error("Error while producing", e);
                    }
                }
            });

        // flush data - synchronous
        producer.flush();
        // flush and close producer
        producer.close();
        return metaBuilder.build();
    }
}