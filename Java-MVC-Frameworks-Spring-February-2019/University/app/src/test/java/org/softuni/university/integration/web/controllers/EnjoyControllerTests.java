package org.softuni.university.integration.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.softuni.university.domain.entities.Enjoy;
import org.softuni.university.domain.models.view.EnjoyViewModel;
import org.softuni.university.repository.EnjoyRepository;
import org.softuni.university.web.controllers.EnjoyController;
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
public class EnjoyControllerTests {
    @Autowired
    EnjoyController controller;

    @Mock
    Principal principal;

    @MockBean
    EnjoyRepository enjoyRepository;

    private ArrayList<Enjoy> enjoys;

    @Before
    public void setupTest(){
        enjoys = new ArrayList<>();

        when(enjoyRepository.findAllByUser_Username(any()))
                .thenReturn(enjoys);
    }

    @Test
    @WithMockUser("spring")
    public void getStudentEnjoys_whenStudentHasNoEnjoys_empty() {
        enjoys.clear();
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getStudentEnjoys(modelAndView, principal);

        List<EnjoyViewModel> viewModels = (List<EnjoyViewModel>) result.getModel().get("enjoys");
        assertTrue(viewModels.isEmpty());
    }

    @Test
    @WithMockUser("spring")
    public void getStudentEnjoys_whenAllEnjoysAreForStudent_Enjoys() {
        enjoys.addAll(List.of(new Enjoy()));
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getStudentEnjoys(modelAndView, principal);

        List<EnjoyViewModel> viewModels = (List<EnjoyViewModel>) result.getModel().get("enjoys");
        assertEquals(enjoys.size(), viewModels.size());

    }

    @Test
    @WithMockUser("spring")
    public void getStudentEnjoys_whenNotAllEnjoysAreForStudent_enjoys() {
        enjoys.addAll(List.of(
                new Enjoy()
        ));

        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getStudentEnjoys(modelAndView, principal);

        List<EnjoyViewModel> viewModels = (List<EnjoyViewModel>) result.getModel().get("enjoys");
        assertEquals(enjoys.size(), viewModels.size());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void enjoyCourse_whenInvalidId_throw() {
        enjoys.addAll(List.of(new Enjoy()));
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.enjoyCourse("Test Id", modelAndView);

        List<EnjoyViewModel> viewModels = (List<EnjoyViewModel>) result.getModel().get("enjoys");

        assertEquals(enjoys.get(0).getCourse().getName(), viewModels.get(0).getName());
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void getAllEnjoys_whenNoAccess_AccessIsDenied() {
        enjoys.clear();
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");

        ModelAndView result = controller.getAllEnjoys(modelAndView);

        List<EnjoyViewModel> viewModels = (List<EnjoyViewModel>) result.getModel().get("enjoys");
        assertTrue(viewModels.isEmpty());
    }
}
