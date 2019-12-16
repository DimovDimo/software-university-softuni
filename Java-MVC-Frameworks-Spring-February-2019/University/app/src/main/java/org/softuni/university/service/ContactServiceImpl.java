package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ServiceConstants;
import org.softuni.university.domain.entities.Contact;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.error.ContactNotFoundException;
import org.softuni.university.repository.ContactRepository;
import org.softuni.university.validation.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;

    @Autowired
    public ContactServiceImpl(
            ContactRepository contactRepository,
            UserService userService,
            ModelMapper mapper,
            UserValidationService userValidation
    ) {
        this.contactRepository = contactRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.userValidation = userValidation;
    }

    @Override
    public void createContact(ContactServiceModel contactServiceModel, String name) throws UsernameNotFoundException {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new UsernameNotFoundException(ServiceConstants.USERNAME_NOT_FOUND);
        }

        User user = new User();
        user.setId(userModel.getId());
        Contact contact = this.mapper.map(contactServiceModel, Contact.class);
        contact.setUser(user);

        contactRepository.save(contact);
    }

    @Override
    public List<ContactServiceModel> findAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(contact -> mapper.map(contact, ContactServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContactServiceModel findContactById(String id) {
        return this.contactRepository.findById(id)
                .map(course -> this.mapper.map(course, ContactServiceModel.class))
                .orElseThrow(() -> new ContactNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));
    }
}
