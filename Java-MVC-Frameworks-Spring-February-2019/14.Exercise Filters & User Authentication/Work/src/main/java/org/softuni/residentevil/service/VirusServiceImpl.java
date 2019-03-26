package org.softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Virus;
import org.softuni.residentevil.domain.model.service.VirusServiceModel;
import org.softuni.residentevil.repository.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VirusServiceModel addVirus(VirusServiceModel virusServiceModel) {

        Virus entity = this.modelMapper.map(virusServiceModel, Virus.class);
        //if(entity.getId() != null) this.deleteVirusById(entity.getId());
        return this.modelMapper.map(this.virusRepository.saveAndFlush(entity), VirusServiceModel.class);
    }

    @Override
    public List<VirusServiceModel> getAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(v -> this.modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public VirusServiceModel findById(String id) {
        Virus virus = this.virusRepository.findById(id).orElse(null);
        return this.modelMapper.map(virus, VirusServiceModel.class);
    }

    @Override
    @Transactional
    public VirusServiceModel editVirus(VirusServiceModel virusServiceModel) {
        Virus entity = this.modelMapper.map(virusServiceModel, Virus.class);
        //if(entity.getId() != null)this.deleteVirusById(entity.getId());
        return this.modelMapper.map(this.virusRepository.saveAndFlush(entity), VirusServiceModel.class);
    }

    @Override
    public void deleteVirusById(String id) {
        this.virusRepository.deleteById(id);
    }

    @Override
    public List<VirusServiceModel> findAllViruses() {
        return this.virusRepository.findAll()
                .stream()
                .map(v->this.modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toList());
    }
}
