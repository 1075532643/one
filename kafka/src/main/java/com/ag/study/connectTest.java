package com.ag.study;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class connectTest {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.16.82.130:2181");
        //设置key和value的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        //关联自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.ag.study.myPatition");
        //创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        //缓冲区大小
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        //批次大小
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        //linger.ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1    );
        //压缩
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");

        //发送数据（最普通的一种）
       // kafkaProducer.send(new ProducerRecord<>("主题", "数据"));
        //带分区--2是分区的key
        //kafkaProducer.send(new ProducerRecord<>("主题","2" ,"数据"), new Callback() );

            //带回调的
        kafkaProducer.send(new ProducerRecord<>("主题", "数据"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    e.printStackTrace();
                }
            }
        }
        );


        //关闭资源
        kafkaProducer.close();
    }
}
