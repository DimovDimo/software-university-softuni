package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Part;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.PartRepository;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PartsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Before
    public void emptyDB(){
        this.partRepository.deleteAll();
        this.supplierRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void add_savesEntityProperty() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("suftuni");
        supplier.setIsImporter(true);

        this.supplierRepository.saveAndFlush(supplier);

        this.mockMvc
                .perform(
                        post("/parts/add")
                                .param("name", "pesho")
                                .param("price", "2")
                                .param("supplier", "suftuni")
                );

        Assert.assertEquals(1, this.partRepository.count());

        this.mockMvc
                .perform(
                        post("/parts/add")
                                .param("name", "gosho")
                                .param("price", "3")
                                .param("supplier", "suftuni")
                );

        Part actual = this.partRepository.findAll().get(1);

        Assert.assertEquals(2, this.partRepository.count());
        Assert.assertEquals("gosho", actual.getName());
        Assert.assertEquals("3.00", actual.getPrice().toString());
    }

    @Test
    @WithMockUser("spring")
    public void add_redirectCorrectly() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("suftuni");
        supplier.setIsImporter(true);

        this.supplierRepository.saveAndFlush(supplier);

        this.mockMvc
                .perform(post("/parts/add")
                        .param("name", "gosho")
                        .param("price", "3")
                        .param("supplier", "suftuni")
                )
                .andExpect(view().name("redirect:all"));
    }

    @Test
    @WithMockUser("spring")
    public void edit_workCorrectly() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("suftuni");
        supplier.setIsImporter(true);

        this.supplierRepository.saveAndFlush(supplier);

        Part first = new Part();
        first.setName("partFirst");
        first.setPrice(new BigDecimal(2));
        first.setSupplier(supplier);

        Part second = new Part();
        second.setName("partSecond");
        second.setPrice(new BigDecimal(3));
        second.setSupplier(supplier);

        first = this.partRepository.saveAndFlush(first);
        second = this.partRepository.saveAndFlush(second);

        this.mockMvc
                .perform(
                        post("/parts/edit/" + first.getId())
                                .param("name", "SoftUni")
                                .param("price", "222")
                                .param("supplier", "suftuni")
                );

        this.mockMvc
                .perform(
                        post("/parts/edit/" + second.getId())
                                .param("name", "peshov")
                                .param("price", "333")
                                .param("supplier", "suftuni")
                );

        Part firstActual = this.partRepository.findById(first.getId()).orElse(null);
        Part secondActual = this.partRepository.findById(second.getId()).orElse(null);

        assert firstActual != null;
        Assert.assertEquals("SoftUni", firstActual.getName());
        Assert.assertEquals("222.00", firstActual.getPrice().toString());

        assert secondActual != null;
        Assert.assertEquals("peshov", secondActual.getName());
        Assert.assertEquals("333.00", secondActual.getPrice().toString());
    }

    @Test
    @WithMockUser("spring")
    public void delete_workCorrectly() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("suftuni");
        supplier.setIsImporter(true);

        this.supplierRepository.saveAndFlush(supplier);

        Part first = new Part();
        first.setName("partFirst");
        first.setPrice(new BigDecimal(2));
        first.setSupplier(supplier);

        Part second = new Part();
        second.setName("partSecond");
        second.setPrice(new BigDecimal(3));
        second.setSupplier(supplier);

        first = this.partRepository.saveAndFlush(first);
        second = this.partRepository.saveAndFlush(second);

        this.mockMvc
                .perform(
                        post("/parts/delete/" + first.getId())
                );

        Assert.assertEquals(1, this.partRepository.count());

        this.mockMvc
                .perform(
                        post("/parts/delete/" + second.getId())
                );

        Assert.assertEquals(0, this.partRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void delete_trowExceptionWhenInvalidGiven() throws Exception {
        this.mockMvc
                .perform(
                        post("/parts/delete/pesho")
                );
    }

    @Test
    public void all_GuestRedirecToLogin() throws Exception {
        this.mockMvc
                .perform(get("/parts/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/parts/all"))
                .andExpect(view().name("all-parts"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectAtribute() throws Exception {
        this.mockMvc
                .perform(get("/parts/all"))
                .andExpect(model().attributeExists("parts"));
    }

    @Test
    @WithMockUser("spring")
    public void fetch_workCorrectly() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setName("suftuni");
        supplier.setIsImporter(true);

        supplier = this.supplierRepository.saveAndFlush(supplier);

        Part first = new Part();
        first.setName("partFirst");
        first.setPrice(new BigDecimal(2));
        first.setSupplier(supplier);

        first = this.partRepository.saveAndFlush(first);

        String actual = this.mockMvc
                .perform(get("/parts/fetch"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assert.assertEquals("[{\"id\":\"" + first.getId() + "\",\"name\":\"partFirst\",\"price\":2.00,\"supplier\":{\"id\":\"" + supplier.getId() + "\",\"name\":\"suftuni\",\"isImporter\":true}}]", actual);
    }
}
