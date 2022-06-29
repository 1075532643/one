package com.ag.xxl_jobT;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyTestXXL_JOB {
    private static Logger logger = LoggerFactory.getLogger(MyTestXXL_JOB.class);


    @XxlJob("demoJobHandlerTest")
    public void testXXl_job(){
        System.out.println("xxl_job");
        String jobParam = XxlJobHelper.getJobParam();
        log.debug("接收调度中心参数：{}",jobParam);
        log.info("");
        XxlJobHelper.log("第一次测试，记录～～～");
        XxlJobHelper.handleSuccess();
    }
}
