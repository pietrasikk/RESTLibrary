package restlibrary.controller;


import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import restlibrary.configuration.ApplicationConfiguration;
import restlibrary.configuration.HibernateConfigurationTest;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfigurationTest.class, ApplicationConfiguration.class})
public class LibraryControllerTest {

    public static final String ADD_NEW_USER = "/addNewUser";
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testForWebApplicationContextAndController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("libraryController"));
    }

    @Test
    public void testForCreated() throws Exception {
        String user = "{\t\"login\":\"login\",\t\"name\":\"name\",\t\"surname\":\"surname\"}";
        this.mockMvc.perform(post(ADD_NEW_USER).content(user).contentType("application/json")).andDo(print())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.description").value("New user was created."))
                .andReturn();
    }

    @Test
    public void testOneOfErrorInCreationUser() throws Exception {
        String user = "{}";
        this.mockMvc.perform(post(ADD_NEW_USER).content(user).contentType("application/json")).andDo(print())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errorMessage").value("Login cannot be empty or blank."))
                .andExpect(jsonPath("$.url").value("http://localhost" + ADD_NEW_USER))
                .andReturn();
    }
}
