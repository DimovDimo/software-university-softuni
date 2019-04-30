package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.InclusionServiceModel;
import org.softuni.university.validation.service.InclusionValidationService;
import org.softuni.university.validation.service.InclusionValidationServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class InclusionValidationServiceTests {
    private InclusionValidationService service;

    @Before
    public void setupTest() {
        service = new InclusionValidationServiceImpl();
    }

    @Test
    public void isValidWithInclusionServiceModel_whenValid_true() {
        InclusionServiceModel inclusion = new InclusionServiceModel();
        boolean result = service.isValid(inclusion);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
