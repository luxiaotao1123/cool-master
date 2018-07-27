package com.cool.boot.task.dao;

import com.cool.boot.task.pojo.Task;

import java.util.List;

public interface TaskDao {

    List<Task> selectAll();

    List<Task> selectTasks(Task task);

    int insertTask(Task task);

    int updateTask(Task task);

    int deleteTask(Task task);
}
