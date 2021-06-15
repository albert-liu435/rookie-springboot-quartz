package com.rookie.bigdata.schedule;

import com.rookie.bigdata.controller.ScheduleJobController;
import com.rookie.bigdata.entity.ScheduleJob;
import com.rookie.bigdata.service.ScheduleJobService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @ClassName ScheduleSupport
 * @Description ScheduleSupport
 * @Author rookie
 * @Date 2021/6/15 11:20
 * @Version 1.0
 */
@Component
public class ScheduleSupport {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleSupport.class);


    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobService scheduleJobService;


    @PostConstruct
    public void init() throws Exception {
        // 这里获取任务信息数据
        List<ScheduleJob> schedules = scheduleJobService.list();//(ConvertUtil.toMap("status",(Object)1));

        for (ScheduleJob schedule : schedules) {
            if (schedule.getStatus().equals("1")) {
                add(schedule);
            }

        }

    }

    @SuppressWarnings("unchecked")
    public void add(ScheduleJob schedule) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(schedule.getJobName(), schedule.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            Class<?> clazz = schedule.getIsConcurrent().equals("1") ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) clazz).withIdentity(schedule.getJobName(), schedule.getJobGroup()).build();
            jobDetail.getJobDataMap().put("schedule", schedule);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedule.getJobCron());
            trigger = TriggerBuilder.newTrigger().withIdentity(schedule.getJobName(), schedule.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(schedule.getJobCron());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }


    /**
     * 暂停一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    @Transactional(readOnly = false)
    public void pause(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.pauseJob(jobKey);
        scheduleJobService.updateById(scheduleJob);
    }

    /**
     * 恢复一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    @Transactional(readOnly = false)
    public void resume(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.resumeJob(jobKey);
        scheduleJobService.updateById(scheduleJob);
    }

    /**
     * 删除一个job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void delete(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.deleteJob(jobKey);
        scheduleJobService.removeById(scheduleJob.getId());
    }

    /**
     * 立即执行job
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 更新job时间表达式
     *
     * @param scheduleJob
     * @throws SchedulerException
     */
    public void updateJobCron(ScheduleJob scheduleJob) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getJobCron());
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);
        scheduleJobService.updateById(scheduleJob);
    }
}
