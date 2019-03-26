package org.softuni.residentevil.service;

import javassist.NotFoundException;
import org.softuni.residentevil.domain.entities.Capital;
import org.softuni.residentevil.domain.model.service.CapitalServiceModel;

import java.util.List;

public interface CapitalService {

    List<CapitalServiceModel> findAllCapitals();

    Capital findById(String id) throws NotFoundException;
}
