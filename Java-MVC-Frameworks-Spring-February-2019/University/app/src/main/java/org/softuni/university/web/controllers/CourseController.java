package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ControllerConstants;
import org.softuni.university.domain.models.binding.CourseAddBindingModel;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.domain.models.view.CourseAllViewModel;
import org.softuni.university.domain.models.view.CourseDetailsViewModel;
import org.softuni.university.error.CourseDoNotCreateException;
import org.softuni.university.error.CourseNameAlreadyExistsException;
import org.softuni.university.error.CourseNotFoundException;
import org.softuni.university.service.ModuleService;
import org.softuni.university.service.CloudinaryService;
import org.softuni.university.service.CourseService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final CloudinaryService cloudinaryService;
    private final ModuleService moduleService;
    private final ModelMapper modelMapper;

    @Autowired
    public CourseController(
            CourseService courseService,
            CloudinaryService cloudinaryService,
            ModuleService moduleService,
            ModelMapper modelMapper
    ) {
        this.courseService = courseService;
        this.cloudinaryService = cloudinaryService;
        this.moduleService = moduleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    @PageTitle("Add course")
    public ModelAndView addCourse() {
        return super.view("course/add-course");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView addCourseConfirm(@ModelAttribute CourseAddBindingModel model) throws IOException {
        CourseServiceModel courseServiceModel = this.modelMapper.map(model, CourseServiceModel.class);
        addCourseeSetModules(model, courseServiceModel);
        addCourseeSetImageUrl(model, courseServiceModel);
        this.courseService.createCourse(courseServiceModel);

        return super.redirect("/courses/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    @PageTitle("All courses")
    public ModelAndView allCourses(ModelAndView modelAndView) {
        allCoursesGet(modelAndView);

        return super.view("course/all-courses", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Details course")
    public ModelAndView detailsCourse(@PathVariable String id, ModelAndView modelAndView) {
        findCourseDetailsById(id, modelAndView);

        return super.view("course/details-course", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    @PageTitle("Edit course")
    public ModelAndView editCourse(@PathVariable String id, ModelAndView modelAndView) {
        CourseServiceModel courseServiceModel = this.courseService.findCourseById(id);
        CourseAddBindingModel model = this.modelMapper.map(courseServiceModel, CourseAddBindingModel.class);
        editCourseSetModules(courseServiceModel, model);

        modelAndView.addObject(ControllerConstants.COURSE, model);
        modelAndView.addObject(ControllerConstants.COURSE_ID, id);

        return super.view("course/edit-course", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView editCourseConfirm(@PathVariable String id, @ModelAttribute CourseAddBindingModel model) {
        this.courseService.editCourse(id, this.modelMapper.map(model, CourseServiceModel.class));

        return super.redirect("/courses/details/" + id);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    @PageTitle("Delete course")
    public ModelAndView deleteCourse(@PathVariable String id, ModelAndView modelAndView) {
        CourseServiceModel courseServiceModel = this.courseService.findCourseById(id);
        CourseAddBindingModel model = this.modelMapper.map(courseServiceModel, CourseAddBindingModel.class);
        deleteCourseSetModules(courseServiceModel, model);

        modelAndView.addObject(ControllerConstants.COURSE, model);
        modelAndView.addObject(ControllerConstants.COURSE_ID, id);

        return super.view("course/delete-course", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_CHAIR_OF_A_DEPARTMENT')")
    public ModelAndView deleteCourseConfirm(@PathVariable String id) {
        this.courseService.deleteCourse(id);

        return super.redirect("/courses/all");
    }

    @GetMapping("/fetch/{module}")
    @ResponseBody
    public List<CourseAllViewModel> fetchByModule(@PathVariable String module) {
        if(module.equals(ControllerConstants.FETCH_ALL)) {
            return findAllCourses();
        }

        return findAllByModule(module);
    }

    @ExceptionHandler({CourseNotFoundException.class})
    public ModelAndView handleCourseNotFound(CourseNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView(ControllerConstants.VIEW_ERROR);
        modelAndView.addObject(ControllerConstants.MESSAGE, e.getMessage());
        modelAndView.addObject(ControllerConstants.STATUS_CODE, e.getStatusCode());

        return modelAndView;
    }

    @ExceptionHandler({CourseNameAlreadyExistsException.class})
    public ModelAndView handleCourseNameALreadyExist(CourseNameAlreadyExistsException e) {
        ModelAndView modelAndView = new ModelAndView(ControllerConstants.VIEW_ERROR);
        modelAndView.addObject(ControllerConstants.MESSAGE, e.getMessage());
        modelAndView.addObject(ControllerConstants.STATUS_CODE, e.getStatusCode());

        return modelAndView;
    }

    @ExceptionHandler({CourseDoNotCreateException.class})
    public ModelAndView handleCourseNotDoNotCreate(CourseDoNotCreateException e) {
        ModelAndView modelAndView = new ModelAndView(ControllerConstants.VIEW_ERROR);
        modelAndView.addObject(ControllerConstants.MESSAGE, e.getMessage());
        modelAndView.addObject(ControllerConstants.STATUS_CODE, e.getStatusCode());

        return modelAndView;
    }

    private List<CourseAllViewModel> findAllCourses() {
        return this.courseService.findAllCourses()
                .stream()
                .map(courseServiceModel -> this.modelMapper.map(courseServiceModel, CourseAllViewModel.class))
                .collect(Collectors.toList());
    }

    private List<CourseAllViewModel> findAllByModule(@PathVariable String module) {
        return this.courseService.findAllByModule(module)
                .stream()
                .map(courseServiceModel -> this.modelMapper.map(courseServiceModel, CourseAllViewModel.class))
                .collect(Collectors.toList());
    }

    private void deleteCourseSetModules(CourseServiceModel courseServiceModel, CourseAddBindingModel model) {
        model.setModules(
                courseServiceModel.getModules()
                        .stream()
                        .map(c -> c.getName())
                        .collect(Collectors.toList())
        );
    }

    private void editCourseSetModules(CourseServiceModel courseServiceModel, CourseAddBindingModel model) {
        model.setModules(
                courseServiceModel.getModules()
                        .stream().map(c -> c.getName())
                        .collect(Collectors.toList())
        );
    }

    private void allCoursesGet(ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.COURSES, this.courseService.findAllCourses()
                .stream()
                .map(courseServiceModel -> this.modelMapper.map(courseServiceModel, CourseAllViewModel.class))
                .collect(Collectors.toList()));
    }

    private void addCourseeSetImageUrl(@ModelAttribute CourseAddBindingModel model, CourseServiceModel courseServiceModel) throws IOException {
        courseServiceModel.setImageUrl(
                this.cloudinaryService.uploadImage(model.getImage())
        );
    }

    private void addCourseeSetModules(@ModelAttribute CourseAddBindingModel model, CourseServiceModel courseServiceModel) {
        courseServiceModel.setModules(
                this.moduleService.findAllModules()
                        .stream()
                        .filter(c -> model.getModules().contains(c.getId()))
                        .collect(Collectors.toList())
        );
    }



    private void findCourseDetailsById(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.COURSE,
                this.modelMapper.map(
                        this.courseService.findCourseById(id),
                        CourseDetailsViewModel.class
                ));
    }
}
