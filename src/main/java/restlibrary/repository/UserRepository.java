package restlibrary.repository;


import restlibrary.model.User;

import java.util.List;

public interface UserRepository {

    void addNewUser(User newUser);

    void removeUser(Long userId);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User updateUser(User user);

    User findByLogin(String login);
}
