package restlibrary.services;


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
import restlibrary.model.User;
import restlibrary.model.enums.UserRoleEnum;
import restlibrary.service.UserService;

import javax.persistence.PersistenceException;

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
    public void testForAddNewUser() {
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
    public void testForAddNewUserWithoutAnyRequiredAttribute() {
        thrown.expect(PersistenceException.class);

        User newUser = new User();
        newUser.setSurname("Test_surname");
        newUser.setLogin("Test_login");
        newUser.setRole(UserRoleEnum.CUSTOMER);

        userService.addNewUser(newUser);
    }

    @Test
    public void testForRemoveUser() {
        User newUser_1 = new User();
        newUser_1.setName("Test_name");
        newUser_1.setSurname("Test_surname");
        newUser_1.setLogin("Test_login");
        newUser_1.setRole(UserRoleEnum.CUSTOMER);

        User newUser_2 = new User();
        newUser_2.setName("Test_name_2");
        newUser_2.setSurname("Test_surname_2");
        newUser_2.setLogin("Test_login_2");
        newUser_2.setRole(UserRoleEnum.WORKER);

        Assert.assertEquals(userService.getAllUsers().size(), 0);
        userService.addNewUser(newUser_1);
        User user = userService.addNewUser(newUser_2);
        Assert.assertEquals(userService.getAllUsers().size(), 2);
        userService.removeUser(user);
        Assert.assertEquals(userService.getAllUsers().size(), 1);
    }
}