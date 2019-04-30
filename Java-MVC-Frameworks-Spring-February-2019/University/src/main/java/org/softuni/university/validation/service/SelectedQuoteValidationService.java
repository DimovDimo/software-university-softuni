package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.SelectedQuoteServiceModel;

public interface SelectedQuoteValidationService {
    boolean isValid(SelectedQuoteServiceModel quote);
}
