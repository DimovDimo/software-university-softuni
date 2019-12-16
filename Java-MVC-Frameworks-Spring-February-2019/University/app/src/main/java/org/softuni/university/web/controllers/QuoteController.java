package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ControllerConstants;
import org.softuni.university.domain.models.binding.QuoteAddBindingModel;
import org.softuni.university.domain.models.service.QuoteServiceModel;
import org.softuni.university.domain.models.view.QuoteViewModel;
import org.softuni.university.service.CloudinaryService;
import org.softuni.university.service.QuoteService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quotes")
public class QuoteController extends BaseController {

    private final QuoteService quoteService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuoteController(
            QuoteService quoteService,
            CloudinaryService cloudinaryService,
            ModelMapper modelMapper
    ) {
        this.quoteService = quoteService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("Add quote")
    public ModelAndView addQuote() {
        return super.view("quote/add-quote");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    public ModelAndView addQuoteConfirm(@ModelAttribute QuoteAddBindingModel model) throws Exception {
        this.quoteService.addQuote(this.modelMapper.map(model, QuoteServiceModel.class));
        
        return super.redirect("/quotes/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("All quotes")
    public ModelAndView allQuotes(ModelAndView modelAndView) {
        findAllQuotes(modelAndView);

        return super.view("quote/all-quotes", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("Edit quote")
    public ModelAndView editQuote(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.QUOTE,
                this.modelMapper.map(this.quoteService.findQuoteById(id), QuoteViewModel.class)
        );

        return super.view("quote/edit-quote", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    public ModelAndView editQuoteConfirm(@PathVariable String id, @ModelAttribute QuoteAddBindingModel model) {
        this.quoteService.editQuote(id, this.modelMapper.map(model, QuoteServiceModel.class));

        return super.redirect("/quotes/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("Delete quote")
    public ModelAndView deleteQuote(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.QUOTE,
                this.modelMapper.map(this.quoteService.findQuoteById(id), QuoteViewModel.class)
        );

        return super.view("quote/delete-quote", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    public ModelAndView deleteQuoteConfirm(@PathVariable String id) {
        this.quoteService.deleteQuote(id);

        return super.redirect("/quotes/all");
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @ResponseBody
    public List<QuoteViewModel> fetchQuotes() {
        return fetchQuotesFindAll();
    }

    private List<QuoteViewModel> fetchQuotesFindAll() {
        return this.quoteService.findAllQuotes()
                .stream()
                .map(c -> this.modelMapper.map(c, QuoteViewModel.class))
                .collect(Collectors.toList());
    }

    private void findAllQuotes(ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.QUOTES,
                this.quoteService.findAllQuotes()
                        .stream()
                        .map(c -> this.modelMapper.map(c, QuoteViewModel.class))
                        .collect(Collectors.toList())
        );
    }
}
