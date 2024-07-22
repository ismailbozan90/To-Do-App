package com.scamlet.todo_app.Services;

import com.scamlet.todo_app.DTO.UserDTO;
import com.scamlet.todo_app.Entities.User;
import com.scamlet.todo_app.Repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
    public UserDTO addUser(User user) {
        User result = iUserRepository.addUser(user);
        if (result == null) {
            return null;
        }
        return modelmapper.map(result, UserDTO.class);
    }

    @Override
    public UserDTO deleteUser(Long id) {
        User result = iUserRepository.deleteUser(id);
        if (result == null) {
            return null;
        }
        return modelmapper.map(result, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(User user) {
        User result = iUserRepository.updateUser(user);
        if (result == null) {
            return null;
        }
        return modelmapper.map(result, UserDTO.class);
    }

    @Override
    public UserDTO loginUser(User user) {
        User result = iUserRepository.loginUser(user);
        if (result == null) {
            return null;
        }
        return modelmapper.map(result, UserDTO.class);
    }

    @Override
    public List<UserDTO> getUserList() {
        List<User> userList = iUserRepository.getUserList();
        List<UserDTO> userDTOList = userList.stream().map(user->modelmapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return userDTOList;
    }
}
