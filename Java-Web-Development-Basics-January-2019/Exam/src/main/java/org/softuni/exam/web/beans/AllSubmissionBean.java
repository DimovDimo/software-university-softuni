package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.service.SubmissionServiceModel;
import org.softuni.exam.domain.models.service.UserServiceModel;
import org.softuni.exam.domain.models.view.SubmissionAllViewModel;
import org.softuni.exam.service.SubmissionService;
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
public class AllSubmissionBean {

    private List<SubmissionAllViewModel> submissions;

    private SubmissionService submissionService;
    private UserService userService;
    private ModelMapper modelMapper;

    public AllSubmissionBean() {
    }

    @Inject
    public AllSubmissionBean(SubmissionService submissionService, ModelMapper modelMapper, UserService userService) {
        this.submissionService = submissionService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.initModel();
    }

    private void initModel() {
        String id = initModelGetId();
        UserServiceModel loggedInUser = this.userService.getUserById(id);

        this.submissions = this.submissionService.findAllSubmissions()
                .stream()
                .map(c -> this.modelMapper.map(c, SubmissionAllViewModel.class))
                .collect(Collectors.toList());

    }

    private String initModelGetId() {
        return (String) ((HttpSession) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getSession(false))
                    .getAttribute("userId");
    }

    public List<SubmissionAllViewModel> getSubmissions() {
        return this.submissions;
    }

    public void setSubmissions(List<SubmissionAllViewModel> submissions) {
        this.submissions = submissions;
    }

    public void follow(String id) throws IOException, IOException {
        getLoggedInUser();
        SubmissionServiceModel submissionServiceModel =this.submissionService.findById(id);
        
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
