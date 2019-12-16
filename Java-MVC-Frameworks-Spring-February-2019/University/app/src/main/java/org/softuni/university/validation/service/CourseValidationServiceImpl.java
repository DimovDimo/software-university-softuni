package org.softuni.university.validation.service;

import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseValidationServiceImpl implements CourseValidationService {
    @Override
    public boolean isValid(Course course) {
        return course != null;
    }

    @Override
    public boolean isValid(CourseServiceModel course) {
        return course != null
                && areModulesValid(course.getModules());
    }

    private boolean areModulesValid(List<ModuleServiceModel> modules) {
        return modules != null && !modules.isEmpty();
    }
}
