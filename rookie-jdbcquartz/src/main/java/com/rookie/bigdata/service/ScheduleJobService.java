package com.rookie.bigdata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rookie.bigdata.entity.ScheduleJob;
import org.quartz.SchedulerException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rookie
 * @since 2021-06-15
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

    boolean add(ScheduleJob scheduleJob) throws SchedulerException;

    void deleteScheduleJob(ScheduleJob scheduleJob) throws SchedulerException;

    void updateScheduleJob(ScheduleJob scheduleJob) throws SchedulerException;

    void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException;
}
