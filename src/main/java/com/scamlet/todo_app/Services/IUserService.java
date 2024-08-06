package com.scamlet.todo_app.Services;

import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<UserDTO> addUser(User user);
    Optional<UserDTO> deleteUser(Long id);
    Optional<UserDTO> updateUser(User user);

    List<UserDTO> getUserList();

    Optional<UserDTO> loginUser(User user);
}
