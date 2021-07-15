//package com.rookie.bigdata.learn9;
//
//
//import org.quartz.*;
//
//
//public interface JobListener {
//
//    /**
//     * Job监听器的名字
//     *
//     * @return
//     */
//    String getName();
//
//    /**
//     * Scheduler在JobeDetail将要被执行的时候调用该方法
//     *
//     * @param context
//     */
//    void jobToBeExecuted(JobExecutionContext context);
//
//    /**
//     * Scheduler在JobeDetail将要被执行，但是被TriggerListener取消的时候调用该方法
//     *
//     * @param context
//     */
//    void jobExecutionVetoed(JobExecutionContext context);
//
//
//    /**
//     * Scheduler在JobDetail被执行后调用该方法
//     *
//     * @param context
//     * @param jobException
//     */
//    void jobWasExecuted(JobExecutionContext context,
//                        JobExecutionException jobException);
//
//}
