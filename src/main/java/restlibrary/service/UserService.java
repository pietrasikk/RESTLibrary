package restlibrary.service;


import restlibrary.model.User;

import java.util.List;

public interface UserService {

    User addNewUser(User newUser);

    void removeUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);
}
