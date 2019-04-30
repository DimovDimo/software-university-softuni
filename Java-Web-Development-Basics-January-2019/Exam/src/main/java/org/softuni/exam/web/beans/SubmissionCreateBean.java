package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.SubmissionCreateBindingModel;
import org.softuni.exam.domain.models.service.SubmissionServiceModel;
import org.softuni.exam.service.SubmissionService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class SubmissionCreateBean {

    private SubmissionCreateBindingModel submissionCreateBindingModel;

    private SubmissionService submissionService;
    private ModelMapper modelMapper;

    public SubmissionCreateBean(){}

    @Inject
    public SubmissionCreateBean(SubmissionService submissionService, ModelMapper modelMapper) {
        this.submissionCreateBindingModel = new SubmissionCreateBindingModel();
        this.submissionService = submissionService;
        this.modelMapper = modelMapper;
    }

    public SubmissionCreateBindingModel getSubmissionCreateBindingModel() {
        return this.submissionCreateBindingModel;
    }

    public void setSubmissionCreateBindingModel(SubmissionCreateBindingModel submissionCreateBindingModel) {
        this.submissionCreateBindingModel = submissionCreateBindingModel;
    }

    public void addSubmission() throws IOException {
        this.submissionService.createSubmission(
                this.modelMapper.map(this.submissionCreateBindingModel, SubmissionServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/submission/details-submission.xhtml");
    }
}
