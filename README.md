# KAFKA LAB

## Start the zookeeper service
$ bin/zookeeper-server-start.sh config/zookeeper.properties

## Start the broker service
$ bin/kafka-server-start.sh config/server.properties

## Create a topic to store events
$ bin/kafka-topics.sh --create --topic kafka_garage --bootstrap-server localhost:9092

## Write some events into the topic
$ bin/kafka-console-producer.sh --topic kafka_garage --bootstrap-server localhost:9092

## Read the events
$ bin/kafka-console-consumer.sh --topic kafka_garage --from-beginning --bootstrap-server localhost:9092

https://kafka.apache.org/quickstart

