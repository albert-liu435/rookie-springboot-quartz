package com.rookie.bigdata.learn9;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * @ClassName SimpleJob1
 * @Description SimpleJob1
 * @Author
 * @Date 2020/1/19 11:43
 * @Version 1.0
 */
public class SimpleJob1 implements Job {

    public SimpleJob1() {
    }

    /**
     * 执行业务的真正逻辑
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();

        System.out.println("SimpleJob1 says: " + jobKey + "executing at " + new Date());

    }
}
