package com.scamlet.todo_app.Services;

import com.scamlet.todo_app.DTO.TaskDTO;
import com.scamlet.todo_app.Entities.Task;

import java.util.List;

public interface ITaskService {

    TaskDTO addTask(Task user);
    TaskDTO deleteTask(Long id);
    TaskDTO updateTask(Task user);
    List<TaskDTO> getTaskList();

}
