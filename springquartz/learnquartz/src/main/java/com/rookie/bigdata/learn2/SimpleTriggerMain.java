package com.rookie.bigdata.learn2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @ClassName SimpleTriggerMain
 * @Description SimpleTriggerMain
 * @Author
 * @Date 2020/1/17 15:53
 * @Version 1.0
 */
public class SimpleTriggerMain {

    public static void main(String[] args) throws Exception {

        //获取Scheduler对象
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        Date startTime = DateBuilder.nextGivenSecondDate(null, 50);
        System.out.println(startTime);

        //构建JobDetail
        JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("job1", "group1").build();
        //构建定时器，并执行一次
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime).build();
        Date ft = scheduler.scheduleJob(job, trigger);

        job = newJob(SimpleJob.class).withIdentity("job2", "group1").build();
        trigger = (SimpleTrigger) newTrigger().withIdentity("trigger2", "group1").startAt(startTime).build();
        ft = scheduler.scheduleJob(job, trigger);



        job = newJob(SimpleJob.class).withIdentity("job3", "group1").build();
        trigger = newTrigger().withIdentity("trigger3", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(10)).build();
        ft = scheduler.scheduleJob(job, trigger);

        trigger = newTrigger().withIdentity("trigger3", "group2").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(2)).forJob(job).build();
        ft = scheduler.scheduleJob(trigger);


        job = newJob(SimpleJob.class).withIdentity("job4", "group1").build();
        trigger = newTrigger().withIdentity("trigger4", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(5)).build();
        ft = scheduler.scheduleJob(job, trigger);

        job = newJob(SimpleJob.class).withIdentity("job5", "group1").build();
        trigger = (SimpleTrigger) newTrigger().withIdentity("trigger5", "group1")
                .startAt(futureDate(5, DateBuilder.IntervalUnit.MINUTE)).build();
        ft = scheduler.scheduleJob(job, trigger);


        job = newJob(SimpleJob.class).withIdentity("job6", "group1").build();
        trigger = newTrigger().withIdentity("trigger6", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
        ft = scheduler.scheduleJob(job, trigger);


        scheduler.start();



        job = newJob(SimpleJob.class).withIdentity("job7", "group1").build();
        trigger = newTrigger().withIdentity("trigger7", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
        ft = scheduler.scheduleJob(job, trigger);


        job = newJob(SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();
        scheduler.addJob(job, true);
        scheduler.triggerJob(jobKey("job8", "group1"));


        trigger = newTrigger().withIdentity("trigger7", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInMinutes(5).withRepeatCount(20)).build();
        ft = scheduler.rescheduleJob(trigger.getKey(), trigger);
        Thread.sleep(300L * 1000L);


        scheduler.shutdown(true);
    }

}
