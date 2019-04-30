package org.softuni.exam.web.beans;

import org.softuni.exam.domain.models.service.ProblemServiceModel;
import org.softuni.exam.service.ProblemService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ProblemDetailsBean {

    private ProblemService problemService;

    public ProblemDetailsBean() {
    }

    @Inject
    public ProblemDetailsBean(ProblemService problemService) {
        this.problemService = problemService;
    }

    public ProblemServiceModel getProblem(String id){
        return this.problemService.getProblemById(id);
    }
}
