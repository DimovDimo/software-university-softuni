package org.softuni.exam.web.beans;

import org.softuni.exam.domain.models.service.SubmissionServiceModel;
import org.softuni.exam.service.SubmissionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SubmissionDetailsBean {

    private SubmissionService submissionService;

    public SubmissionDetailsBean() {
    }

    @Inject
    public SubmissionDetailsBean(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    public SubmissionServiceModel getSubmission(String id){
        return this.submissionService.getSubmissionById(id);
    }
}
