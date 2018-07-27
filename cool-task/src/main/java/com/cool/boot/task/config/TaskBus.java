package com.cool.boot.task.config;


import com.cool.boot.task.dao.TaskDao;
import com.cool.boot.task.pojo.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @author Vincent
 */
@Slf4j
@Component("taskBus")
@Transactional(rollbackFor = Exception.class)
public class TaskBus {

    private final TaskDao taskDao;

    @Autowired
    public TaskBus(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @PostConstruct
    public void initScheduler(){

        log.info("start init task process ......");

        try {

            List<Task> allTasks = taskDao.selectAll();

            if (allTasks == null || allTasks.isEmpty()){

                log.info("there is no task to run ......");
            }

        }catch (Exception e){

            log.error(" init task process defeat !!!");
            throw new RuntimeException(e);
        }



    }


    @PreDestroy
    public void destroyScheduler(){



    }
}
