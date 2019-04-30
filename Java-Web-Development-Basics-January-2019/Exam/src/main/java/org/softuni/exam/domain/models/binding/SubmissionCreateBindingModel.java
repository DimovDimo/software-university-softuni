package org.softuni.exam.domain.models.binding;

import org.softuni.exam.domain.entities.Problem;
import org.softuni.exam.domain.entities.User;

import java.time.LocalDateTime;

public class SubmissionCreateBindingModel {

    private String id;
    private String code;
    private int achievedResult;
    private LocalDateTime createdOn;
    private Problem problem;
    private User user;

    public SubmissionCreateBindingModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAchievedResult() {
        return achievedResult;
    }

    public void setAchievedResult(int achievedResult) {
        this.achievedResult = achievedResult;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
