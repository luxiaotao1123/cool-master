package com.cool.boot.task.component;


import com.cool.boot.task.dao.TaskDao;
import com.cool.boot.task.pojo.Task;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

import static org.quartz.JobBuilder.newJob;

/**
 * @author Vincent
 */
@Slf4j
@Component("taskBus")
public class TaskBus {

    private String JOB_DETAIL_GROUP = "job_detail_group";
    private String JOB_TRIGGER_GROUP = "job_trigger_group";

    @Autowired
    public TaskBus(TaskDao taskDao, TaskJob taskJob) {
        this.taskDao = taskDao;
        this.taskJob = taskJob;
    }

    private final TaskDao taskDao;
    private final TaskJob taskJob;
    private Scheduler scheduler;
    private JobDetail jobDetail;
    private JobKey jobKey;

    /*
     * when the class is new
     */
    {
        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            jobDetail = newJob(TaskJob.class).build();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * init scheduler
     */
    @PostConstruct
    public void initScheduler(){
        log.info("start init task process ......");
        try {
            List<Task> allTasks = taskDao.selectAll();
            if (allTasks == null || allTasks.isEmpty()){
                log.info("there is no task to run ......");
                return;
            }
            allTasks.forEach(task -> {
                try {
                    jobDetail.getJobDataMap().put(task.getName(), task);
                    scheduler.scheduleJob(jobDetail, TriggerBuilder.newTrigger().
                            withIdentity(task.getName(), JOB_TRIGGER_GROUP).
                            withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).
                            build());
                } catch (SchedulerException ignore) {
                }
            });
            scheduler.start();
        }catch (Exception e){
            log.error(" init task process defeat !!!");
            throw new RuntimeException(e);
        }
    }

    /**
     * init task
     */
    private boolean initTask(String name){
        if (name == null || "".equals(name)){
            return false;
        }
        try {
            jobKey = new JobKey(name, JOB_DETAIL_GROUP);
            jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null){
                jobDetail = newJob(TaskJob.class).withIdentity(name, JOB_DETAIL_GROUP).build();
            }
            return true;
        } catch (SchedulerException ignore) {
            return false;
        }
    }

    /**
     * add task
     */
    public boolean addTask(Task task){
        try {
            if (scheduler.checkExists(new JobKey(task.getName(), JOB_DETAIL_GROUP))){
                log.warn("the task name ={} exist", task.getName());
                return false;
            }
            if (!initTask(task.getName())){
                return false;
            }
            jobDetail.getJobDataMap().put(task.getName(), task);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getName(), JOB_TRIGGER_GROUP).withSchedule(cronScheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
            if (!scheduler.isStarted()){
                scheduler.start();
            }
            if (taskDao.insertTask(task) != 1){
                log.error("persist task name={} error", task.getName());
            }
            log.info("add task name={} success", task.getName());
            return true;
        } catch (Exception e){
            log.error("add task name={} error", task.getName());
        }
        return false;
    }

    /**
     * modify task
     */
    public boolean modifyTask(Task task){
        try {
            TriggerKey triggerKey = new TriggerKey(task.getName(), JOB_TRIGGER_GROUP);
            if (!scheduler.checkExists(triggerKey)){
                log.warn("the task name ={} not exist", task.getName());
                return false;
            }
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCron());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getName(), JOB_TRIGGER_GROUP).withSchedule(cronScheduleBuilder).build();
            trigger.getJobDataMap().put(task.getName(), task);
            scheduler.rescheduleJob(triggerKey, trigger);
            if (taskDao.updateTask(task) != 1){
                log.error("persist task name={} error", task.getName());
            }
            log.info("modify task name={} error", task.getName());
            return true;
        } catch (Exception ignore){
            log.error("modify task name={} error", task.getName());
        }
        return false;
    }

    /**
     * remove task
     */
    public boolean removeTask(Task task){
        try {
            jobKey = new JobKey(task.getName(), JOB_DETAIL_GROUP);
            if (!scheduler.checkExists(jobKey)){
                log.warn("the task name ={} not exist", task.getName());
                return true;
            }
            scheduler.deleteJob(jobKey);
            if (taskDao.deleteTask(task) != 1){
                log.error("persist task name={} error", task.getName());
                return false;
            }
            log.info("remove task name={} error", task.getName());
            return true;
        } catch (Exception ignore){
            log.error("modify task name={} error", task.getName());
        }
        return false;
    }

    @PreDestroy
    public void destroyScheduler(){
        try {
            if (scheduler != null && !scheduler.isShutdown()){
                scheduler.shutdown();
                log.info("destroy scheduler success");
            }
        } catch (SchedulerException e) {
            log.warn("destroy scheduler error", e);
        }
    }
}
