package com.rookie.bigdata.schedule;

import com.rookie.bigdata.entity.ScheduleJob;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName QuartzJobFactoryDisallowConcurrentExecution
 * @Description QuartzJobFactoryDisallowConcurrentExecution 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 * @Author rookie
 * @Date 2021/6/15 11:35
 * @Version 1.0
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

    public static final Logger logger = LoggerFactory.getLogger(QuartzJobFactoryDisallowConcurrentExecution.class);

    private ExecutorService service = Executors.newSingleThreadExecutor();

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
