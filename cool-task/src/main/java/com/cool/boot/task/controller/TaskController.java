package com.cool.boot.task.controller;

import com.cool.boot.common.pojo.Response;
import com.cool.boot.task.pojo.Task;
import com.cool.boot.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vincent
 */
@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public Response findTask(@RequestBody Task task){
        return taskService.findTask(task);
    }

    @PostMapping
    public Response createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping
    public Response modifyTask(@RequestBody Task task){
        return taskService.modifyTask(task);
    }

    @DeleteMapping
    public Response removeTask(@RequestBody Task task){
        return taskService.removeTask(task);
    }
}
