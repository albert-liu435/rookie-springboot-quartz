package com.rookie.bigdata.learn2;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * @ClassName SimpleJob
 * @Description SimpleJob
 * @Author
 * @Date 2020/1/17 15:51
 * @Version 1.0
 */
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("jobkey: " + jobKey + "executing at" + new Date());

    }
}
