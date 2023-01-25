package com.avismatutina.kafka.api.impl;

import com.avismatutina.kafka.boundedcontext.client.services.TopicProducerClient;
import com.avismatutina.type.MessageMeta;
import com.avismatutina.type.MessageRequest;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/api/messages")
public class KafkaProducerImpl {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerImpl.class);

    private final TopicProducerClient topicProducerClient;

    public KafkaProducerImpl(TopicProducerClient topicProducerClient){
        this.topicProducerClient = topicProducerClient;
    }

    @GetMapping
    public List<String> getMessages() {
        LOG.info("Hi, from messages GET");
        return new ArrayList<>();
    }

    @PostMapping (produces = "application/json")
    public ResponseEntity<MessageMeta> sendMsg(@RequestBody MessageRequest msg)  {

        LOG.info("/api/messages", msg);

        this.topicProducerClient.produceMessage(msg);

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
                        LOG.info("Received new metadata. \n" +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partition: " + recordMetadata.partition() + "\n" +
                                "Offset: " + recordMetadata.offset() + "\n" +
                                "Timestamp: " + recordMetadata.timestamp());

                        metaBuilder.setTopic(recordMetadata.topic());
                        metaBuilder.setPartition(recordMetadata.partition());
                        metaBuilder.setOffset(recordMetadata.offset());
                        metaBuilder.setTimestamp(recordMetadata.timestamp());
                    } else {
                        LOG.error("Error while producing", e);
                    }
                }
            });

        // flush data - synchronous
        producer.flush();
        // flush and close producer
        producer.close();
        return new ResponseEntity<>(metaBuilder.build(), HttpStatus.OK);
    }
}
