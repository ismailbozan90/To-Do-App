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

@RestController
@RequestMapping("/api")
public class UserController {

    private final IUserService  iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/getuserlist")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userList = iUserService.getUserList();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @PostMapping("/adduser")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody User user) {
        UserDTO result = iUserService.addUser(user);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/updateuser")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody User user) {
        if (user.getId() == null || user.getId() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO result = iUserService.updateUser(user);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/deleteuser/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDTO result = iUserService.deleteUser(id);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody User user) {
        UserDTO result = iUserService.loginUser(user);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
