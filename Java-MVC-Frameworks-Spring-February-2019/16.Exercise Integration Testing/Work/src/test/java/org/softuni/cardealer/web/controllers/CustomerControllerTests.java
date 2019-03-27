package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.CustomerRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void emptyDB(){
        this.customerRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void add_savesEntityProperty() throws Exception {
        this.mockMvc
                .perform(
                        post("/customers/add")
                                .param("name", "pesho")
                                .param("birthDate", "2000-02-02")
                );

        Assert.assertEquals(1, this.customerRepository.count());

        this.mockMvc
                .perform(
                        post("/customers/add")
                                .param("name", "gosho")
                                .param("birthDate", "2003-03-03")
                );

        Customer actual = this.customerRepository.findAll().get(0);

        Assert.assertEquals(2, this.customerRepository.count());
        Assert.assertEquals("pesho", actual.getName());
        Assert.assertEquals("2000-02-02", actual.getBirthDate().toString());
    }

    @Test
    @WithMockUser("spring")
    public void add_redirectCorrectly() throws Exception {
        this.mockMvc
                .perform(post("/customers/add")
                        .param("name", "pesho")
                        .param("birthDate", "2000-02-02")
                )
                .andExpect(view().name("redirect:all"));
    }

    @Test
    public void all_GuestRedirecToLogin() throws Exception {
        this.mockMvc
                .perform(get("/customers/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/customers/all"))
                .andExpect(view().name("all-customers"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectAtribute() throws Exception {
        this.mockMvc
                .perform(get("/customers/all"))
                .andExpect(model().attributeExists("customers"));
    }
}
