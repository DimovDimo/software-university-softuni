package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Role;
import org.softuni.university.domain.models.service.RoleServiceModel;
import org.softuni.university.repository.RoleRepository;
import org.softuni.university.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleServiceTests {
    @Autowired
    private RoleService service;

    @MockBean
    private RoleRepository mockRoleRepository;

    private List<Role> roles;

    @Before
    public void setupTest() {
        roles = new ArrayList<>();

        when(mockRoleRepository.findAll())
                .thenReturn(roles);

        service.seedRolesInDb();
    }

    @Test
    public void seedRolesInDb_whenEmpty_returnRmpty() {
        int result = mockRoleRepository.findAll().size();

        assertEquals(0, result);
    }

    @Test
    public void seedRolesInDb_whenSeed_return1Role() {
        roles.add(new Role("Role"));
        service.seedRolesInDb();
        int result = mockRoleRepository.findAll().size();

        assertEquals(1, result);
    }

    @Test
    public void findAllRoles_whenEmpty_returnRmpty() {
        int result = mockRoleRepository.findAll().size();

        assertEquals(0, result);
    }

    @Test
    public void findAllRoles_when1Role_return1Role() {
        roles.add(new Role("Role"));
        int result = mockRoleRepository.findAll().size();

        assertEquals(1, result);
    }

    @Test(expected = Exception.class)
    public void findByAuthority_whenEmpty_throw() {
        Role result = mockRoleRepository.findByAuthority("empty");

        assertNull(result.getAuthority());
    }

    @Test
    public void findByAuthority_when1Role_return1Role() throws Exception {
        Role role = new Role("Role");

        when(mockRoleRepository.findByAuthority("Role"))
                .thenReturn(role);

        RoleServiceModel result = service.findByAuthority("Role");

        assertEquals("Role", result.getAuthority());
    }
}
