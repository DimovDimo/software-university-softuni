package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.RoleServiceModel;

public class RoleValidationServiceImpl implements RoleValidationService {
    @Override
    public boolean isValid(RoleServiceModel role) {
        return role != null;
    }
}
