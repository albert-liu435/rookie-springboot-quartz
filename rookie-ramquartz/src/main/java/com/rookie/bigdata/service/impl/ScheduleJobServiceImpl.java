package com.rookie.bigdata.service.impl;

import cn.hutool.core.date.DateUtil;
import com.rookie.bigdata.entity.ScheduleJob;
import com.rookie.bigdata.mapper.ScheduleJobMapper;
import com.rookie.bigdata.schedule.ScheduleSupport;
import com.rookie.bigdata.service.ScheduleJobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rookie
 * @since 2021-06-15
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {


    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleSupport scheduleSupport;

    /**
     * 添加定时任务
     *
     * @param scheduleJob
     * @return
     */
    @Override
    public boolean add(ScheduleJob scheduleJob) throws SchedulerException {

        scheduleJob.setCreateDate(DateUtil.now());
        scheduleJob.setUpdateTime(DateUtil.now());

        scheduleSupport.add(scheduleJob);

        this.save(scheduleJob);


        return true;
    }

    @Override
    public void deleteScheduleJob(ScheduleJob scheduleJob) throws SchedulerException {

        scheduleSupport.delete(scheduleJob);

        //  this.removeById(scheduleJob.getId());


    }

    @Override
    public void updateScheduleJob(ScheduleJob scheduleJob) throws SchedulerException {

        scheduleSupport.updateJobCron(scheduleJob);
    }

    @Override
    public void runAJobNow(ScheduleJob scheduleJob) throws SchedulerException {

        scheduleSupport.runAJobNow(scheduleJob);
    }
}
