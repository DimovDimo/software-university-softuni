package org.softuni.university.service;

import org.softuni.university.domain.models.service.ModuleServiceModel;

import java.util.List;

public interface ModuleService {

    ModuleServiceModel addModule(ModuleServiceModel moduleServiceModel) throws Exception;

    List<ModuleServiceModel> findAllModules();

    ModuleServiceModel findModuleById(String id);

    ModuleServiceModel editModule(String id, ModuleServiceModel moduleServiceModel);

    ModuleServiceModel deleteModule(String id);
}
