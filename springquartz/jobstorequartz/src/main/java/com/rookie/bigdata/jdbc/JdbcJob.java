package com.rookie.bigdata.jdbc;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName JdbcJob
 * @Description JdbcJob
 * @Author
 * @Date 2020/1/20 9:58
 * @Version 1.0
 */
public class JdbcJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


        System.out.println("job执行成功");
    }
}
