package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Contact;
import org.softuni.university.domain.models.service.ContactServiceModel;
import org.softuni.university.repository.ContactRepository;
import org.softuni.university.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactServiceTests {
    @Autowired
    private ContactService service;

    @MockBean
    private ContactRepository contactRepository;

    private List<Contact> contacts;

    @Before
    public void setupTest() {
        contacts = new ArrayList<>();
    }

    @Test(expected = Exception.class)
    public void createContact_whenNull_throw() throws Exception {
        ContactServiceModel contactServiceModel = new ContactServiceModel();
        contactServiceModel.setTitle("test");
        contactServiceModel.setDescription("test 2");

        service.createContact(contactServiceModel, "test");

        assertEquals(1, contactRepository.findAll().size());
    }

    @Test(expected = Exception.class)
    public void createContact_whenNameNull_throw() throws Exception {
        ContactServiceModel contactServiceModel = new ContactServiceModel();
        contactServiceModel.setTitle("Liking");
        contactServiceModel.setDescription("Not Liking");

        service.createContact(contactServiceModel, null);

        assertEquals(1, contactRepository.findAll().size());
    }

    @Test(expected = Exception.class)
    public void createContact_whenContactServiceModelNull_throw() throws Exception {
        service.createContact(null, "test");
    }

    @Test
    public void findAllContacts_whenEmpty_returnEmpty() throws Exception {
        when(contactRepository.findAll())
                .thenReturn(contacts);

        var result = service.findAllContacts();

        assertEquals(0, result.size());
    }

    @Test(expected = Exception.class)
    public void findContactById_whenNull_throw() throws Exception {
        when(contactRepository.findAll())
                .thenReturn(contacts);

        var result = service.findContactById("Empty");

        assertNull(result);
    }

    @Test(expected = Exception.class)
    public void findContactById_when1Contact_return1Contact() throws Exception {
        Contact contact = new Contact();
        contact.setId("Id");
        contacts.add(contact);

        when(contactRepository.findAll())
                .thenReturn(contacts);

        var result = service.findContactById("Id");

        assertNotNull(result);
    }
}
