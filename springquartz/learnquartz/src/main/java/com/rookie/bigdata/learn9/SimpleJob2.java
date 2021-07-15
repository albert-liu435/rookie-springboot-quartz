package com.rookie.bigdata.learn9;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.Date;

/**
 * @ClassName SimpleJob2
 * @Description SimpleJob2
 * @Author
 * @Date 2020/1/19 11:49
 * @Version 1.0
 */
public class SimpleJob2 implements Job {

    public SimpleJob2() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("SimpleJob2 says: " + jobKey + " executing at " + new Date());


    }
}
