package com.ag.mapreduce2;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 7步
 * <p>
 * 1。获取job
 * 2。设置jar路径
 * 3。关联mapper和reducer
 * 4。设置map输出的kv类型
 * 5。最终输出的kv类型
 * 6。设置输入路径和输出路径
 * 7。提交job
 */
public class wordCountDrive {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration config = new Configuration();
        //1
        Job job = Job.getInstance(config);
        //2
        job.setJarByClass(wordCountDrive.class);
        //3
        job.setMapperClass(wordCountMap.class);
        job.setReducerClass(wordCountReduce.class);
        //4
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //5
        job.setOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        //6
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}

