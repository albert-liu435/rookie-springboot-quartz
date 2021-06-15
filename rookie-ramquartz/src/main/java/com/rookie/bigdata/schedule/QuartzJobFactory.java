package com.rookie.bigdata.schedule;

import com.rookie.bigdata.controller.ScheduleJobController;
import com.rookie.bigdata.entity.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName QuartzJobFactory
 * @Description QuartzJobFactory 计划任务执行处 无状态
 * @Author rookie
 * @Date 2021/6/15 11:25
 * @Version 1.0
 */
public class QuartzJobFactory implements Job {

    public static final Logger logger = LoggerFactory.getLogger(QuartzJobFactory.class);


    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob schedule = (ScheduleJob) context.getMergedJobDataMap().get("schedule");
        try {
            long startTime = System.currentTimeMillis();
            ScheduleRunnable task = new ScheduleRunnable(schedule.getBeanName(), schedule.getMethodName(), "");
            Future<?> future = service.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            logger.info("任务执行完毕，任务名称：" + schedule.getJobName() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
            logger.error("任务执行失败，任务名称：" + schedule.getJobName(), e);
        }
    }
}
