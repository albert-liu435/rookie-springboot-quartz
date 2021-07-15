package com.rookie.bigdata.jdbc;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @ClassName JdbcJobMain
 * @Description JdbcJobMain
 * @Author
 * @Date 2020/1/20 9:59
 * @Version 1.0
 */
public class JdbcJobMain {

    public static void main(String[] args) throws Exception {


      //  StdSchedulerFactory stdSchedulerFactory=new StdSchedulerFactory();


        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        JobDetail jobDetail = JobBuilder.newJob(JdbcJob.class).withDescription("测试JOB").withIdentity("job1", "group1").build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("trigger")
                .withIdentity("trigger1", "trigger1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();


        Thread.sleep(Long.MAX_VALUE);

        scheduler.shutdown(true);


    }
}
