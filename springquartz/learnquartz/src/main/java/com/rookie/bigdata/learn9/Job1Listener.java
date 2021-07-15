package com.rookie.bigdata.learn9;

import org.quartz.*;


/**
 * @ClassName Job1Listener
 * @Description Job1Listener
 * @Author
 * @Date 2020/1/19 11:50
 * @Version 1.0
 */
public class Job1Listener implements JobListener {
    /**
     * @return JobListener 名称
     */
    @Override
    public String getName() {
        return "job1_to_job2";
    }

    /**
     * @param context
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext context) {

        JobDetail jobDetail = context.getJobDetail();
        System.out.println("Job1Listener says: " + jobDetail + "Job Is about to be executed.");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        System.out.println("Job1Listener says: " + jobDetail + "Job Execution was vetoed.");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        JobDetail jobDetail = context.getJobDetail();
        System.out.println("Job1Listener says: " + jobDetail + "Job was executed.");

        // Simple job #2
//        JobDetail job2 = newJob(SimpleJob2.class).withIdentity("job2").build();
//
//        Trigger trigger = newTrigger().withIdentity("job2Trigger").startNow().build();
//
//        try {
//            // schedule the job to run!
//            context.getScheduler().scheduleJob(job2, trigger);
//        } catch (SchedulerException e) {
//            System.out.println("Unable to schedule job2!");
//            e.printStackTrace();
//        }
    }
}
