package com.scamlet.todo_app.Controllers;

import com.scamlet.todo_app.DTO.TaskDTO;
import com.scamlet.todo_app.Entities.Task;
import com.scamlet.todo_app.Services.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final ITaskService iTaskService;

    @Autowired
    public TaskController(ITaskService iTaskService) {
        this.iTaskService = iTaskService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getTaskList() {
        List<TaskDTO> taskList = iTaskService.getTaskList();
        if (taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskList);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> addTask(@Valid @RequestBody Task task) {
        Optional<TaskDTO> result = iTaskService.addTask(task);
        return result.map(taskDTO -> ResponseEntity.status(HttpStatus.OK).body(taskDTO)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping("/tasks")
    public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody Task task) {
        Optional<TaskDTO> result = iTaskService.updateTask(task);
        return result.map(taskDTO -> ResponseEntity.status(HttpStatus.OK).body(taskDTO)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> deleteTask(@Valid @PathVariable Long id) {
        Optional<TaskDTO> result = iTaskService.deleteTask(id);
        return result.map(taskDTO -> ResponseEntity.status(HttpStatus.OK).body(taskDTO)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
