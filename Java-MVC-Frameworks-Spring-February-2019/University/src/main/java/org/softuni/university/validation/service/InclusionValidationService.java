package org.softuni.university.validation.service;

import org.softuni.university.domain.models.service.InclusionServiceModel;

public interface InclusionValidationService {
    boolean isValid(InclusionServiceModel inclusion);
}
