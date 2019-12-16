package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.EnjoyServiceModel;
import org.softuni.university.validation.service.EnjoyValidationService;
import org.softuni.university.validation.service.EnjoyValidationServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class EnjoyValidationServiceTests {
    private EnjoyValidationService service;

    @Before
    public void setupTest() {
        service = new EnjoyValidationServiceImpl();
    }

    @Test
    public void isValidWithEnjoyServiceModel_whenValid_true() {
        EnjoyServiceModel enjoy = new EnjoyServiceModel();
        boolean result = service.isValid(enjoy);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
