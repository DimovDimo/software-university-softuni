package org.softuni.university.web.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.softuni.university.domain.models.view.InclusionViewModel;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.modelmapper.ModelMapper;

import org.softuni.university.domain.models.service.CourseServiceModel;
import org.softuni.university.domain.models.view.CourseDetailsViewModel;
import org.softuni.university.service.InclusionService;
import org.softuni.university.service.CourseService;

@Controller
@RequestMapping("/inclusion")
public class InclusionsController extends BaseController {

    private final CourseService courseService;
    private final InclusionService inclusionService;
    private final ModelMapper mapper;

    public InclusionsController(
        CourseService courseService,
        InclusionService inclusionService,
        ModelMapper modelMapper
    ){
        this.courseService = courseService;
        this.inclusionService = inclusionService;
        this.mapper = modelMapper;
    }

    @GetMapping("/course/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Inclusion course")
    public ModelAndView inclusionCourse(@PathVariable String id, ModelAndView modelAndView) {
        CourseServiceModel serviceModel = courseService.findCourseById(id);
        CourseDetailsViewModel viewModel = mapper.map(serviceModel, CourseDetailsViewModel.class);
        modelAndView.addObject("course", viewModel);

        return view("inclusion/course", modelAndView);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    @PageTitle("All inclusions")
    public ModelAndView getAllInclusions(ModelAndView modelAndView) {
        List<InclusionViewModel> viewModels = findAllInclusions();
        modelAndView.addObject("inclusions", viewModels);

        return view("inclusion/list-inclusions", modelAndView);
    }

    @GetMapping("/strudent")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Student inclusions")
    public ModelAndView getStudentInclusions(ModelAndView modelAndView, Principal principal) {
        List<InclusionViewModel> viewModels = findInclusionsByStudent(principal);
        modelAndView.addObject("inclusions", viewModels);

        return view("inclusion/list-inclusions", modelAndView);
    }

    private List<InclusionViewModel> findInclusionsByStudent(Principal principal) {
        String username = principal.getName();
        return inclusionService.findInclusionsByStudent(username)
                .stream()
                .map(o -> mapper.map(o, InclusionViewModel.class))
                .collect(Collectors.toList());
    }

    private List<InclusionViewModel> findAllInclusions() {
        return inclusionService.findAllInclusions()
                .stream()
                .map(o -> mapper.map(o, InclusionViewModel.class))
                .collect(Collectors.toList());
    }
}
