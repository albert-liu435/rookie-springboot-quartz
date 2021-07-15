package com.rookie.bigdata;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @ClassName SimpleJob
 * @Description SimpleJob
 * @Author
 * @Date 2020/1/20 14:39
 * @Version 1.0
 */
public class SimpleJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(new Date()+"执行SimpleJob");
    }
}
