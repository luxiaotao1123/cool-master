package com.cool.boot.task.dao.impl;

import com.cool.boot.task.dao.TaskDao;
import com.cool.boot.task.mapper.TaskMapper;
import com.cool.boot.task.pojo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vincent
 */
@Repository("taskDao")
public class TaskDaoImpl implements TaskDao{

    private final TaskMapper taskMapper;

    @Autowired
    public TaskDaoImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> selectAll() {
        return null;
    }

    @Override
    public List<Task> selectTasks(Task task) {
        return null;
    }

    @Override
    public int insertTask(Task task) {
        return 0;
    }

    @Override
    public int updateTask(Task task) {
        return 0;
    }

    @Override
    public int deleteTask(Task task) {
        return 0;
    }
}
