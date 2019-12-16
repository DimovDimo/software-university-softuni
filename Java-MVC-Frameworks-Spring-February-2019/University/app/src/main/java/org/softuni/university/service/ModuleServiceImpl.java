package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ServiceConstants;
import org.softuni.university.domain.entities.Module;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.error.ModuleNotFoundException;
import org.softuni.university.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModuleServiceImpl(
            ModuleRepository moduleRepository,
            ModelMapper modelMapper
    ) {
        this.moduleRepository = moduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ModuleServiceModel addModule(ModuleServiceModel moduleServiceModel) throws Exception {
        Module module = this.modelMapper.map(moduleServiceModel, Module.class);

        return this.modelMapper.map(this.moduleRepository.saveAndFlush(module), ModuleServiceModel.class);
    }

    @Override
    public List<ModuleServiceModel> findAllModules() {
        return this.moduleRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, ModuleServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ModuleServiceModel findModuleById(String id) {
        Module module = this.moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));

        return this.modelMapper.map(module, ModuleServiceModel.class);
    }

    @Override
    public ModuleServiceModel editModule(String id, ModuleServiceModel moduleServiceModel) {
        Module module = this.moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));

        module.setName(moduleServiceModel.getName());

        return this.modelMapper.map(this.moduleRepository.saveAndFlush(module), ModuleServiceModel.class);
    }

    @Override
    public ModuleServiceModel deleteModule(String id) {
        Module module = this.moduleRepository.findById(id)
                .orElseThrow(() -> new ModuleNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));

        this.moduleRepository.delete(module);

        return this.modelMapper.map(module, ModuleServiceModel.class);
    }
}
