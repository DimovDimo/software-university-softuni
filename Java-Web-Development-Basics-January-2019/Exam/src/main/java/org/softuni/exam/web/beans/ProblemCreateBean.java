package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.ProblemCreateBindingModel;
import org.softuni.exam.domain.models.service.ProblemServiceModel;
import org.softuni.exam.service.ProblemService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class ProblemCreateBean {

    private ProblemCreateBindingModel problemCreateBindingModel;

    private ProblemService problemService;
    private ModelMapper modelMapper;

    public ProblemCreateBean(){}

    @Inject
    public ProblemCreateBean(ProblemService problemService, ModelMapper modelMapper) {
        this.problemCreateBindingModel = new ProblemCreateBindingModel();
        this.problemService = problemService;
        this.modelMapper = modelMapper;
    }

    public ProblemCreateBindingModel getProblemCreateBindingModel() {
        return this.problemCreateBindingModel;
    }

    public void setProblemCreateBindingModel(ProblemCreateBindingModel problemCreateBindingModel) {
        this.problemCreateBindingModel = problemCreateBindingModel;
    }

    public void addProblem() throws IOException {
        this.problemService.createProblem(
                this.modelMapper.map(this.problemCreateBindingModel, ProblemServiceModel.class));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/home.xhtml");
    }
}
