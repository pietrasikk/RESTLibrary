package restlibrary.repository;


import restlibrary.model.User;

import java.util.List;

public interface UserRepository {

    User addNewUser(User newUser);

    void removeUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User updateUser(User user);

    User findByLogin(String login);
}
