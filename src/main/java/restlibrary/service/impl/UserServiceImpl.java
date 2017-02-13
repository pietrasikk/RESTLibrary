package restlibrary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restlibrary.model.User;
import restlibrary.repository.UserRepository;
import restlibrary.service.UserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addNewUser(User newUser) {
        return userRepository.addNewUser(newUser);
    }

    @Override
    public void removeUser(User user) {
        userRepository.removeUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }
}
