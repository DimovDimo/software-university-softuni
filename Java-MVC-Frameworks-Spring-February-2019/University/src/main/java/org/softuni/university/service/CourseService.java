package org.softuni.university.service;

import org.softuni.university.domain.models.service.CourseServiceModel;

import java.util.List;

public interface CourseService {

    CourseServiceModel createCourse(CourseServiceModel courseServiceModel);

    List<CourseServiceModel> findAllCourses();

    CourseServiceModel findCourseById(String id);

    CourseServiceModel editCourse(String id, CourseServiceModel courseServiceModel);

    void deleteCourse(String id);

    List<CourseServiceModel> findAllByModule(String module);
}
