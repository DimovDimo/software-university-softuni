package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.binding.CampAddBindingModel;
import org.softuni.university.domain.models.service.CampServiceModel;
import org.softuni.university.domain.models.view.CampAllViewModel;
import org.softuni.university.service.CampService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/camp")
public class CampController extends BaseController {

    private final CampService campService;
    private final ModelMapper mapper;

    public CampController(CampService campService, ModelMapper mapper) {
        this.campService = campService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add camp")
    public ModelAndView addCamp() {
        return super.view("camp/add-camp");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Тhanks for your opinion")
    public ModelAndView addCampConfirm(@ModelAttribute CampAddBindingModel model) throws Exception {
        CampServiceModel campServiceModel = this.mapper.map(model, CampServiceModel.class);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        campService.createCamp(campServiceModel, name);

        return super.view("camp/thanks-camp");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("All camps")
    public ModelAndView allCamps(ModelAndView modelAndView) {
        findAllCamps(modelAndView);

        return super.view("camp/all-camps", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("Details camp")
    public ModelAndView detailsCamp(@PathVariable String id, ModelAndView modelAndView) {
        findCampDetailsById(id, modelAndView);

        return super.view("camp/details-camp", modelAndView);
    }

    private void findCampDetailsById(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("camp",
                this.mapper.map(
                        this.campService.findCampById(id),
                        CampAllViewModel.class
                ));
    }

    private void findAllCamps(ModelAndView modelAndView) {
        modelAndView.addObject("camps", this.campService.findAllCamps()
                .stream()
                .map(campServiceModel -> this.mapper.map(campServiceModel, CampAllViewModel.class))
                .collect(Collectors.toList()));
    }
}
