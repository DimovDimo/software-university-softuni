package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.service.ProblemServiceModel;
import org.softuni.exam.domain.models.service.UserServiceModel;
import org.softuni.exam.domain.models.view.ProblemAllViewModel;
import org.softuni.exam.service.ProblemService;
import org.softuni.exam.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class AllProblemBean {

    private List<ProblemAllViewModel> problems;

    private ProblemService problemService;
    private UserService userService;
    private ModelMapper modelMapper;

    public AllProblemBean() {
    }

    @Inject
    public AllProblemBean(ProblemService problemService, ModelMapper modelMapper, UserService userService) {
        this.problemService = problemService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.initModel();
    }

    private void initModel() {
        String id = initModelGetId();
        UserServiceModel loggedInUser = this.userService.getUserById(id);

        this.problems = this.problemService.findAllProblems()
                .stream()
                .map(c -> this.modelMapper.map(c, ProblemAllViewModel.class))
                .collect(Collectors.toList());

    }

    private String initModelGetId() {
        return (String) ((HttpSession) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getSession(false))
                    .getAttribute("userId");
    }

    public List<ProblemAllViewModel> getProblems() {
        return this.problems;
    }

    public void setProblems(List<ProblemAllViewModel> problems) {
        this.problems = problems;
    }

    public void follow(String id) throws IOException, IOException {
        getLoggedInUser();
        ProblemServiceModel problemServiceModel =this.problemService.findById(id);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }

    private void getLoggedInUser() {
        UserServiceModel loggedInUser =
                this.userService
                        .getUserById((String) ((HttpSession) FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .getSession(true))
                                .getAttribute("userId"));
    }
}
