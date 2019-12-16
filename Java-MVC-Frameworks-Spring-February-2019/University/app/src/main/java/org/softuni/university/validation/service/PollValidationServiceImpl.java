package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Poll;
import org.springframework.stereotype.Component;

@Component
public class PollValidationServiceImpl implements PollValidationService {
    @Override
    public boolean isValid(Poll poll) {
        return poll != null;
    }
}
