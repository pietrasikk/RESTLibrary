package restlibrary.service;


import restlibrary.exception.service.UserException;
import restlibrary.model.User;

import java.util.List;

public interface UserService {

    void addNewUser(User newUser) throws UserException;

    void removeUser(Long userId) throws UserException;

    List<User> getAllUsers();

    User getUserById(Long userId);
}
