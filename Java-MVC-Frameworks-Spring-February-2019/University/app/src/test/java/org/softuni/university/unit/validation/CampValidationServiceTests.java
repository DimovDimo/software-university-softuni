package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.entities.Camp;
import org.softuni.university.validation.service.CampValidationService;
import org.softuni.university.validation.service.CampValidationServiceImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CampValidationServiceTests {
    private CampValidationService service;

    @Before
    public void setupTest() {
        service = new CampValidationServiceImpl();
    }

    @Test
    public void isValidWithCamp_whenValid_true() {
        Camp camp = new Camp();
        boolean result = service.isValid(camp);
        assertTrue(result);
    }

    @Test
    public void isValidWithCamp_whenNull_false() {
        Camp camp = null;
        boolean result = service.isValid(camp);
        assertFalse(result);
    }
}
