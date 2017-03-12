package restlibrary.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.exception.service.UserException;
import restlibrary.model.User;
import restlibrary.repository.UserRepository;
import restlibrary.service.UserService;

import javax.persistence.NoResultException;
import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addNewUser(User newUser) throws UserException {
        validateNewUser(newUser);
        checkIfThatUserExist(newUser);
        userRepository.addNewUser(newUser);
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
        logger.info("Start validate new user with login: " + newUser.getLogin());
        if (StringUtils.isBlank(newUser.getLogin())) {
            throwException("Login cannot be empty or blank.");
        }

        if (StringUtils.isBlank(newUser.getName())) {
            throwException("Name cannot be empty or blank.");
        }

        if (StringUtils.isBlank(newUser.getSurname())) {
            throwException("Surname cannot be empty or blank.");
        }
        logger.info("Validate new user with login: " + newUser.getLogin() + " is finished.");
    }

    private void throwException(String errorMessage) throws UserException {
        logger.error(errorMessage);
        throw new UserException(errorMessage);
    }

    private void checkIfThatUserExist(User newUser) throws UserException {
        logger.info("Check if user with login: " + newUser.getLogin() + " already exists.");
        try {
            User user = userRepository.findByLogin(newUser.getLogin());
            if (user != null) {
                logger.error("User with that login already exist. New User: " + newUser.toString());
                throw new UserException("User with that login already exist.");
            }
        } catch (NoResultException exception) {
            logger.info("User with login: " + newUser.getLogin() + " can be add to database.");
        } finally {
            logger.info("User with login: " + newUser.getLogin() + " does not exist in database.");
        }
    }
}
