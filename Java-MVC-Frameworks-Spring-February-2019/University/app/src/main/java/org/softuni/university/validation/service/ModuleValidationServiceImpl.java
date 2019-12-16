package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.ModuleServiceModel;

public class ModuleValidationServiceImpl implements ModuleValidationService {
    @Override
    public boolean isValid(ModuleServiceModel module) {
        return module != null;
    }
}
