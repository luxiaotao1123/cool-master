package com.cool.boot.task.component;

import com.cool.boot.task.pojo.Task;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Vincent
 */
@Slf4j
@Component
public class TaskJob implements Job {

    private Task task;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Collection<Object> values = jobExecutionContext.getMergedJobDataMap().values();
        for (Object obj : values){
            task = (Task) obj;
            //todo
            System.out.println(task.toString());
        }
    }
}
