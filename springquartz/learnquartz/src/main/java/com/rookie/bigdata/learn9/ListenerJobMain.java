package com.rookie.bigdata.learn9;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @ClassName ListenerTriggerMain
 * @Description ListenerTriggerMain
 * @Author
 * @Date 2020/1/19 11:52
 * @Version 1.0
 */
public class ListenerJobMain {

    public static void main(String[] args) throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job = newJob(SimpleJob1.class).withIdentity("job1","group1").build();
        Trigger trigger = newTrigger().withIdentity("trigger1").startNow().build();

        // 针对特定的JobDetail进行监听
        JobListener listener = new Job1Listener();
        Matcher<JobKey> matcher = KeyMatcher.keyEquals(job.getKey());
        sched.getListenerManager().addJobListener(listener, matcher);

        //设置全局监听
        //sched.getListenerManager().addJobListener(listener);



        //设定局部监听
        TriggerListener TriggerListener = new Job1TriggerListener();
        KeyMatcher<TriggerKey> tkeyMatcher = KeyMatcher.keyEquals(trigger.getKey());
        sched.getListenerManager().addTriggerListener(TriggerListener, tkeyMatcher);

        //设置全局监听
        //sched.getListenerManager().addTriggerListener(TriggerListener);

        //sched.getListenerManager().addSchedulerListener();


        // schedule the job to run
        sched.scheduleJob(job, trigger);


        sched.start();

        Thread.sleep(30L * 1000L);

    }

}
