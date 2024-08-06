package com.scamlet.todo_app.Services;

import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.User;
import com.scamlet.todo_app.Repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository iUserRepository;
    private final ModelMapper modelmapper;

    @Autowired
    public UserService(IUserRepository iUserRepository, ModelMapper modelmapper) {
        this.iUserRepository = iUserRepository;
        this.modelmapper = modelmapper;
    }

    @Override
    public Optional<UserDTO> addUser(User user) {
        User result = iUserRepository.addUser(user);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(modelmapper.map(result, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> deleteUser(Long id) {
        User result = iUserRepository.deleteUser(id);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(modelmapper.map(result, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> updateUser(User user) {
        User result = iUserRepository.updateUser(user);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(modelmapper.map(result, UserDTO.class));
    }

    @Override
    public Optional<UserDTO> loginUser(User user) {
        User result = iUserRepository.loginUser(user);
        if (result == null) {
            return Optional.empty();
        }
        return Optional.of(modelmapper.map(result, UserDTO.class));
    }

    @Override
    public List<UserDTO> getUserList() {
        List<User> userList = iUserRepository.getUserList();
        return userList.stream().map(user->modelmapper.map(user, UserDTO.class)).toList();
    }
}
