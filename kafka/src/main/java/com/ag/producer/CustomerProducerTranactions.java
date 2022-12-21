package com.ag.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomerProducerTranactions {
    public final String firstTopic = "first";

    public static void main(String[] args) {
        //配置
        Properties properties = new Properties();
        //连接集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092");

        //指定对应的key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tranactional_id_01");

        //kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        //开启事物
        kafkaProducer.initTransactions();
        kafkaProducer.beginTransaction();

        //
        try {
            for (int i = 0; i < 5; i++) {
                kafkaProducer.send(new ProducerRecord<>("first", "nanjing" + i), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e == null) {
                            System.out.println("主题：" + recordMetadata.topic() + "分区" + recordMetadata.partition());
                        }
                    }
                });
            }
            kafkaProducer.commitTransaction();
            //模拟失败，验证事物
            int i = 1 / 0;
        } catch (Exception e) {
            //事物回滚
            kafkaProducer.abortTransaction();
        } finally {
            kafkaProducer.close();
        }


    }
}
