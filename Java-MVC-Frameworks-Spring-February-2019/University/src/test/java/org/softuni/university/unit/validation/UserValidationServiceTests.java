package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.validation.service.UserValidationService;
import org.softuni.university.validation.service.UserValidationServiceImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserValidationServiceTests {
    private UserValidationService service;

    @Before
    public void setupTest() {
        service = new UserValidationServiceImpl();
    }

    @Test
    public void isValidWithUserServiceModel_whenValid_true() {
        UserServiceModel user = new UserServiceModel();
        boolean result = service.isValid(user);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
