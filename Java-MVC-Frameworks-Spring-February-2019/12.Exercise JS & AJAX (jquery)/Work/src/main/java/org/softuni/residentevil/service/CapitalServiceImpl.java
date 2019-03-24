package org.softuni.residentevil.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Capital;
import org.softuni.residentevil.domain.model.service.CapitalServiceModel;
import org.softuni.residentevil.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService {

    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CapitalServiceModel> findAllCapitals() {
        return this.capitalRepository.findAllOrderByName()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Capital findById(String id) throws NotFoundException {
        Capital capital = this.capitalRepository.findById(id).orElse(null);
        if (capital == null) {
            throw new NotFoundException(id);
        }
        return capital;
    }
}
