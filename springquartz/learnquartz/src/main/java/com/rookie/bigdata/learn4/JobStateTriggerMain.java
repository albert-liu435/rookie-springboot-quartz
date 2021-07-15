package com.rookie.bigdata.learn4;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @ClassName JobStateTriggerMain
 * @Description JobStateTriggerMain
 * @Author
 * @Date 2020/1/19 11:17
 * @Version 1.0
 */
public class JobStateTriggerMain {

    public static void main(String[] args) throws Exception {

        //获取调度实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        // get a "nice round" time a few seconds in the future....
        Date startTime = nextGivenSecondDate(null, 10);

        // job1将会执行5次，并且时间间隔是10seconds
        JobDetail job1 = newJob(ColorJob.class).withIdentity("job1", "group1").build();
        SimpleTrigger trigger1 = newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();
        job1.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Green");
        job1.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        Date scheduleTime1 = sched.scheduleJob(job1, trigger1);



        JobDetail job2 = newJob(ColorJob.class).withIdentity("job2", "group1").build();
        SimpleTrigger trigger2 = newTrigger().withIdentity("trigger2", "group1").startAt(startTime)
                .withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4)).build();
        job2.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Red");
        job2.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        // schedule the job to run
        Date scheduleTime2 = sched.scheduleJob(job2, trigger2);



        sched.start();

        Thread.sleep(60L * 1000L);


        //所有job结束时，停止任务
        sched.shutdown(true);
    }

}
