package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.SelectedQuoteServiceModel;
import org.softuni.university.validation.service.SelectedQuoteValidationService;
import org.softuni.university.validation.service.SelectedQuoteValidationServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class SelectedQuoteValidationServiceTests {
    private SelectedQuoteValidationService service;

    @Before
    public void setupTest() {
        service = new SelectedQuoteValidationServiceImpl();
    }

    @Test
    public void isValidWithSelectedQuoteServiceModel_whenValid_true() {
        SelectedQuoteServiceModel quote = new SelectedQuoteServiceModel();
        boolean result = service.isValid(quote);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
