package com.ag.consumer;


import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class Customer {
    public static void main(String[] args) {
       //配置

        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092,hadoop103:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        //必须配置消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");
        //创建消费者
        KafkaConsumer<String, String> con = new KafkaConsumer<String, String>(properties);

        ArrayList<String> topic = new ArrayList<>();
        //定义主题
        topic.add("first");
        con.subscribe(topic);
        //消费数据
        while (true){
            ConsumerRecords<String, String> consumerRecords = con.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
        }



    }
}
