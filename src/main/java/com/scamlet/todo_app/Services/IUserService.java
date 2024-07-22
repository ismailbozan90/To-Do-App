package com.scamlet.todo_app.Services;

import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.User;

import java.util.List;

public interface IUserService {

    public UserDTO addUser(User user);
    public UserDTO deleteUser(Long id);
    public UserDTO updateUser(User user);

    public List<UserDTO> getUserList();

    public UserDTO loginUser(User user);
}
