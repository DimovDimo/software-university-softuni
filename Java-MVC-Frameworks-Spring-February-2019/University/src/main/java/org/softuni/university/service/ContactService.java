package org.softuni.university.service;

import org.softuni.university.domain.models.service.ContactServiceModel;

import java.util.List;

public interface ContactService {

    void createContact(ContactServiceModel contactServiceModel, String name) throws Exception;

    List<ContactServiceModel> findAllContacts();

    ContactServiceModel findContactById(String id);
}
