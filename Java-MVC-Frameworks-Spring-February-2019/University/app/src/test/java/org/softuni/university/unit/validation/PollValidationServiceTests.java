package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.entities.Poll;
import org.softuni.university.validation.service.PollValidationService;
import org.softuni.university.validation.service.PollValidationServiceImpl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PollValidationServiceTests {
    private PollValidationService service;

    @Before
    public void setupTest() {
        service = new PollValidationServiceImpl();
    }

    @Test
    public void isValidWithPoll_whenValid_true() {
        Poll poll = new Poll();
        boolean result = service.isValid(poll);
        assertTrue(result);
    }

    @Test
    public void isValidWithPoll_whenNull_false() {
        Poll poll = null;
        boolean result = service.isValid(poll);
        assertFalse(result);
    }
}
