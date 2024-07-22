package com.scamlet.todo_app.Repositories;


import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.User;

import java.util.List;

public interface IUserRepository {

    public User addUser(User user);
    public User deleteUser(Long id);
    public User updateUser(User user);

    public User loginUser(User user);

    public List<User> getUserList();
}
