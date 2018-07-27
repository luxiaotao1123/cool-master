package com.cool.boot.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {
    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        JobDetail jobDetail = newJob(MyJob.class)
                .withIdentity("cool_job", "boot_group")
                .usingJobData("name","luxiaotao")
                .build();


        Trigger trigger = newTrigger()
                .withIdentity("cool_trigger","cool_group")
                .startNow()
                .withSchedule(simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever())
                .build();


        scheduler.scheduleJob(jobDetail, trigger);
    }

}
