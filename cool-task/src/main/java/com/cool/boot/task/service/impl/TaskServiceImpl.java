package com.cool.boot.task.service.impl;

import com.cool.boot.common.enums.HttpStatusEnum;
import com.cool.boot.common.pojo.Response;
import com.cool.boot.task.component.TaskBus;
import com.cool.boot.task.dao.TaskDao;
import com.cool.boot.task.pojo.Task;
import com.cool.boot.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

/**
 * @author Vincent
 */
@Slf4j
@Service("taskService")
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService{

    @Autowired
    public TaskServiceImpl(TaskDao taskDao, TaskBus taskBus) {
        this.taskDao = taskDao;
        this.taskBus = taskBus;
    }

    private final TaskDao taskDao;
    private final TaskBus taskBus;

    @Override
    public Response findTask(Task task) {
        if (task == null){
            log.warn("create task error：the params was empty！");
            return Response.error(HttpStatusEnum.EMPTY_PARAMS.getCode(), HttpStatusEnum.EMPTY_PARAMS.getValue());
        }
        return null;
    }

    @Override
    public Response createTask(Task task) {
        if (task == null){
            log.warn("create task error：the params was empty！");
            return Response.error(HttpStatusEnum.EMPTY_PARAMS.getCode(), HttpStatusEnum.EMPTY_PARAMS.getValue());
        }
        if (!taskBus.addTask(task)){
            log.error("create task name : {} error!", task.getName());
            return Response.error();
        }
        return Response.ok();
    }

    @Override
    public Response modifyTask(Task task) {
        if (task == null){
            log.warn("create task error：the params was empty！");
            return Response.error(HttpStatusEnum.EMPTY_PARAMS.getCode(), HttpStatusEnum.EMPTY_PARAMS.getValue());
        }
        if (!taskBus.modifyTask(task)){
            log.error("modify task name : {} error!", task.getName());
            return Response.error();
        }
        return Response.ok();
    }

    @Override
    public Response removeTask(Task task) {
        if (task == null){
            log.warn("create task error：the params was empty！");
            return Response.error(HttpStatusEnum.EMPTY_PARAMS.getCode(), HttpStatusEnum.EMPTY_PARAMS.getValue());
        }
        if (!taskBus.removeTask(task)){
            log.error("remove task name : {} error!", task.getName());
            return Response.error();
        }
        return Response.error();
    }
}
