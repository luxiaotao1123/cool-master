package com.cool.boot.task.service;

import com.cool.boot.common.pojo.Response;
import com.cool.boot.task.pojo.Task;

public interface TaskService {

    Response findTask(Task task);

    Response createTask(Task task);

    Response modifyTask(Task task);

    Response removeTask(Task task);

}
