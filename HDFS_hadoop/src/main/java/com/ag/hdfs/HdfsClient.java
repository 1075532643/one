package com.ag.hdfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * 客户端代码
 */
@Slf4j
public class HdfsClient {

    private FileSystem fs;

    @Before
    public void init() throws Exception {
        URI uri = new URI("hdfs://hadoop102:8020");
        Configuration configuration = new Configuration();
        String user = "root";
        //获取客户端对象
        fs = FileSystem.get(uri, configuration, user);

    }

    @After
    public void close() throws IOException {
          //关闭资源
        fs.close();
    }

    @Test
    public void testMkdir() throws IOException {
        fs.mkdirs(new Path("/nanjing"));
    }

    @Test
    public void download() throws IOException {
        // 1.源文件是否删除 2.hdfs路径 3.下载到的目标地址路径  4.
        fs.copyToLocalFile(false,new Path("hdfs://hadoop102/xiyou"),new Path("/Users/chenyongguang/Desktop"),false);
    }

    @Test
    public void delete() throws IOException {
        //参数2：是否递归删除
        fs.delete(new Path("/wcinput2"),true);
    }


}
