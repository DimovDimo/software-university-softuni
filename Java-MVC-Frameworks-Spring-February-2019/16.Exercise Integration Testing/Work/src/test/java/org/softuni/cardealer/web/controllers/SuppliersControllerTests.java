package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.SupplierRepository;
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
public class SuppliersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Before
    public void emptyDB(){
        this.supplierRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void add_savesEntityProperty() throws Exception {
        this.mockMvc
                .perform(
                        post("/suppliers/add")
                        .param("name", "pesho")
                        .param("isImporter", "off")
                );

        Assert.assertEquals(1, this.supplierRepository.count());

        this.mockMvc
                .perform(
                        post("/suppliers/add")
                        .param("name", "gosho")
                        .param("isImporter", "true")
                );

        Supplier actual = this.supplierRepository.findAll().get(1);

        Assert.assertEquals(2, this.supplierRepository.count());
        Assert.assertEquals("gosho", actual.getName());
        Assert.assertTrue(actual.getIsImporter());
    }

    @Test
    @WithMockUser("spring")
    public void add_redirectCorrectly() throws Exception {
        this.mockMvc
                .perform(post("/suppliers/add")
                        .param("name", "pesho")
                        .param("isImporter", "off")
                )
                .andExpect(view().name("redirect:all"));
    }

    @Test
    @WithMockUser("spring")
    public void edit_workCorrectly() throws Exception {
        Supplier first = new Supplier();
        first.setName("sotuni");
        first.setIsImporter(true);

        Supplier second = new Supplier();
        second.setName("pesho");
        second.setIsImporter(false);

        first = this.supplierRepository.saveAndFlush(first);
        second = this.supplierRepository.saveAndFlush(second);

        this.mockMvc
                .perform(
                        post("/suppliers/edit/" + first.getId())
                                .param("name", "SotUni")
                                .param("isImporter", "false")
                );

        this.mockMvc
                .perform(
                        post("/suppliers/edit/" + second.getId())
                                .param("name", "peshov")
                                .param("isImporter", "true")
                );

        Supplier firstActual = this.supplierRepository.findById(first.getId()).orElse(null);
        Supplier secondActual = this.supplierRepository.findById(second.getId()).orElse(null);

        Assert.assertEquals("SotUni", firstActual.getName());
        Assert.assertFalse(firstActual.getIsImporter());

        Assert.assertEquals("peshov", secondActual.getName());
        Assert.assertTrue(secondActual.getIsImporter());
    }

    @Test
    @WithMockUser("spring")
    public void delete_workCorrectly() throws Exception {
        Supplier first = new Supplier();
        first.setName("sotuni");
        first.setIsImporter(true);

        Supplier second = new Supplier();
        second.setName("pesho");
        second.setIsImporter(false);

        first = this.supplierRepository.saveAndFlush(first);
        second = this.supplierRepository.saveAndFlush(second);

        this.mockMvc
                .perform(
                        post("/suppliers/delete/" + first.getId())
                );

        Assert.assertEquals(1, this.supplierRepository.count());

        this.mockMvc
                .perform(
                        post("/suppliers/delete/" + second.getId())
                );

        Assert.assertEquals(0, this.supplierRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void delete_trowExceptionWhenInvalidGiven() throws Exception {
        this.mockMvc
                .perform(
                        post("/suppliers/delete/pesho")
                );
    }

    @Test
    public void all_GuestRedirecToLogin() throws Exception {
        this.mockMvc
                .perform(get("/suppliers/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/suppliers/all"))
                .andExpect(view().name("all-suppliers"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectAtribute() throws Exception {
        this.mockMvc
                .perform(get("/suppliers/all"))
                .andExpect(model().attributeExists("suppliers"));
    }

    @Test
    @WithMockUser("spring")
    public void fetch_workCorrectly() throws Exception {
        Supplier first = new Supplier();
        first.setName("sotuni");
        first.setIsImporter(true);

        first = this.supplierRepository.saveAndFlush(first);

        String actual = this.mockMvc
                .perform(get("/suppliers/fetch"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals("[{\"id\":\"" + first.getId() + "\",\"name\":\"sotuni\",\"isImporter\":true}]", actual);
    }
}
