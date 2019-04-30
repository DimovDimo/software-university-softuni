package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.SelectedQuoteServiceModel;

public class SelectedQuoteValidationServiceImpl implements SelectedQuoteValidationService {
    @Override
    public boolean isValid(SelectedQuoteServiceModel quote) {
        return quote != null;
    }
}
