package restlibrary.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restlibrary.exception.service.UserException;
import restlibrary.model.User;
import restlibrary.repository.UserRepository;
import restlibrary.service.UserService;

import javax.persistence.NoResultException;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addNewUser(User newUser) throws UserException {
        validateNewUser(newUser);
        checkIfThatUserExist(newUser);
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

    private void validateNewUser(User newUser) throws UserException {
        if (StringUtils.isBlank(newUser.getLogin())) {
            logger.error("Login cannot be empty or blank. New User: " + newUser.toString());
            throw new UserException("Login cannot be empty or blank.");
        }

        if (StringUtils.isBlank(newUser.getName())) {
            logger.error("Name cannot be empty or blank. New User: " + newUser.toString());
            throw new UserException("Name cannot be empty or blank.");
        }

        if (StringUtils.isBlank(newUser.getSurname())) {
            logger.error("Surname cannot be empty or blank. New User: " + newUser.toString());
            throw new UserException("Surname cannot be empty or blank.");
        }
    }

    private void checkIfThatUserExist(User newUser) throws UserException {
        try {
            User user = userRepository.findByLogin(newUser.getLogin());
            if (user != null) {
                logger.error("User with that login already exist. New User: " + newUser.toString());
                throw new UserException("User with that login already exist.");
            }
        } catch (NoResultException exception) {

        }
    }
}
