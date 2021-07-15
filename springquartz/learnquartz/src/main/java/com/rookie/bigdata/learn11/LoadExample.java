/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 
package com.rookie.bigdata.learn11;

import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


public class LoadExample {

  private int _numberOfJobs = 500;

  public LoadExample(int inNumberOfJobs) {
    _numberOfJobs = inNumberOfJobs;
  }

  public void run() throws Exception {


    SchedulerFactory sf = new StdSchedulerFactory();
    Scheduler sched = sf.getScheduler();

    // schedule 500 jobs to run
    for (int count = 1; count <= _numberOfJobs; count++) {
      JobDetail job = newJob(SimpleJob.class).withIdentity("job" + count, "group_1").requestRecovery() // ask scheduler
                                                                                                       // to re-execute
                                                                                                       // this job if it
                                                                                                       // was in
                                                                                                       // progress when
                                                                                                       // the scheduler
                                                                                                       // went down...
          .build();


      long timeDelay = (long) (Math.random() * 2500);
      job.getJobDataMap().put(SimpleJob.DELAY_TIME, timeDelay);

      Trigger trigger = newTrigger().withIdentity("trigger_" + count, "group_1")
          .startAt(futureDate((10000 + (count * 100)), IntervalUnit.MILLISECOND)) // space fire times a small bit
          .build();

      sched.scheduleJob(job, trigger);
      if (count % 25 == 0) {
        System.out.println("...scheduled " + count + " jobs");
      }
    }

    try {
      Thread.sleep(300L * 1000L);
    } catch (Exception e) {
      //
    }

    sched.shutdown(true);

    SchedulerMetaData metaData = sched.getMetaData();
    System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
  }

  public static void main(String[] args) throws Exception {

    int numberOfJobs = 500;
    if (args.length == 1) {
      numberOfJobs = Integer.parseInt(args[0]);
    }
    if (args.length > 1) {
      System.out.println("Usage: java " + LoadExample.class.getName() + "[# of jobs]");
      return;
    }
    LoadExample example = new LoadExample(numberOfJobs);
    example.run();
  }

}
