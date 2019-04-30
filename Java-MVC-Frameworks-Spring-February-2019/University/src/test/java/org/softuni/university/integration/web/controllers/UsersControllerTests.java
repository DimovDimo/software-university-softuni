package org.softuni.university.integration.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UsersControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void emptyDB(){
        this.userRepository.deleteAll();
    }

    @Test
    public void login_whenGet_returnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/login"))
                .andExpect(view().name("user/login"));
    }

    @Test
    public void register_whenNoRegister_ReturnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/register"))
                .andExpect(view().name("user/register"));
    }

    @Test
    public void register_whenNoRegister_RegisterUserCorrectly() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "username")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(1, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenNoRegisterInvalidUsernameShort_doNotRegisterUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "p")
                                .param("password", "passwordPesho")
                                .param("confirmPassword", "passwordPesho")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(0, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenNoRegisterInvalidUsernameLon_doNotRegisterUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "pppppppppppppppppppppppppppppp")
                                .param("password", "passwordPesho")
                                .param("confirmPassword", "passwordPesho")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(0, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenNoRegisterInvalidPasswordShort_doNotRegisterUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "username")
                                .param("password", "p")
                                .param("confirmPassword", "p")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(0, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenNoRegisterInvalidPasswordLong_doNotRegisterUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "username")
                                .param("password", "ppppppppppppppppppppppppppppppppppppppppppp")
                                .param("confirmPassword", "ppppppppppppppppppppppppppppppppppppppppppp")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(0, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenNoRegisterPasswordLong_doNotRegisterUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "username")
                                .param("password", "ppppppppppppppppppppppppppppppppppppppppppp")
                                .param("confirmPassword", "ppppppppppppppppppppppppppppppppppppppppppp")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(0, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenNoRegisterDoNotConfirmPassword_doNotRegisterUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "username")
                                .param("password", "password")
                                .param("confirmPassword", "confirmPassword")
                                .param("email", "p@p.p")
                );

        Assert.assertEquals(0, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenNoEmail_doNotRegisterUser() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "username")
                                .param("password", "password")
                                .param("confirmPassword", "confirmPassword")
                                .param("email", "")
                );

        Assert.assertEquals(0, this.userRepository.findAll().size());
    }

    @Test
    public void register_whenRegister_redirectCorrect() throws Exception {
        this.mockMvc
                .perform(
                        post("/users/register")
                                .param("username", "pesho")
                                .param("password", "passwordPesho")
                                .param("confirmPassword", "passwordPesho")
                                .param("email", "p@p.p")
                )
                .andExpect(view().name("user/register"));
    }

    @Test
    @WithMockUser
    public void profile_whenGetIsNotAuthenticated_returnsErrorView() throws Exception {
        this.mockMvc
                .perform(get("/users/profile"))
                .andExpect(view().name("error"));
    }

    @Test
    @WithMockUser
    public void edit_whenGetIsNotAuthenticated_returnsErrorView() throws Exception {
        this.mockMvc
                .perform(get("/users/edit"))
                .andExpect(view().name("error"));
    }

    @Test
    @WithMockUser
    public void all_whenGetIsNotAuthenticated_returnsErrorView() throws Exception {
        this.mockMvc
                .perform(get("/users/all"))
                .andExpect(view().name("error"));
    }
}
