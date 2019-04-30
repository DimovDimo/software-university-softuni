package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Inclusion;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.InclusionServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.repository.InclusionRepository;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.service.InclusionService;
import org.softuni.university.service.UserService;
import org.softuni.university.validation.service.CourseValidationService;
import org.softuni.university.validation.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class InclusionServiceTests {
    @Autowired
    InclusionService service;

    @MockBean
    InclusionRepository mockInclusionRepository;

    @MockBean
    UserValidationService mockUserValidation;

    @MockBean
    UserService mockUserService;

    @MockBean
    CourseRepository mockCourseRepository;

    @MockBean
    CourseValidationService courseValidation;

    private List<Inclusion> inclusions;

    @Before
    public void setupTest() {
        inclusions = new ArrayList<>();

        when(mockInclusionRepository.findAll())
                .thenReturn(inclusions);
    }

    @Test
    public void createInclusion_whenUserAndCourseAreValid_inclusionCreated() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(true);

        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(true);

        when(mockUserService.findUserByUserName(any()))
                .thenReturn(new UserServiceModel());

        when(mockCourseRepository.findById(any()))
                .thenReturn(java.util.Optional.of(new Course()));

        service.createInclusion("", "");

        verify(mockInclusionRepository)
            .save(any());
    }

    @Test(expected = Exception.class)
    public void createInclusion_whenUserIsValidAndCourseIsNotValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(true);
        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(false);

        service.createInclusion("", "");
    }

    @Test(expected = Exception.class)
    public void createInclusion_whenUserIsNotValidAndCourseIsValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(false);
        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(true);

        service.createInclusion("", "");
    }

    @Test(expected = Exception.class)
    public void createInclusion_whenUserAndCourseAreNotValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(false);
        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(false);

        service.createInclusion("", "");
    }

    @Test
    public void findAllInclusions_when1Inclusions_return1Inclusions() {
        String student = "Test student";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Inclusion inclusion = new Inclusion();
        inclusion.setUser(new User() {{
            setUsername(student);
        }});

        inclusion.setCourse(new Course() {{
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }});

        inclusions.add(inclusion);

        var result = service.findAllInclusions();
        InclusionServiceModel inclusionResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(student, inclusionResult.getStudent());
        assertEquals(courseName, inclusionResult.getName());
        assertEquals(courseImageUrl, inclusionResult.getImageUrl());
        assertEquals(coursePrice, inclusionResult.getPrice());
    }

    @Test
    public void findAllInclusions_whenNoInclusions_returnEmptyInclusions() {
        inclusions.clear();
        var result = service.findAllInclusions();
        assertTrue(result.isEmpty());
    }

    @Test
    public void findInclusionsByStudent_whenValidStudent_return1Inclusions() {
        String student = "Test student";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Inclusion inclusion = new Inclusion();
        User user = new User() {{ setUsername(student); }};
        inclusion.setUser(user);

        inclusion.setCourse(new Course() {{
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }});

        inclusions.add(inclusion);

        when(mockInclusionRepository.findAllByUser_Username(student))
                .thenReturn(inclusions);

        var result = service.findInclusionsByStudent(student);
        InclusionServiceModel inclusionResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(student, inclusionResult.getStudent());
        assertEquals(courseName, inclusionResult.getName());
        assertEquals(courseImageUrl, inclusionResult.getImageUrl());
        assertEquals(coursePrice, inclusionResult.getPrice());
    }

    @Test
    public void findInclusionsByStudent_whenNoStudent_returnEmptyInclusions() {
        String student = "Test student";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Inclusion inclusion = new Inclusion();
        inclusion.setUser(new User() {{
            setUsername(student);
        }});

        inclusion.setCourse(new Course() {{
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }});

        inclusions.add(inclusion);

        var result = service.findInclusionsByStudent(student);
        assertTrue(result.isEmpty());
    }

    @Test
    public void findInclusionsByStudent_whenNoNull_returnEmptyInclusions() {
        var result = service.findInclusionsByStudent(null);
        assertTrue(result.isEmpty());
    }
}
