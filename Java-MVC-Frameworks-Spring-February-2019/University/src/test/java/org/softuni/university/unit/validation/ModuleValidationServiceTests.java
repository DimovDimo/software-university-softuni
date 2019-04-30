package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.validation.service.ModuleValidationService;
import org.softuni.university.validation.service.ModuleValidationServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ModuleValidationServiceTests {
    private ModuleValidationService service;

    @Before
    public void setupTest() {
        service = new ModuleValidationServiceImpl();
    }

    @Test
    public void isValidWithModuleServiceModel_whenValid_true() {
        ModuleServiceModel module = new ModuleServiceModel();
        boolean result = service.isValid(module);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
