package com.rookie.bigdata.learn9;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * @ClassName Job1TriggerListener
 * @Description Job1TriggerListener
 * @Author rookie
 * @Date 2020/1/19 17:28
 * @Version 1.0
 */
public class Job1TriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "Job1Trigger";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("triggerFired");

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        System.out.println("vetoJobExecution");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("triggerMisfired");

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {

        System.out.println("triggerComplete");

    }
}
