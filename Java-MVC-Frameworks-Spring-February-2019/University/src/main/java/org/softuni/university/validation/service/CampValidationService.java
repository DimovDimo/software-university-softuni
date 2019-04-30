package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Camp;

public interface CampValidationService {
    boolean isValid(Camp camp);
}
