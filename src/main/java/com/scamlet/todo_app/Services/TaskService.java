package com.scamlet.todo_app.Services;


import com.scamlet.todo_app.DTO.TaskDTO;
import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.Task;
import com.scamlet.todo_app.Entities.User;
import com.scamlet.todo_app.Repositories.ITaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {


    private final ITaskRepository iTaskRepository;
    private final ModelMapper modelmapper;

    @Autowired
    public TaskService(ITaskRepository iTaskRepository, ModelMapper modelmapper) {
        this.iTaskRepository = iTaskRepository;
        this.modelmapper = modelmapper;
    }

    @Override
    public TaskDTO addTask(Task task) {
        Task result = iTaskRepository.addTask(task);
        if (result == null) {
            return null;
        }
        return modelmapper.map(result, TaskDTO.class);
    }

    @Override
    public TaskDTO deleteTask(Long id) {
        Task result = iTaskRepository.deleteTask(id);
        if (result == null) {
            return null;
        }
        return modelmapper.map(result, TaskDTO.class);
    }

    @Override
    public TaskDTO updateTask(Task task) {
        Task result = iTaskRepository.updateTask(task);
        if (result == null) {
            return null;
        }
        return modelmapper.map(result, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> getTaskList() {
        List<Task> taskList = iTaskRepository.getTaskList();
        List<TaskDTO> userDTOList = taskList.stream().map(task->modelmapper.map(task, TaskDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }
}
