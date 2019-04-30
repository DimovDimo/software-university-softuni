package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.QuoteServiceModel;

public interface QuoteValidationService {
    boolean isValid(QuoteServiceModel quote);
}
