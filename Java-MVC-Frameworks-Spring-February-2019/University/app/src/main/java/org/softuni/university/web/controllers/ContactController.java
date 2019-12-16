package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ControllerConstants;
import org.softuni.university.domain.models.binding.ContactAddBindingModel;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.domain.models.view.ContactAllViewModel;
import org.softuni.university.domain.models.view.ContactViewModel;
import org.softuni.university.service.ContactService;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/contact")
public class ContactController extends BaseController {

    private final ContactService contactService;
    private final ModelMapper mapper;

    public ContactController(ContactService contactService, ModelMapper mapper) {
        this.contactService = contactService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Add contact")
    public ModelAndView addContact() {
        return super.view("contact/add-contact");
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Тhanks for your contact")
    public ModelAndView addContactConfirm(@ModelAttribute ContactAddBindingModel model) throws Exception {
        ContactServiceModel contactServiceModel = this.mapper.map(model, ContactServiceModel.class);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        this.contactService.createContact(contactServiceModel, name);

        return super.view("contact/thanks-contact");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("All contacts")
    public ModelAndView allContacts(ModelAndView modelAndView) {
        findAllContacts(modelAndView);

        return super.view("contact/all-contacts", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('ROLE_PUBLIC_RELATIONS')")
    @PageTitle("Details contact")
    public ModelAndView detailsContact(@PathVariable String id, ModelAndView modelAndView) {
        findContactDetailsById(id, modelAndView);

        return super.view("contact/details-contact", modelAndView);
    }

    private void findContactDetailsById(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.CONTACT,
                this.mapper.map(
                        this.contactService.findContactById(id),
                        ContactViewModel.class
                ));
    }

    private void findAllContacts(ModelAndView modelAndView) {
        modelAndView.addObject(ControllerConstants.CONTACTS, this.contactService.findAllContacts()
                .stream()
                .map(contactServiceModel -> this.mapper.map(contactServiceModel, ContactAllViewModel.class))
                .collect(Collectors.toList()));
    }
}
