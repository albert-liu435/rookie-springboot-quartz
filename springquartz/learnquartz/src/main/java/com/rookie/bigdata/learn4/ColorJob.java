package com.rookie.bigdata.learn4;

import org.quartz.*;

import java.util.Date;

/**
 * @ClassName ColorJob
 * @Description ColorJob
 * @Author
 * @Date 2020/1/19 11:12
 * @Version 1.0
 */

@PersistJobDataAfterExecution
//表示 Quartz 将会在成功执行 execute() 方法后（没有抛出异常）更新 JobDetail 的 JobDataMap，下一次执行相同的任务（JobDetail）将会得到更新后的值，而不是原始的值
@DisallowConcurrentExecution //定义不能同时并发执行相同的JobDetail
public class ColorJob implements Job {

    public static final String FAVORITE_COLOR = "favorite color";
    public static final String EXECUTION_COUNT = "count";

    private int _counter = 1;

    public ColorJob() {
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        //获取执行该任务的JobKey
        JobKey jobKey = context.getJobDetail().getKey();

        JobDataMap data = context.getJobDetail().getJobDataMap();
        String favoriteColor = data.getString(FAVORITE_COLOR);
        int count = data.getInt(EXECUTION_COUNT);
        System.out.println("ColorJob: " + jobKey + " executing at " + new Date() + "\n" +
                "  favorite color is " + favoriteColor + "\n" +
                "  execution count (from job map) is " + count + "\n" +
                "  execution count (from job member variable) is " + _counter);


        count++;
        data.put(EXECUTION_COUNT, count);


        _counter++;
    }

}
