package org.softuni.university.integration.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.softuni.university.domain.entities.Inclusion;
import org.softuni.university.domain.models.view.InclusionViewModel;
import org.softuni.university.repository.InclusionRepository;
import org.softuni.university.web.controllers.InclusionsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
public class InclusionsControllerTests {
    @Autowired
    InclusionsController controller;

    @Mock
    Principal principal;

    @MockBean
    InclusionRepository mockInclusionRepository;

    private ArrayList<Inclusion> inclusions;

    @Before
    public void setupTest(){
        inclusions = new ArrayList<>();

        when(mockInclusionRepository.findAllByUser_Username(any()))
                .thenReturn(inclusions);
    }

    @Test
    @WithMockUser("spring")
    public void getStudentInclusions_whenStudentHasNoInclusions_empty() {
        inclusions.clear();
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getStudentInclusions(modelAndView, principal);

        List<InclusionViewModel> viewModels = (List<InclusionViewModel>) result.getModel().get("inclusions");
        assertTrue(viewModels.isEmpty());
    }

    @Test
    @WithMockUser("spring")
    public void getStudentInclusions_whenAllInclusionsAreForStudent_Inclusions() {
        inclusions.addAll(List.of(new Inclusion()));
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getStudentInclusions(modelAndView, principal);

        List<InclusionViewModel> viewModels = (List<InclusionViewModel>) result.getModel().get("inclusions");
        assertEquals(inclusions.size(), viewModels.size());

    }

    @Test
    @WithMockUser("spring")
    public void getStudentInclusions_whenNotAllInclusionsAreForStudent_inclusions() {
        inclusions.addAll(List.of(
                new Inclusion()
        ));

        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getStudentInclusions(modelAndView, principal);

        List<InclusionViewModel> viewModels = (List<InclusionViewModel>) result.getModel().get("inclusions");
        assertEquals(inclusions.size(), viewModels.size());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void inclusionCourse_whenInvalidId_throw() {
        inclusions.addAll(List.of(new Inclusion()));
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.inclusionCourse("Test Id", modelAndView);

        List<InclusionViewModel> viewModels = (List<InclusionViewModel>) result.getModel().get("inclusions");

        assertEquals(inclusions.get(0).getCourse().getName(), viewModels.get(0).getName());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void getAllInclusions_whenNoAccess_AccessIsDenied() {
        inclusions.clear();
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getAllInclusions(modelAndView);

        List<InclusionViewModel> viewModels = (List<InclusionViewModel>) result.getModel().get("inclusions");
        assertTrue(viewModels.isEmpty());
    }
}
