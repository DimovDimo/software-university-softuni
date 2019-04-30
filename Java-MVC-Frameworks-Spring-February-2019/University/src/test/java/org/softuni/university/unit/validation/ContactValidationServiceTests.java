package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.validation.service.ContactValidationService;
import org.softuni.university.validation.service.ContactValidationServiceImpl;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ContactValidationServiceTests {
    private ContactValidationService service;

    @Before
    public void setupTest() {
        service = new ContactValidationServiceImpl();
    }

    @Test
    public void isValidWithInclusionServiceModel_whenValid_true() {
        ContactServiceModel contact = new ContactServiceModel();
        boolean result = service.isValid(contact);
        assertTrue(result);
    }

    @Test
    public void isValid_whenNull_false() {
        boolean result = service.isValid(null);
        assertFalse(result);
    }
}
