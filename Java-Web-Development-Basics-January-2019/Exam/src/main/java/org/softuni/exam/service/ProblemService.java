package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.ProblemServiceModel;
import org.softuni.exam.domain.models.view.ProblemAllViewModel;

import java.util.List;

public interface ProblemService {
    ProblemServiceModel createProblem(ProblemServiceModel problemServiceModel);

    List<ProblemAllViewModel> findAllProblems();

    ProblemServiceModel getProblemById(String id);

    ProblemServiceModel findById(String id);
}
