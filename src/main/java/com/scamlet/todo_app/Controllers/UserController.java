package com.scamlet.todo_app.Controllers;

import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.User;
import com.scamlet.todo_app.Services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final IUserService  iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userList = iUserService.getUserList();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody User user) {
        Optional<UserDTO> result = iUserService.addUser(user);
        return result.map(userDTO -> ResponseEntity.status(HttpStatus.OK).body(userDTO)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody User user) {
        Optional<UserDTO> result = iUserService.updateUser(user);
        return result.map(userDTO -> ResponseEntity.status(HttpStatus.OK).body(userDTO)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> deleteUser(@Valid @PathVariable Long id) {
        Optional<UserDTO> result = iUserService.deleteUser(id);
        return result.map(userDTO -> ResponseEntity.status(HttpStatus.OK).body(userDTO)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody User user) {
        Optional<UserDTO> result = iUserService.loginUser(user);
        return result.map(userDTO -> ResponseEntity.status(HttpStatus.OK).body(userDTO)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
