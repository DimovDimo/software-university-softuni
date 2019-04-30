package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Poll;

public interface PollValidationService {
    boolean isValid(Poll poll);
}
