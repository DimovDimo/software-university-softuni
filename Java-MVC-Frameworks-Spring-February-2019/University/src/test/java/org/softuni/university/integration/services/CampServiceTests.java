package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Camp;
import org.softuni.university.domain.models.service.CampServiceModel;
import org.softuni.university.repository.CampRepository;
import org.softuni.university.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CampServiceTests {
    @Autowired
    private CampService service;

    @MockBean
    private CampRepository campRepository;

    private List<Camp> camps;

    @Before
    public void setupTest() {
        camps = new ArrayList<>();
    }

    @Test(expected = Exception.class)
    public void createCamp_whenNull_throw() throws Exception {
        CampServiceModel campServiceModel = new CampServiceModel();
        campServiceModel.setBeds(2);
        campServiceModel.setNights(2);
        campServiceModel.setParkingLots(2);
        campServiceModel.setPlace("test");

        service.createCamp(campServiceModel, "test");

        assertEquals(1, campRepository.findAll().size());
    }

    @Test(expected = Exception.class)
    public void createCamp_whenNameNull_throw() throws Exception {
        CampServiceModel campServiceModel = new CampServiceModel();
        campServiceModel.setBeds(2);
        campServiceModel.setNights(2);
        campServiceModel.setParkingLots(2);
        campServiceModel.setPlace("test");

        service.createCamp(campServiceModel, null);

        assertEquals(1, campRepository.findAll().size());
    }

    @Test(expected = Exception.class)
    public void createCamp_whenCampServiceModelNull_throw() throws Exception {
        service.createCamp(null, "test");
    }

    @Test
    public void findAllCamps_whenEmpty_returnEmpty() throws Exception {
        when(campRepository.findAll())
                .thenReturn(camps);

        var result = service.findAllCamps();

        assertEquals(0, result.size());
    }

    @Test(expected = Exception.class)
    public void findCampById_whenNull_throw() throws Exception {
        when(campRepository.findAll())
                .thenReturn(camps);

        var result = service.findCampById("Empty");

        assertNull(result);
    }

    @Test(expected = Exception.class)
    public void findCampById_when1Camp_return1Camp() throws Exception {
        Camp camp = new Camp();
        camp.setId("Id");
        camps.add(camp);

        when(campRepository.findAll())
                .thenReturn(camps);

        var result = service.findCampById("Id");

        assertNotNull(result);
    }
}
