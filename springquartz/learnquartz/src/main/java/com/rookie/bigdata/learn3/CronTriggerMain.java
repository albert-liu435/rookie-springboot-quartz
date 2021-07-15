package com.rookie.bigdata.learn3;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @ClassName CronTriggerMain
 * @Description CronTriggerMain
 * @Author
 * @Date 2020/1/17 16:15
 * @Version 1.0
 */
public class CronTriggerMain {
    public static void main(String[] args) throws Exception {


        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // job1每20秒跑一次
        JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();
        CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/20 * * * * ?"))
                .build();
        Date ft = sched.scheduleJob(job, trigger);


        // job2每2分钟跑一次
        job = newJob(SimpleJob.class).withIdentity("job2", "group1").build();
        trigger = newTrigger().withIdentity("trigger2", "group1").withSchedule(cronSchedule("15 0/2 * * * ?")).build();
        ft = sched.scheduleJob(job, trigger);

        // job3 每天8到17点 每2分钟跑一次
        job = newJob(SimpleJob.class).withIdentity("job3", "group1").build();
        trigger = newTrigger().withIdentity("trigger3", "group1").withSchedule(cronSchedule("0 0/2 8-17 * * ?")).build();
        ft = sched.scheduleJob(job, trigger);

        // job4每天5到11点 每3分钟跑一次
        job = newJob(SimpleJob.class).withIdentity("job4", "group1").build();
        trigger = newTrigger().withIdentity("trigger4", "group1").withSchedule(cronSchedule("0 0/3 17-23 * * ?")).build();
        ft = sched.scheduleJob(job, trigger);


        job = newJob(SimpleJob.class).withIdentity("job5", "group1").build();
        trigger = newTrigger().withIdentity("trigger5", "group1").withSchedule(cronSchedule("0 0 10am 1,15 * ?")).build();
        ft = sched.scheduleJob(job, trigger);



        job = newJob(SimpleJob.class).withIdentity("job6", "group1").build();
        trigger = newTrigger().withIdentity("trigger6", "group1").withSchedule(cronSchedule("0,30 * * ? * MON-FRI"))
                .build();
        ft = sched.scheduleJob(job, trigger);



        job = newJob(SimpleJob.class).withIdentity("job7", "group1").build();
        trigger = newTrigger().withIdentity("trigger7", "group1").withSchedule(cronSchedule("0,30 * * ? * SAT,SUN"))
                .build();
        ft = sched.scheduleJob(job, trigger);


        sched.start();


        Thread.sleep(300L * 1000L);


        sched.shutdown(true);


        SchedulerMetaData metaData = sched.getMetaData();
        System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }
}
