package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Module;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CourseServiceTests {
    @Autowired
    private CourseService service;

    @MockBean
    private CourseRepository mockCourseRepository;

    @MockBean
    private CourseServiceModel mockCourseServiceModel;

    private List<Course> courses;

    @Before
    public void setupTest() {
        courses = new ArrayList<>();

        when(mockCourseRepository.findAll())
                .thenReturn(courses);
    }

    @Test
    public void createCourse_whenValid_courseCreated() {
        CourseServiceModel course = new CourseServiceModel();
        course.setModules(List.of(new ModuleServiceModel()));
        when(mockCourseRepository.save(any()))
                .thenReturn(new Course());

        service.createCourse(course);
        verify(mockCourseRepository)
              .save(any());
    }

    @Test(expected = Exception.class)
    public void createCourse_whenNull_throw() {
        service.createCourse(null);
        verify(mockCourseRepository)
                .save(any());
    }

    @Test
    public void findAllCourses_when1Courses_return1Courses() {
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Course course = new Course() {{
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }};

        courses.add(course);

        var result = service.findAllCourses();
        CourseServiceModel courseResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(courseName, courseResult.getName());
        assertEquals(courseImageUrl, courseResult.getImageUrl());
        assertEquals(coursePrice, courseResult.getPrice());
    }

    @Test
    public void findAllCourses_whenNoCourses_returnEmptyCourses() {
        courses.clear();
        var result = service.findAllCourses();
        assertTrue(result.isEmpty());
    }

    @Test
    public void findCourseById_when1Courses_return1Courses() {
        String courseId = "course Id";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Course course = new Course() {{
            setId(courseId);
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }};

        courses.add(course);

        when(mockCourseRepository.findById(courseId))
                .thenReturn(Optional.of(course));

        var result = service.findAllCourses();
        CourseServiceModel courseResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(courseName, courseResult.getName());
        assertEquals(courseImageUrl, courseResult.getImageUrl());
        assertEquals(coursePrice, courseResult.getPrice());
    }

    @Test(expected = Exception.class)
    public void findCourseById_whenNoCourses_ThrowException() {
        var result = service.findCourseById("Empty");
    }

    @Test(expected = Exception.class)
    public void editCourse_whenNoModules_ThrowException() {
        String courseId = "course Id";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Course course = new Course() {{
            setId(courseId);
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }};

        courses.add(course);

        when(mockCourseRepository.findById(courseId))
                .thenReturn(Optional.of(course));

        var result = service.editCourse(courseId, mockCourseServiceModel);
    }

    @Test
    public void deleteCourse_when1Courses_return0Courses() {
        String courseId = "course Id";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Course course = new Course() {{
            setId(courseId);
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }};

        courses.add(course);

        when(mockCourseRepository.findById(courseId))
                .thenReturn(Optional.of(course));

        service.deleteCourse(courseId);
        courses.clear();

        var result = service.findAllCourses();

        assertTrue(result.isEmpty());
    }

    @Test(expected = Exception.class)
    public void deleteCourse_whenNoCourses_ThrowException() {
        service.deleteCourse("Empty");
    }

    @Test
    public void findAllByModule_when1ModuleNames_return1Courses() {
        String courseId = "course Id";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);
        String moduleName = "Module Name";

        Module module = new Module(){{setName(moduleName);}};

        Course course = new Course() {{
            setId(courseId);
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
            setModules(List.of(module));
        }};

        courses.add(course);

        when(mockCourseRepository.findById(courseId))
                .thenReturn(Optional.of(course));

        var result = service.findAllByModule(moduleName);
        CourseServiceModel courseResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(courseName, courseResult.getName());
        assertEquals(courseImageUrl, courseResult.getImageUrl());
        assertEquals(coursePrice, courseResult.getPrice());
    }

    @Test
    public void findAllByModule_whenNoModuleNames_returnEmptyCourses() {
        courses.clear();
        String moduleName = "Module Name";
        var result = service.findAllByModule(moduleName);
        assertTrue(result.isEmpty());
    }
}
