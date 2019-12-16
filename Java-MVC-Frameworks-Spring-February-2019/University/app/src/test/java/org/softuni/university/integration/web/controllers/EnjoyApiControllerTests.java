package org.softuni.university.integration.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.softuni.university.domain.entities.Enjoy;
import org.softuni.university.domain.models.rest.CourseEnjoyRequestModel;
import org.softuni.university.repository.EnjoyRepository;
import org.softuni.university.service.EnjoyService;
import org.softuni.university.web.controllers.EnjoyApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
public class EnjoyApiControllerTests {
    @Autowired
    EnjoyApiController controller;

    @Autowired
    EnjoyService enjoyService;

    @Mock
    Principal principal;

    @MockBean
    EnjoyRepository enjoyRepository;

    private List<Enjoy> enjoys;

    @Before
    public void setupTest(){
        enjoys = new ArrayList<>();

        when(enjoyRepository.findAllByUser_Username(any()))
                .thenReturn(enjoys);
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void submitEnjoy_whenUsernameNotFound_throw() throws Exception {
        enjoys.clear();
        CourseEnjoyRequestModel courseEnjoyRequestModel = new CourseEnjoyRequestModel();
        when(principal.getName())
                .thenReturn("UsernameNotFound");

        int result = controller.submitEnjoy(courseEnjoyRequestModel, principal);
        assertEquals("-1", -1 , result);
    }
}
