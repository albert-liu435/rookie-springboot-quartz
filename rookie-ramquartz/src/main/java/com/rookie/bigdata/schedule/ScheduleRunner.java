package com.rookie.bigdata.schedule;

import com.rookie.bigdata.entity.ScheduleJob;
import com.rookie.bigdata.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName ScheduleRunner
 * @Description ScheduleRunner
 * @Author rookie
 * @Date 2021/6/15 14:52
 * @Version 1.0
 */
public class ScheduleRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleRunner.class);

    @Autowired
    private ScheduleJobService scheduleJobService;
    @Autowired
    private ScheduleSupport scheduleSupport;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 这里获取任务信息数据
        List<ScheduleJob> schedules = scheduleJobService.list();//(ConvertUtil.toMap("status",(Object)1));

        for (ScheduleJob schedule : schedules) {
            if (schedule.getStatus().equals("1")) {
                scheduleSupport.add(schedule);
            }

        }
    }
}

