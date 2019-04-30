package org.softuni.university.integration.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.softuni.university.domain.entities.Inclusion;
import org.softuni.university.domain.models.rest.CourseInclusionRequestModel;
import org.softuni.university.repository.InclusionRepository;
import org.softuni.university.service.InclusionService;
import org.softuni.university.web.controllers.InclusionsApiController;
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
public class InclusionsApiControllerTests {
    @Autowired
    InclusionsApiController controller;

    @Autowired
    InclusionService inclusionService;

    @Mock
    Principal principal;

    @MockBean
    InclusionRepository mockInclusionRepository;

    private List<Inclusion> inclusions;

    @Before
    public void setupTest(){
        inclusions = new ArrayList<>();

        when(mockInclusionRepository.findAllByUser_Username(any()))
                .thenReturn(inclusions);
    }

    @Test(expected = Exception.class)
    @WithMockUser("spring")
    public void submitInclusion_whenUsernameNotFound_throw() throws Exception {
        inclusions.clear();
        CourseInclusionRequestModel courseInclusionRequestModel = new CourseInclusionRequestModel();
        when(principal.getName())
                .thenReturn("UsernameNotFound");

        int result = controller.submitInclusion(courseInclusionRequestModel, principal);
        assertEquals("-1", -1 , result);
    }
}
