package com.ag.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.rocketmq.common.filter.impl.Op;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class CustomerPartition {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop103:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        //必须配置消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        //创建消费者
        KafkaConsumer<String, String> con = new KafkaConsumer<String, String>(properties);
        ArrayList<TopicPartition> topicPartitions = new ArrayList<>();
        topicPartitions.add(new TopicPartition("first", 0));
        con.assign(topicPartitions);
        while (true) {
            ConsumerRecords<String, String> consumerRecords = con.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.toString());

            }
        }


        //订阅相应分区

    }
}
