package org.softuni.cardealer.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.cardealer.domain.entities.Car;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.repository.CarRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CarsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Before
    public void emptyDB(){
        this.carRepository.deleteAll();
    }

    @Test
    @WithMockUser("spring")
    public void add_savesEntityProperty() throws Exception {
        this.mockMvc
                .perform(post("/cars/add")
                        .param("make", "q")
                        .param("model", "w")
                        .param("travelledDistance", "99")
                );

        Assert.assertEquals(1L, this.carRepository.count());
    }

    @Test
    @WithMockUser("spring")
    public void add_redirectCorrectly() throws Exception {
        this.mockMvc
                .perform(
                        post("/cars/add")
                        .param("make", "q")
                        .param("model", "w")
                        .param("travelledDistance", "99")
                )
                .andExpect(view().name("redirect:all"));
    }

    @Test
    @WithMockUser("spring")
    public void edit_workCorrectly() throws Exception {
        Car car = new Car();
        car.setMake("q");
        car.setModel("w");
        car.setTravelledDistance(99l);

        Car first = this.carRepository.saveAndFlush(car);

        this.mockMvc
                .perform(
                        post("/cars/edit/" + first.getId())
                        .param("make", "q2")
                        .param("model", "w2")
                        .param("travelledDistance", "9999")
                );

        Car firstActual = this.carRepository.findById(first.getId()).orElse(null);

        assert firstActual != null;
        Assert.assertEquals("q2", firstActual.getMake());
        Assert.assertEquals("w2", firstActual.getModel());
        Assert.assertEquals("9999", firstActual.getTravelledDistance().toString());
    }

    @Test
    @WithMockUser("spring")
    public void delete_workCorrectly() throws Exception {
        Car car = new Car();
        car.setMake("q");
        car.setModel("w");
        car.setTravelledDistance(99l);

        Car first = this.carRepository.saveAndFlush(car);

        Car car2 = new Car();
        car2.setMake("q2");
        car2.setModel("w2");
        car2.setTravelledDistance(999l);

        Car second = this.carRepository.saveAndFlush(car2);

        this.mockMvc
                .perform(
                        post("/cars/delete/" + first.getId())
                );

        Assert.assertEquals(1, this.carRepository.count());

        this.mockMvc
                .perform(
                        post("/cars/delete/" + second.getId())
                );

        Assert.assertEquals(0, this.carRepository.count());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void delete_trowExceptionWhenInvalidGiven() throws Exception {
        this.mockMvc
                .perform(
                        post("/cars/delete/pesho")
                );
    }

    @Test
    public void all_GuestRedirecToLogin() throws Exception {
        this.mockMvc
                .perform(get("/cars/all"))
                .andExpect(redirectedUrl("http://localhost/users/login"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/cars/all"))
                .andExpect(view().name("all-cars"));
    }

    @Test
    @WithMockUser("spring")
    public void all_returnsCorrectAtribute() throws Exception {
        this.mockMvc
                .perform(get("/cars/all"))
                .andExpect(model().attributeExists("cars"));
    }
}
