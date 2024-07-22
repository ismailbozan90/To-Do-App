package com.scamlet.todo_app.Repositories;


import com.scamlet.todo_app.Entities.User;

import java.util.List;

public interface IUserRepository {

    User addUser(User user);
    User deleteUser(Long id);
    User updateUser(User user);

    User loginUser(User user);

    List<User> getUserList();
}
