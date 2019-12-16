package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.ContactServiceModel;

public class ContactValidationServiceImpl implements ContactValidationService {
    @Override
    public boolean isValid(ContactServiceModel contact) {
        return contact != null;
    }
}
