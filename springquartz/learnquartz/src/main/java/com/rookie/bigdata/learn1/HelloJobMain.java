package com.rookie.bigdata.learn1;

import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;


/**
 * @ClassName HelloJobMain
 * @Description HelloJobMain
 * @Author
 * @Date 2020/1/17 11:40
 * @Version 1.0
 */
public class HelloJobMain {

    public static void main(String[] args) throws Exception {
      //  DirectSchedulerFactory directSchedulerFactory =  DirectSchedulerFactory.getInstance();

        //创建一个调度工厂对象，并从里面获取一个调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();


        //获取当前时间的下一分钟
        Date runTime = DateBuilder.evenMinuteDate(new Date());
        //定义一个job jobkey名称 jobkey 组
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

        JobDataMap jobDataMap = job.getJobDataMap();
        //JobDataMap jobDataMap=new JobDataMap();
        jobDataMap.put("zhangsan",23);

        //定义一个定时器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

        scheduler.getContext().put("lisi",24);

        scheduler.scheduleJob(job, trigger);

        scheduler.start();


        Thread.sleep(65L * 1000L);

        scheduler.shutdown(true);

    }
}
