package org.softuni.university.service;

import org.softuni.university.domain.models.service.EnjoyServiceModel;

import java.util.List;

public interface EnjoyService {

    void createEnjoy(String courseId, String name) throws Exception;

    List<EnjoyServiceModel> findAllEnjoys();

    List<EnjoyServiceModel> findEnjoysByStudent(String username);
}
