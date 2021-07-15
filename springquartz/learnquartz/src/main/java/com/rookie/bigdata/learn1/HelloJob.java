package com.rookie.bigdata.learn1;

import org.quartz.*;

/**
 * @ClassName HelloJob
 * @Description HelloJob
 * @Author
 * @Date 2020/1/17 11:35
 * @Version 1.0
 * <p>
 * 任务需要执行的任务都需要实现Job这个接口
 */
public class HelloJob implements Job {
    /**
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("HelloJob 任务执行啦");

        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobKey key = jobDetail.getKey();
        Trigger trigger = jobExecutionContext.getTrigger();
        TriggerKey key1 = trigger.getKey();

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println(mergedJobDataMap.get("zhangsan"));

        SchedulerContext context = null;
        try {
            context = jobExecutionContext.getScheduler().getContext();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        System.out.println(context.get("lisi"));


    }
}
