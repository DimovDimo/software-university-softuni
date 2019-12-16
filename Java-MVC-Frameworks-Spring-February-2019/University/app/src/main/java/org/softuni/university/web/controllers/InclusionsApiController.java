package org.softuni.university.web.controllers;

import org.softuni.university.constants.ControllerConstants;
import org.softuni.university.domain.models.rest.CourseInclusionRequestModel;
import org.softuni.university.service.InclusionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/inclusion")
public class InclusionsApiController {

    private final InclusionService inclusionService;

    public InclusionsApiController(InclusionService inclusionService) {
        this.inclusionService = inclusionService;
    }

    @PostMapping("/submit")
    @PreAuthorize("isAuthenticated()")
    public int submitInclusion(@RequestBody CourseInclusionRequestModel model, Principal principal) throws Exception {
        String name = principal.getName();
        inclusionService.createInclusion(model.getId(), name);

        return ControllerConstants.SUBMIT_INCLUSION_RETURN;
    }
}
