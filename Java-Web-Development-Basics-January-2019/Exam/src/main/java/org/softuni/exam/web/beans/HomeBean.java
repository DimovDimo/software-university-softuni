package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.view.SubmissionAllViewModel;
import org.softuni.exam.service.SubmissionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {
    private List<SubmissionAllViewModel> submissions;

    private SubmissionService submissionService;
    private ModelMapper modelMapper;

    public HomeBean(){}

    @Inject
    public HomeBean(SubmissionService submissionService, ModelMapper modelMapper) {
        this.submissionService = submissionService;
        this.modelMapper = modelMapper;
        this.initModels();
    }

    private void initModels() {
        this.submissions = this.submissionService
                .findAllSubmissions()
                .stream()
                .map(p -> this.modelMapper.map(p, SubmissionAllViewModel.class))
                .collect(Collectors.toList());
    }

    public List<SubmissionAllViewModel> getSubmissions() {
        return this.submissions;
    }

    public void setSubmissions(List<SubmissionAllViewModel> submissions) {
        this.submissions = submissions;
    }
}
