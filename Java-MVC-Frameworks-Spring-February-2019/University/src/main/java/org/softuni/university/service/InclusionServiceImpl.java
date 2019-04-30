package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Inclusion;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.InclusionServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.error.CourseNotFoundException;
import org.softuni.university.repository.InclusionRepository;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.validation.service.CourseValidationService;
import org.softuni.university.validation.service.UserValidationService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InclusionServiceImpl implements InclusionService {

    private final InclusionRepository inclusionRepository;
    private final CourseRepository courseRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;
    private final CourseValidationService courseValidation;

    public InclusionServiceImpl(
            InclusionRepository inclusionRepository,
            CourseRepository courseRepository,
            UserService userService,
            UserValidationService userValidation,
            CourseValidationService courseValidation,
            ModelMapper mapper
    ) {
        this.inclusionRepository = inclusionRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
        this.userValidation = userValidation;
        this.courseValidation = courseValidation;
        this.mapper = mapper;
    }

    @Override
    public void createInclusion(String courseId, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new UsernameNotFoundException("Username not found!");
        }

        Course course = courseRepository.findById(courseId)
                .filter(courseValidation::isValid)
                .orElseThrow(() -> new CourseNotFoundException("CourseNotFoundException with the given id was not found!"));

        User user = new User();
        user.setId(userModel.getId());
        Inclusion inclusion = new Inclusion();
        inclusion.setCourse(course);
        inclusion.setUser(user);

        inclusionRepository.save(inclusion);
    }

    @Override
    public List<InclusionServiceModel> findAllInclusions() {
        return inclusionRepository.findAll()
                .stream()
                .map(o -> mapper.map(o, InclusionServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InclusionServiceModel> findInclusionsByStudent(String username) {
        return inclusionRepository.findAllByUser_Username(username)
                .stream()
                .map(o -> mapper.map(o, InclusionServiceModel.class))
                .collect(Collectors.toList());
    }
}
