package com.rookie.bigdata.controller;


import com.rookie.bigdata.common.Result;
import com.rookie.bigdata.entity.ScheduleJob;
import com.rookie.bigdata.service.ScheduleJobService;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rookie
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/schedulejob")
public class ScheduleJobController {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);

    @Autowired
    private ScheduleJobService scheduleJobService;


    @RequestMapping("save")
    public Result addScheduleJob(@Validated @RequestBody ScheduleJob scheduleJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("缺少必要的参数:{}", bindingResult.getFieldError().getDefaultMessage());
            return Result.fail(4001, bindingResult.getFieldError().getDefaultMessage());
        }

        if (!CronExpression.isValidExpression(scheduleJob.getJobCron())) {
            return Result.fail(5001, "CRON表达式错误");
        }

        try {
            boolean save = scheduleJobService.add(scheduleJob);
        } catch (SchedulerException e) {

            logger.info(e.toString());

            return Result.fail("保存失败");
        }

        return Result.succ();
    }


    /**
     * 新增或者更新渠道
     *
     * @param id
     * @param
     * @return
     */
    @RequestMapping("delete/{id}")
    public Result deleteScheduleJob(@PathVariable("id") Integer id) {

        //查询出该任务
        ScheduleJob scheduleJob = scheduleJobService.getById(id);

        try {
            scheduleJobService.deleteScheduleJob(scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.fail("删除失败");
        }

        return Result.succ();
    }

    @RequestMapping("update")
    public Result updateScheduleJob(@Validated @RequestBody ScheduleJob scheduleJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("缺少必要的参数:{}", bindingResult.getFieldError().getDefaultMessage());
            return Result.fail(4001, bindingResult.getFieldError().getDefaultMessage());
        }

        if (!CronExpression.isValidExpression(scheduleJob.getJobCron())) {
            return Result.fail(5001, "CRON表达式错误");
        }

        try {
            scheduleJobService.updateScheduleJob(scheduleJob);
        } catch (SchedulerException e) {

            logger.info(e.toString());

            return Result.fail("更新失败");
        }

        return Result.succ();
    }


    @RequestMapping("runjobnow")
    public Result runScheduleJobNow(@Validated @RequestBody ScheduleJob scheduleJob, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info("缺少必要的参数:{}", bindingResult.getFieldError().getDefaultMessage());
            return Result.fail(4001, bindingResult.getFieldError().getDefaultMessage());
        }

        if (!CronExpression.isValidExpression(scheduleJob.getJobCron())) {
            return Result.fail(5001, "CRON表达式错误");
        }

        try {
            scheduleJobService.runAJobNow(scheduleJob);
        } catch (SchedulerException e) {

            logger.info(e.toString());

            return Result.fail("立即执行任务失败");
        }

        return Result.succ();
    }


}
