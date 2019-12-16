package org.softuni.university.integration.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Module;
import org.softuni.university.domain.models.service.ModuleServiceModel;
import org.softuni.university.repository.ModuleRepository;
import org.softuni.university.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ModuleServiceTests {
    @Autowired
    private ModuleService service;

    @MockBean
    private ModuleRepository mockModuleRepository;

    @MockBean
    private ModuleServiceModel mockModuleServiceModel;

    private List<Module> modules;

    @Before
    public void setupTest() {
        modules = new ArrayList<>();

        when(mockModuleRepository.findAll())
                .thenReturn(modules);
    }

    @Test(expected = Exception.class)
    public void addModule_whenNull_throw() throws Exception {
        ModuleServiceModel module = new ModuleServiceModel();

        service.addModule(module);

        verify(mockModuleRepository)
                .save(any());
    }

    @Test
    public void findAllModules_when1Modules_return1Modules() {
        String moduleName = "module 1";

        Module module = new Module() {{
            setName(moduleName);
        }};

        modules.add(module);

        var result = service.findAllModules();
        ModuleServiceModel moduleResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(moduleName, moduleResult.getName());
    }

    @Test
    public void findAllModules_whenNoModules_returnEmptyModules() {
        modules.clear();
        var result = service.findAllModules();
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void findModuleById_when1Modules_return1Modules() {
        String moduleName = "module 1";
        String moduleId = "module Id";

        Module module = new Module() {{
            setName(moduleName);
            setId(moduleId);
        }};

        modules.add(module);

        when(mockModuleRepository.findById(moduleId))
                .thenReturn(Optional.of(module));

        var result = service.findAllModules();
        ModuleServiceModel moduleResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(moduleName, moduleResult.getName());
    }

    @Test(expected = Exception.class)
    public void findModuleById_whenNoModule_ThrowException() {
        service.findModuleById("Empty");
    }

    @Test(expected = Exception.class)
    public void editModule_whenNoModules_ThrowException() {
        String moduleName = "module 1";
        String moduleId = "module Id";

        Module module = new Module() {{
            setName(moduleName);
            setId(moduleId);
        }};

        modules.add(module);

        when(mockModuleRepository.findById(moduleId))
                .thenReturn(Optional.of(module));

        var result = service.editModule(moduleId, mockModuleServiceModel);
    }

    @Test
    public void deleteModule_when1Modules_return0Modules() {
        String moduleName = "module 1";
        String moduleId = "module Id";

        Module module = new Module() {{
            setName(moduleName);
            setId(moduleId);
        }};

        modules.add(module);

        when(mockModuleRepository.findById(moduleId))
                .thenReturn(Optional.of(module));

        service.deleteModule(moduleId);
        modules.clear();

        var result = service.findAllModules();

        assertTrue(String.valueOf(result.isEmpty()), true);
    }

    @Test(expected = Exception.class)
    public void deleteModule_whenNoModules_ThrowException() {
        service.deleteModule("Empty");
    }
}
