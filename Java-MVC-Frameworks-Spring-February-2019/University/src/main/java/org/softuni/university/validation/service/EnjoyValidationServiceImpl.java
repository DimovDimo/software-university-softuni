package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.EnjoyServiceModel;

public class EnjoyValidationServiceImpl implements EnjoyValidationService {
    @Override
    public boolean isValid(EnjoyServiceModel enjoy) {
        return enjoy != null;
    }
}
