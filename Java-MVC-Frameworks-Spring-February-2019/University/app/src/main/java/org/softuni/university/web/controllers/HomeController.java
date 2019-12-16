package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ControllerConstants;
import org.softuni.university.domain.models.view.ModuleViewModel;
import org.softuni.university.domain.models.view.SelectedQuoteViewModel;
import org.softuni.university.service.ModuleService;
import org.softuni.university.service.SelectedQuoteService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private final ModuleService moduleService;
    private final SelectedQuoteService selectedQuoteService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(
            ModuleService moduleService,
            SelectedQuoteService selectedQuoteService,
            ModelMapper modelMapper
    ) {
        this.moduleService = moduleService;
        this.selectedQuoteService = selectedQuoteService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Index")
    public ModelAndView index() {
        return super.view(ControllerConstants.INDEX);
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Home")
    public ModelAndView home(ModelAndView modelAndView) {
        addModules(modelAndView);
        addSelectedQuotes(modelAndView);

        return super.view(ControllerConstants.HOME, modelAndView);
    }

    private void addModules(ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.MODULES,
                this.moduleService.findAllModules()
                        .stream()
                        .map(module -> this.modelMapper.map(module, ModuleViewModel.class)
                ).collect(Collectors.toList()));
    }

    private void addSelectedQuotes(ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.QUOTES,
                this.selectedQuoteService.findAllSelectedQuotes()
                        .stream()
                        .map(selectedQuote -> this.modelMapper.map(selectedQuote, SelectedQuoteViewModel.class)
                        ).collect(Collectors.toList()));
    }
}
