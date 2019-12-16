package org.softuni.university.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Module;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.validation.service.CourseValidationService;
import org.softuni.university.validation.service.CourseValidationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseValidationServiceTests {
    private CourseValidationService service;

    @Before
    public void setupTest() {
        service = new CourseValidationServiceImpl();
    }

    @Test
    public void isValidWithCourse_whenValid_true() {
        Course course = new Course();
        course.setModules(List.of(new Module()));
        boolean result = service.isValid(course);
        assertTrue(result);
    }

    @Test
    public void isValidWithCourse_whenNull_false() {
        Course course = null;
        boolean result = service.isValid(course);
        assertFalse(result);
    }

    @Test
    public void isValidWithCourseServiceModel_whenNull_false() {
        CourseServiceModel course = null;
        boolean result = service.isValid(course);
        assertFalse(result);
    }

    @Test
    public void isValidWithCourseServiceModel_whenValid_true() {
        CourseServiceModel course = new CourseServiceModel();
        course.setModules(List.of(new ModuleServiceModel()));
        boolean result = service.isValid(course);
        assertTrue(result);
    }

    @Test
    public void isValidWithCourseServiceModel_whenSetModulesNull_false() {
        CourseServiceModel course = new CourseServiceModel();
        course.setModules(null);

        boolean result = service.isValid(course);
        assertFalse(result);
    }


    @Test
    public void isValidWithCourseServiceModel_whenSetModulesEmpty_false() {
        CourseServiceModel course = new CourseServiceModel();
        course.setModules(new ArrayList<>());

        boolean result = service.isValid(course);
        assertFalse(result);
    }
}
