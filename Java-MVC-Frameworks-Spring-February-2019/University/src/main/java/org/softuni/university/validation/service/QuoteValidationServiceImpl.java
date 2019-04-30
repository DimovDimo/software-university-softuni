package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.QuoteServiceModel;

public class QuoteValidationServiceImpl implements QuoteValidationService {
    @Override
    public boolean isValid(QuoteServiceModel quote) {
        return quote != null;
    }
}
