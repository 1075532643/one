package com.ag.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


/**
 * ketin longWriteable
 * valuein text
 * keyout text
 * valueout intWriteable
 */
public class wordCountMap extends Mapper<LongWritable, Text,Text, IntWritable> {

    private Text outK = new Text();

    private IntWritable outV = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        //1.获取一行
        String line = value.toString();
        //切割"afadf" -->"a f a d f"
        String[] words = line.split(" ");
        //循环写出
        for (String word: words){
            //封装
            outK.set(word);
            //写出
            context.write(outK,outV);
        }
    }
}
