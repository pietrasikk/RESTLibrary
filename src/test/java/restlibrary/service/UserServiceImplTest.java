package restlibrary.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import restlibrary.configuration.HibernateConfigurationTest;
import restlibrary.exception.service.UserException;
import restlibrary.model.User;
import restlibrary.model.enums.UserRoleEnum;


@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfigurationTest.class})
public class UserServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private UserService userService;

    @Test
    public void testForAddNewUser() throws UserException {
        User newUser = new User();
        newUser.setName("Test_name");
        newUser.setSurname("Test_surname");
        newUser.setLogin("Test_login");
        newUser.setRole(UserRoleEnum.CUSTOMER);

        Assert.assertEquals(userService.getAllUsers().size(), 0);
        userService.addNewUser(newUser);
        Assert.assertEquals(userService.getAllUsers().size(), 1);
    }

    @Test
    public void testForAlreadyExistUser() throws UserException {
        thrown.expect(UserException.class);
        thrown.expectMessage("User with that login already exist.");

        User newUser = new User();
        newUser.setName("Test_name");
        newUser.setSurname("Test_surname");
        newUser.setLogin("Test_login");
        newUser.setRole(UserRoleEnum.CUSTOMER);

        Assert.assertEquals(userService.getAllUsers().size(), 0);
        userService.addNewUser(newUser);
        userService.addNewUser(newUser);
    }

    @Test
    public void testForAddNewUserWithEmptyOrNullFields() throws UserException {
        thrown.expect(UserException.class);
        thrown.expectMessage("Login cannot be empty or blank.");

        User newUser = new User();

        Assert.assertEquals(userService.getAllUsers().size(), 0);
        userService.addNewUser(newUser);
    }


    @Test
    public void testForAddNewUserWithoutRequiredField() throws UserException {
        thrown.expect(UserException.class);
        thrown.expectMessage("Surname cannot be empty or blank.");

        User newUser = new User();
        newUser.setName("Test_name");
        newUser.setLogin("Test_login");
        newUser.setRole(UserRoleEnum.CUSTOMER);

        Assert.assertEquals(userService.getAllUsers().size(), 0);
        userService.addNewUser(newUser);
    }
}
