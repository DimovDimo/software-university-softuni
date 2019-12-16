package org.softuni.university.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Contact;
import org.softuni.university.mappings.IHaveCustomMappings;

public class ContactServiceModel implements IHaveCustomMappings {

    private String id;
    private String title;
    private String description;
    private String phone;
    private String zipCode;
    private String address;
    private String skype;
    private String website;
    private String student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(Contact.class, ContactServiceModel.class)
                .addMapping(
                        contact -> contact.getUser().getUsername(),
                        (dto, value) -> dto.setStudent((String) value)
                );
    }
}
