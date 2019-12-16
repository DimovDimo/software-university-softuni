package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.QuoteServiceModel;
import org.softuni.university.validation.service.QuoteValidationService;
import org.softuni.university.validation.service.QuoteValidationServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class QuoteValidationServiceTests {
    private QuoteValidationService service;

    @Before
    public void setupTest() {
        service = new QuoteValidationServiceImpl();
    }

    @Test
    public void isValidWithQuoteServiceModel_whenValid_true() {
        QuoteServiceModel quote = new QuoteServiceModel();
        boolean result = service.isValid(quote);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
