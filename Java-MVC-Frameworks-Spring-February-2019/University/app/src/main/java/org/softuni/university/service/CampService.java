package org.softuni.university.service;

import org.softuni.university.domain.models.service.CampServiceModel;

import java.util.List;

public interface CampService {

    void createCamp(CampServiceModel campServiceModel, String name) throws Exception;

    List<CampServiceModel> findAllCamps();

    CampServiceModel findCampById(String id);
}
