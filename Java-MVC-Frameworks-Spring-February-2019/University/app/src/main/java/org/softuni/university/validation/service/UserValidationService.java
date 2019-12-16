package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}
