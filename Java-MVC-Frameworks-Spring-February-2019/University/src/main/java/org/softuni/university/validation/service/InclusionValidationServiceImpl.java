package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.InclusionServiceModel;

public class InclusionValidationServiceImpl implements InclusionValidationService {
    @Override
    public boolean isValid(InclusionServiceModel inclusion) {
        return inclusion != null;
    }
}
