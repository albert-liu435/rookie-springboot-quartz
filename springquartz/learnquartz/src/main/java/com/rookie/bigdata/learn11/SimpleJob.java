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

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class SimpleJob implements Job {

  private static Logger      _log       = LoggerFactory.getLogger(SimpleJob.class);

  // job parameter
  public static final String DELAY_TIME = "delay time";


  public SimpleJob() {
  }


  public void execute(JobExecutionContext context) throws JobExecutionException {


    JobKey jobKey = context.getJobDetail().getKey();
    System.out.println("Executing job: " + jobKey + " executing at " + new Date());

    // wait for a period of time
    long delayTime = context.getJobDetail().getJobDataMap().getLong(DELAY_TIME);
    try {
      Thread.sleep(delayTime);
    } catch (Exception e) {
      //
    }

    System.out.println("Finished Executing job: " + jobKey + " at " + new Date());
  }

}
