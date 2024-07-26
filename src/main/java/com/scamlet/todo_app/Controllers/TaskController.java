package com.scamlet.todo_app.Controllers;

import com.scamlet.todo_app.DTO.TaskDTO;
import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.Task;
import com.scamlet.todo_app.Entities.User;
import com.scamlet.todo_app.Services.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final ITaskService iTaskService;

    @Autowired
    public TaskController(ITaskService iTaskService) {
        this.iTaskService = iTaskService;
    }

    @GetMapping("/gettasklist")
    public ResponseEntity<List<TaskDTO>> getUserList() {
        List<TaskDTO> taskList = iTaskService.getTaskList();
        if (taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    @PostMapping("/addtask")
    public ResponseEntity<TaskDTO> addUser(@Valid @RequestBody Task task) {
        TaskDTO result = iTaskService.addTask(task);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/updatetask")
    public ResponseEntity<TaskDTO> updateUser(@Valid @RequestBody Task task) {
        if (task.getId() == null || task.getId() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TaskDTO result = iTaskService.updateTask(task);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/deletetask/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TaskDTO result = iTaskService.deleteTask(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
