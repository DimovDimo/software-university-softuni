package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ControllerConstants;
import org.softuni.university.domain.models.binding.PollAddBindingModel;
import org.softuni.university.domain.models.service.PollServiceModel;
import org.softuni.university.domain.models.view.PollAllViewModel;
import org.softuni.university.service.PollService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/poll")
public class PollController extends BaseController {

    private final PollService pollService;
    private final ModelMapper mapper;

    public PollController(PollService pollService, ModelMapper mapper) {
        this.pollService = pollService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add poll")
    public ModelAndView addPoll() {
        return super.view("poll/add-poll");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Тhanks for your poll")
    public ModelAndView addPollConfirm(@ModelAttribute PollAddBindingModel model) throws Exception {
        PollServiceModel pollServiceModel = this.mapper.map(model, PollServiceModel.class);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        pollService.createPoll(pollServiceModel, name);

        return super.view("poll/thanks-poll");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("All polls")
    public ModelAndView allPolls(ModelAndView modelAndView) {
        findAllPolls(modelAndView);

        return super.view("poll/all-polls", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("Details poll")
    public ModelAndView detailsPoll(@PathVariable String id, ModelAndView modelAndView) {
        findPollDetailsById(id, modelAndView);

        return super.view("poll/details-poll", modelAndView);
    }

    private void findPollDetailsById(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.POLL,
                this.mapper.map(
                        this.pollService.findPollById(id),
                        PollAllViewModel.class
                ));
    }

    private void findAllPolls(ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.POLLS, this.pollService.findAllPolls()
                .stream()
                .map(pollServiceModel -> this.mapper.map(pollServiceModel, PollAllViewModel.class))
                .collect(Collectors.toList()));
    }
}
