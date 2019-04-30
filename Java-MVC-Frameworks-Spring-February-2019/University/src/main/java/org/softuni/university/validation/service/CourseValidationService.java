package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.models.service.CourseServiceModel;

public interface CourseValidationService {
    boolean isValid(Course course);

    boolean isValid(CourseServiceModel course);
}
