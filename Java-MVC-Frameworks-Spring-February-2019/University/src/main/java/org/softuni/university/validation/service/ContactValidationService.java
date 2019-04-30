package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.ContactServiceModel;

public interface ContactValidationService {
    boolean isValid(ContactServiceModel contact);
}
