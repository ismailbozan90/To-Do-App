package com.scamlet.todo_app.Repositories;

import com.scamlet.todo_app.Entities.Task;

import java.util.List;

public interface ITaskRepository {
    Task addTask(Task user);
    Task deleteTask(Long id);
    Task updateTask(Task user);
    List<Task> getTaskList();
}
