package com.scamlet.todo_app.Services;

import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.User;

import java.util.List;

public interface IUserService {

    UserDTO addUser(User user);
    UserDTO deleteUser(Long id);
    UserDTO updateUser(User user);

    List<UserDTO> getUserList();

    UserDTO loginUser(User user);
}
