package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Role;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.RoleServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.repository.UserRepository;
import org.softuni.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private UserService service;

    @MockBean
    private UserRepository mockUserRepository;

    private List<User> users;

    @Before
    public void setupTest() {
        users = new ArrayList<>();

        when(mockUserRepository.findAll())
                .thenReturn(users);
    }

    @Test(expected = Exception.class)
    public void registerUser_whenNullUsername_throw() throws Exception {
        UserServiceModel user = new UserServiceModel();
        user.setUsername(null);
        user.setPassword("Test Password");
        user.setEmail("Test Email");

        Set<RoleServiceModel> authorities = new HashSet<>();
        user.setAuthorities(authorities);

        when(mockUserRepository.save(any()))
                .thenReturn(new User());

        service.registerUser(user);
        verify(mockUserRepository)
                .save(any());
    }

    @Test(expected = Exception.class)
    public void registerUser_whenNullPassword_throw() throws Exception {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("Test Username");
        user.setPassword(null);
        user.setEmail("Test Email");

        Set<RoleServiceModel> authorities = new HashSet<>();
        user.setAuthorities(authorities);

        when(mockUserRepository.save(any()))
                .thenReturn(new User());

        service.registerUser(user);
        verify(mockUserRepository)
                .save(any());
    }

    @Test(expected = Exception.class)
    public void registerUser_whenNullEmail_throw() throws Exception {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("Test Username");
        user.setPassword("Test Password");
        user.setEmail(null);

        Set<RoleServiceModel> authorities = new HashSet<>();
        user.setAuthorities(authorities);

        when(mockUserRepository.save(any()))
                .thenReturn(new User());

        service.registerUser(user);
        verify(mockUserRepository)
                .save(any());
    }

    @Test(expected = Exception.class)
    public void registerUser_whenNullAuthorities_throw() throws Exception {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("Test Username");
        user.setPassword("Test Password");
        user.setEmail("Test Email");
        user.setAuthorities(null);

        when(mockUserRepository.save(any()))
                .thenReturn(new User());

        service.registerUser(user);
        verify(mockUserRepository)
                .save(any());
    }

    @Test
    public void loadUserByUsername_whenValid_loadUser() throws Exception {
        User user = new User();
        String username = "Test Username";
        String password = "Test Password";
        String email = "Test Email";

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        when(mockUserRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        var result = service.loadUserByUsername(username);

        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test(expected = Exception.class)
    public void loadUserByUsername_whenNoUser_throw() throws Exception {
        User user = new User();
        String username = "Test Username";
        String password = "Test Password";
        String email = "Test Email";

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        when(mockUserRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        var result = service.loadUserByUsername("Empty");
        assertNull(result);
    }

    @Test
    public void loadUserByUsername_whenNullUsername_loadUser() throws Exception {
        User user = new User();
        String username = null;
        String password = "Test Password";
        String email = "Test Email";

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        when(mockUserRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        var result = service.loadUserByUsername(username);

        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test
    public void findUserByUserName_whenValid_loadUser() throws Exception {
        User user = new User();
        String username = "Test Username";
        String password = "Test Password";
        String email = "Test Email";

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        when(mockUserRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        var result = service.findUserByUserName(username);

        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test(expected = Exception.class)
    public void findUserByUserName_whenNoUser_throw() throws Exception {
        User user = new User();
        String username = "Test Username";
        String password = "Test Password";
        String email = "Test Email";

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        when(mockUserRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        var result = service.findUserByUserName("Empty");
        assertNull(result);
    }

    @Test
    public void findUserByUserName_whenNullUsername_loadUser() throws Exception {
        User user = new User();
        String username = null;
        String password = "Test Password";
        String email = "Test Email";

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        when(mockUserRepository.findByUsername(username))
                .thenReturn(Optional.of(user));

        var result = service.findUserByUserName(username);

        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test(expected = Exception.class)
    public void editUserProfile_whenUsernameNotFound_throw() throws Exception {
        UserServiceModel user = new UserServiceModel();
        user.setUsername("Test Username");
        String password = "Test Password";
        user.setPassword(password);
        user.setEmail("Test Email");

        Set<RoleServiceModel> authorities = new HashSet<>();
        user.setAuthorities(authorities);

        when(mockUserRepository.save(any()))
                .thenReturn(new User());

        service.editUserProfile(user, password);
        verify(mockUserRepository)
                .save(any());
    }

    @Test
    public void findAllUsers_when1Users_return1Users() {
        User user = new User();

        String username = "Test Username";
        user.setUsername(username);

        String password = "Test Password";
        user.setPassword(password);

        user.setEmail("Test Email");

        Set<Role> authorities = new HashSet<>();
        user.setAuthorities(authorities);

        users.add(user);

        var result = service.findAllUsers();
        UserServiceModel userResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(username, userResult.getUsername());
        assertEquals(password, userResult.getPassword());
    }

    @Test(expected = Exception.class)
    public void setUserRole_whenIncorrectId_throw() throws Exception {
        User user = new User();

        String userId = "Test Id";
        user.setId(userId);

        String username = "Test Username";
        user.setUsername(username);

        String password = "Test Password";
        user.setPassword(password);

        user.setEmail("Test Email");

        Set<Role> authorities = new HashSet<>();
        user.setAuthorities(authorities);

        users.add(user);

        String role = "ROLE_STUDENT";
        service.setUserRole(userId, role);

        User userResult = users.get(0);

        assertEquals(1, userResult.getAuthorities().size());
    }
}
