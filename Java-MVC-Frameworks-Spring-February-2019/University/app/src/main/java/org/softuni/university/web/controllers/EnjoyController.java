package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ControllerConstants;
import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.domain.models.view.CourseDetailsViewModel;
import org.softuni.university.domain.models.view.EnjoyViewModel;
import org.softuni.university.error.EnjoyDoNotCreateException;
import org.softuni.university.service.CourseService;
import org.softuni.university.service.EnjoyService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/enjoy")
public class EnjoyController extends BaseController {

    private final CourseService courseService;
    private final EnjoyService enjoyService;
    private final ModelMapper mapper;

    public EnjoyController(
        CourseService courseService,
        EnjoyService enjoyService,
        ModelMapper modelMapper
    ){
        this.courseService = courseService;
        this.enjoyService = enjoyService;
        this.mapper = modelMapper;
    }

    @GetMapping("/course/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Enjoy course")
    public ModelAndView enjoyCourse(@PathVariable String id, ModelAndView modelAndView) {
        CourseServiceModel serviceModel = courseService.findCourseById(id);
        CourseDetailsViewModel viewModel = mapper.map(serviceModel, CourseDetailsViewModel.class);
        modelAndView.addObject(ControllerConstants.COURSE, viewModel);

        return view("enjoy/course", modelAndView);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("All enjoys")
    public ModelAndView getAllEnjoys(ModelAndView modelAndView) {
        List<EnjoyViewModel> viewModels = findAllEnjoys();
        modelAndView.addObject(ControllerConstants.ENJOYS, viewModels);

        return view("enjoy/list-enjoys", modelAndView);
    }

    @GetMapping("/strudent")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Student enjoys")
    public ModelAndView getStudentEnjoys(ModelAndView modelAndView, Principal principal) {
        List<EnjoyViewModel> viewModels = findEnjoysByStudent(principal);
        modelAndView.addObject(ControllerConstants.ENJOYS, viewModels);

        return view("enjoy/list-enjoys", modelAndView);
    }

    @ExceptionHandler({EnjoyDoNotCreateException.class})
    public ModelAndView handleEnjoyDoNotCreate(EnjoyDoNotCreateException e) {
        ModelAndView modelAndView = new ModelAndView(ControllerConstants.VIEW_ERROR);
        modelAndView.addObject(ControllerConstants.MESSAGE, e.getMessage());
        modelAndView.addObject(ControllerConstants.STATUS_CODE, e.getStatusCode());


        return modelAndView;
    }

    private List<EnjoyViewModel> findEnjoysByStudent(Principal principal) {
        String username = principal.getName();
        return enjoyService.findEnjoysByStudent(username)
                .stream()
                .map(o -> mapper.map(o, EnjoyViewModel.class))
                .collect(Collectors.toList());
    }

    private List<EnjoyViewModel> findAllEnjoys() {
        return enjoyService.findAllEnjoys()
                .stream()
                .map(o -> mapper.map(o, EnjoyViewModel.class))
                .collect(Collectors.toList());
    }
}
