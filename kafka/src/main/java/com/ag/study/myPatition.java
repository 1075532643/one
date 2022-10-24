package com.ag.study;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.awt.geom.PathIterator;
import java.util.Map;
//自定义分区器实现自定义功能
public class myPatition implements Partitioner {


    @Override
    public int partition(String s, Object o, byte[] bytes, Object value, byte[] bytes1, Cluster cluster) {
       //获取数据
        String msg = value.toString();
        int partition ;
        if(msg.contains("ag")){
            partition=0;
        }else {
            partition=1;
        }

        return partition;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
