package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Enjoy;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.EnjoyServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.repository.EnjoyRepository;
import org.softuni.university.service.EnjoyService;
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
public class EnjoyServiceTests {
    @Autowired
    EnjoyService service;

    @MockBean
    EnjoyRepository enjoyRepository;

    @MockBean
    UserValidationService mockUserValidation;

    @MockBean
    UserService mockUserService;

    @MockBean
    CourseRepository mockCourseRepository;

    @MockBean
    CourseValidationService courseValidation;

    private List<Enjoy> enjoys;

    @Before
    public void setupTest() {
        enjoys = new ArrayList<>();

        when(enjoyRepository.findAll())
                .thenReturn(enjoys);
    }

    @Test
    public void createEnjoy_whenUserAndCourseAreValid_enjoyCreated() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(true);

        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(true);

        when(mockUserService.findUserByUserName(any()))
                .thenReturn(new UserServiceModel());

        when(mockCourseRepository.findById(any()))
                .thenReturn(java.util.Optional.of(new Course()));

        service.createEnjoy("", "");

        verify(enjoyRepository)
            .save(any());
    }

    @Test(expected = Exception.class)
    public void createEnjoy_whenUserIsValidAndCourseIsNotValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(true);
        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(false);

        service.createEnjoy("", "");
    }

    @Test(expected = Exception.class)
    public void createEnjoy_whenUserIsNotValidAndCourseIsValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(false);
        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(true);

        service.createEnjoy("", "");
    }

    @Test(expected = Exception.class)
    public void createEnjoy_whenUserAndCourseAreNotValid_throw() throws Exception {
        when(mockUserValidation.isValid(any()))
                .thenReturn(false);
        when(courseValidation.isValid(any(Course.class)))
                .thenReturn(false);

        service.createEnjoy("", "");
    }

    @Test
    public void findAllEnjoys_when1Enjoys_return1Enjoys() {
        String student = "Test student";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Enjoy enjoy = new Enjoy();
        enjoy.setUser(new User() {{
            setUsername(student);
        }});

        enjoy.setCourse(new Course() {{
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }});

        enjoys.add(enjoy);

        var result = service.findAllEnjoys();
        EnjoyServiceModel enjoyServiceModel = result.get(0);

        assertEquals(1, result.size());
        assertEquals(student, enjoyServiceModel.getStudent());
        assertEquals(courseName, enjoyServiceModel.getName());
        assertEquals(courseImageUrl, enjoyServiceModel.getImageUrl());
        assertEquals(coursePrice, enjoyServiceModel.getPrice());
    }

    @Test
    public void findAllEnjoys_whenNoEnjoys_returnEmptyEnjoys() {
        enjoys.clear();
        var result = service.findAllEnjoys();
        assertTrue(result.isEmpty());
    }

    @Test
    public void findEnjoysByStudent_whenValidStudent_return1Enjoys() {
        String student = "Test student";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Enjoy enjoy = new Enjoy();
        User user = new User() {{ setUsername(student); }};
        enjoy.setUser(user);

        enjoy.setCourse(new Course() {{
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }});

        enjoys.add(enjoy);

        when(enjoyRepository.findAllByUser_Username(student))
                .thenReturn(enjoys);

        var result = service.findEnjoysByStudent(student);
        EnjoyServiceModel enjoyServiceModel = result.get(0);

        assertEquals(1, result.size());
        assertEquals(student, enjoyServiceModel.getStudent());
        assertEquals(courseName, enjoyServiceModel.getName());
        assertEquals(courseImageUrl, enjoyServiceModel.getImageUrl());
        assertEquals(coursePrice, enjoyServiceModel.getPrice());
    }

    @Test
    public void findEnjoysByStudent_whenNoStudent_returnEmptyEnjoys() {
        String student = "Test student";
        String courseImageUrl = "http://image.url";
        String courseName = "course 1";
        BigDecimal coursePrice = BigDecimal.valueOf(1.34);

        Enjoy enjoy = new Enjoy();
        enjoy.setUser(new User() {{
            setUsername(student);
        }});

        enjoy.setCourse(new Course() {{
            setImageUrl(courseImageUrl);
            setName(courseName);
            setPrice(coursePrice);
        }});

        enjoys.add(enjoy);

        var result = service.findEnjoysByStudent(student);
        assertTrue(result.isEmpty());
    }

    @Test
    public void findEnjoysByStudent_whenNoNull_returnEmptyEnjoys() {
        var result = service.findEnjoysByStudent(null);
        assertTrue(result.isEmpty());
    }
}
