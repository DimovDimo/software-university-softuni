package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ServiceConstants;
import org.softuni.university.domain.entities.Course;
import org.softuni.university.domain.entities.Module;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.error.CourseDoNotCreateException;
import org.softuni.university.error.CourseNameAlreadyExistsException;
import org.softuni.university.error.CourseNotFoundException;
import org.softuni.university.repository.CourseRepository;
import org.softuni.university.validation.service.CourseValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleService moduleService;
    private final CourseValidationService courseValidation;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseServiceImpl(
            CourseRepository courseRepository,
            ModuleService moduleService,
            CourseValidationService courseValidation,
            ModelMapper modelMapper
    ) {
        this.courseRepository = courseRepository;
        this.moduleService = moduleService;
        this.courseValidation = courseValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseServiceModel createCourse(CourseServiceModel courseServiceModel) {
        if (!courseValidation.isValid(courseServiceModel)) {
            throw new CourseDoNotCreateException(ServiceConstants.DO_NOT_CREATE_EXCEPTION_THIS_INPUT_IS_INVALID);
        }
        Course course = this.courseRepository
                .findByName(courseServiceModel.getName())
                .orElse(null);

        if (course != null) {
            throw new CourseNameAlreadyExistsException(ServiceConstants.NAME_ALREADY_EXISTS);
        }

        course = this.modelMapper.map(courseServiceModel, Course.class);
        course = this.courseRepository.save(course);

        return this.modelMapper.map(course, CourseServiceModel.class);
    }

    @Override
    public List<CourseServiceModel> findAllCourses() {
        return this.courseRepository.findAll()
                .stream()
                .map(course -> this.modelMapper.map(course, CourseServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseServiceModel findCourseById(String id) {
        return this.courseRepository.findById(id)
                .map(course -> this.modelMapper.map(course, CourseServiceModel.class))
                .orElseThrow(() -> new CourseNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));
    }

    @Override
    public CourseServiceModel editCourse(String id, CourseServiceModel courseServiceModel) {
        Course course = this.courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));

        courseServiceModelSetModules(courseServiceModel);
        courseSets(courseServiceModel, course);

        return this.modelMapper.map(this.courseRepository.saveAndFlush(course), CourseServiceModel.class);
    }

    @Override
    public void deleteCourse(String id) {
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));

        this.courseRepository.delete(course);
    }

    @Override
    public List<CourseServiceModel> findAllByModule(String module) {
        return this.courseRepository.findAll()
                .stream()
                .filter(course -> course.getModules().stream().anyMatch(moduleStream -> moduleStream.getName().equals(module)))
                .map(course -> this.modelMapper.map(course, CourseServiceModel.class))
                .collect(Collectors.toList());
    }

    private void courseSets(CourseServiceModel courseServiceModel, Course course) {
        course.setName(courseServiceModel.getName());
        course.setDescription(courseServiceModel.getDescription());
        course.setPrice(courseServiceModel.getPrice());
        course.setModules(
                courseServiceModel.getModules()
                        .stream()
                        .map(c -> this.modelMapper.map(c, Module.class))
                        .collect(Collectors.toList())
        );
    }

    private void courseServiceModelSetModules(CourseServiceModel courseServiceModel) {
        courseServiceModel.setModules(
                this.moduleService.findAllModules()
                        .stream()
                        .filter(c -> courseServiceModel.getModules().contains(c.getId()))
                        .collect(Collectors.toList())
        );
    }
}
