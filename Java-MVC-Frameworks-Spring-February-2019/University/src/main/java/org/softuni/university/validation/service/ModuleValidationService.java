package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.ModuleServiceModel;

public interface ModuleValidationService {
    boolean isValid(ModuleServiceModel module);
}
