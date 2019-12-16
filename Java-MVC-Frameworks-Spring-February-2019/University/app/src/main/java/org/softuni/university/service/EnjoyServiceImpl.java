package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ServiceConstants;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Enjoy;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.EnjoyServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.error.CourseNotFoundException;
import org.softuni.university.error.EnjoyDoNotCreateException;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.repository.EnjoyRepository;
import org.softuni.university.validation.service.CourseValidationService;
import org.softuni.university.validation.service.UserValidationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnjoyServiceImpl implements EnjoyService {

    private final EnjoyRepository enjoyRepository;
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;
    private final CourseValidationService courseValidation;

    public EnjoyServiceImpl(
            EnjoyRepository enjoyRepository,
            CourseRepository courseRepository,
            UserService userService,
            ModelMapper mapper,
            UserValidationService userValidation,
            CourseValidationService courseValidation
    ) {
        this.enjoyRepository = enjoyRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.userValidation = userValidation;
        this.courseValidation = courseValidation;
    }


    @Override
    public void createEnjoy(String courseId, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new EnjoyDoNotCreateException(ServiceConstants.DO_NOT_CREATE_EXCEPTION_THIS_INPUT_IS_INVALID);
        }

        Course course = courseRepository.findById(courseId)
                .filter(courseValidation::isValid)
                .orElseThrow(() -> new CourseNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));

        User user = new User();
        user.setId(userModel.getId());
        Enjoy enjoy = new Enjoy();
        enjoy.setCourse(course);
        enjoy.setUser(user);

        enjoyRepository.save(enjoy);
    }

    @Override
    public List<EnjoyServiceModel> findAllEnjoys() {
        return enjoyRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, EnjoyServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EnjoyServiceModel> findEnjoysByStudent(String username) {
        return enjoyRepository.findAllByUser_Username(username)
                .stream()
                .map(o -> mapper.map(o, EnjoyServiceModel.class))
                .collect(Collectors.toList());
    }
}
