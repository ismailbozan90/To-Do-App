package com.scamlet.todo_app.Services;

import com.scamlet.todo_app.DTO.TaskDTO;
import com.scamlet.todo_app.Entities.Task;
import java.util.List;
import java.util.Optional;

public interface ITaskService {

    Optional<TaskDTO> addTask(Task user);
    Optional<TaskDTO> deleteTask(Long id);
    Optional<TaskDTO> updateTask(Task user);
    List<TaskDTO> getTaskList();

}
