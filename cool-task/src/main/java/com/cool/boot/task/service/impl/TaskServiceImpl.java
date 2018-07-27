package com.cool.boot.task.service.impl;

import com.cool.boot.common.enums.HttpStatusEnum;
import com.cool.boot.common.pojo.Response;
import com.cool.boot.task.dao.TaskDao;
import com.cool.boot.task.pojo.Task;
import com.cool.boot.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Vincent
 */
@Slf4j
@Service("taskService")
@Transactional(rollbackFor = Exception.class)
public class TaskServiceImpl implements TaskService{

    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

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




        //todo Persist
        return null;
    }

    @Override
    public Response modifyTask(Task task) {
        if (task == null){
            log.warn("create task error：the params was empty！");
            return Response.error(HttpStatusEnum.EMPTY_PARAMS.getCode(), HttpStatusEnum.EMPTY_PARAMS.getValue());
        }
        return null;
    }

    @Override
    public Response removeTask(Task task) {
        if (task == null){
            log.warn("create task error：the params was empty！");
            return Response.error(HttpStatusEnum.EMPTY_PARAMS.getCode(), HttpStatusEnum.EMPTY_PARAMS.getValue());
        }
        return null;
    }
}
