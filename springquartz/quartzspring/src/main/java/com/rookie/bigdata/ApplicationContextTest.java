package com.rookie.bigdata;

import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ApplicationContextTest
 * @Description ApplicationContextTest
 * @Author
 * @Date 2020/1/20 14:36
 * @Version 1.0
 */
public class ApplicationContextTest {

    public static Scheduler scheduler;

    public static void main(String[] args) throws Exception {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationcontext-trigger.xml");
        scheduler = (Scheduler) applicationContext.getBean("quartzScheduler");
        //SimpleJob simpleJob = new SimpleJob();

        scheduler.start();

        //从数据库中获取相应的job及调度信息
        //JobDetail jobDetail = scheduler.getJobDetail(new JobKey("trigger1", "trigger1"));
        //resumeJob(jobDetail.getKey().getName(), jobDetail.getKey().getGroup());
        //添加job执行
        addJob("trigger1", "trigger1", "job1", "job2", "0/20 * * * * ?", SimpleJob.class, new HashMap<>());
        Thread.sleep(60 * 1000);
        //重新设置调度时间
        System.out.println("重新设置调度时间");
        rescheduleJob("trigger1","trigger1","0/10 * * * * ?");

        Thread.sleep(60 * 1000);
        //暂停调度
        System.out.println("暂停调度");
        pauseJob("trigger1","trigger1");

        Thread.sleep(60 * 1000);
        System.out.println("恢复调度");
        resumeJob("trigger1","trigger1");

        Thread.sleep(60 * 1000);
        System.out.println("删除调度");
        removeJob("trigger1","trigger1");
        Thread.sleep(60 * 1000);

        System.out.println(scheduler);
    }

    /**
     * 添加job执行
     *
     * @param triggerKeyName
     * @param triggerKeyGroup
     * @param jobName
     * @param jobGroup
     * @param cronExpression
     * @param jobClass
     * @param jobData
     * @return
     * @throws Exception
     */
    public static boolean addJob(String triggerKeyName, String triggerKeyGroup, String jobName, String jobGroup, String cronExpression,
                                 Class<? extends Job> jobClass, Map<String, Object> jobData) throws Exception {

        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(triggerKeyName, triggerKeyGroup).build();

        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).withIdentity(triggerKeyName, triggerKeyGroup).build();
        if (jobData != null && jobData.size() > 0) {
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            jobDataMap.putAll(jobData);    // JobExecutionContext context.getMergedJobDataMap().get("mailGuid");
        }

        scheduler.scheduleJob(jobDetail, trigger);

//        if (!scheduler.isShutdown()) {
//            scheduler.start();
//        }

        return true;


    }

    /**
     * 重新设置job执行
     * @param triggerKeyName
     * @param triggerKeyGroup
     * @param cronExpression
     * @return
     * @throws SchedulerException
     */
    public static boolean rescheduleJob(String triggerKeyName, String triggerKeyGroup, String cronExpression) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerKeyName, triggerKeyGroup);

        if (scheduler.checkExists(triggerKey)) {
            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).withIdentity(triggerKey).build();
            scheduler.rescheduleJob(triggerKey, trigger);
        }

        return true;
    }


    /**
     * 删除job
     * @param triggerKeyName
     * @param triggerKeyGroup
     * @return
     * @throws SchedulerException
     */
    public static boolean removeJob(String triggerKeyName, String triggerKeyGroup) throws SchedulerException {
        // TriggerKey : name + group

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerKeyName, triggerKeyGroup);

        boolean result = false;
        if (scheduler.checkExists(triggerKey)) {
            result = scheduler.unscheduleJob(triggerKey);
        }

        return result;
    }

    /**
     * 暂停job
     * @param triggerKeyName
     * @param triggerKeyGroup
     * @return
     * @throws SchedulerException
     */
    public static boolean pauseJob(String triggerKeyName, String triggerKeyGroup) throws SchedulerException {
        // TriggerKey : name + group

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerKeyName, triggerKeyGroup);

        boolean result = false;
        if (scheduler.checkExists(triggerKey)) {
            scheduler.pauseTrigger(triggerKey);
            result = true;

        } else {

        }
        return result;
    }

    /**
     * 重启job
     * @param triggerKeyName
     * @param triggerKeyGroup
     * @return
     * @throws SchedulerException
     */
    public static boolean resumeJob(String triggerKeyName, String triggerKeyGroup) throws SchedulerException {

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerKeyName, triggerKeyGroup);

        boolean result = false;
        if (scheduler.checkExists(triggerKey)) {
            scheduler.resumeTrigger(triggerKey);
            result = true;

        } else {

        }
        return result;
    }


}
