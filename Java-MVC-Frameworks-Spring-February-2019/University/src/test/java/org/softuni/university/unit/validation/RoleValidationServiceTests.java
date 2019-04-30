package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.RoleServiceModel;
import org.softuni.university.validation.service.RoleValidationService;
import org.softuni.university.validation.service.RoleValidationServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RoleValidationServiceTests {
    private RoleValidationService service;

    @Before
    public void setupTest() {
        service = new RoleValidationServiceImpl();
    }

    @Test
    public void isValidWithRoleServiceModel_whenValid_true() {
        RoleServiceModel role = new RoleServiceModel();
        boolean result = service.isValid(role);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
