package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Camp;
import org.springframework.stereotype.Component;

@Component
public class CampValidationServiceImpl implements CampValidationService {
    @Override
    public boolean isValid(Camp camp) {
        return camp != null;
    }
}
