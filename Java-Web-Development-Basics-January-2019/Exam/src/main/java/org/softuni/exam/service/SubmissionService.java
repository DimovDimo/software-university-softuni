package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.SubmissionServiceModel;
import org.softuni.exam.domain.models.view.SubmissionAllViewModel;

import java.util.List;

public interface SubmissionService {
    SubmissionServiceModel createSubmission(SubmissionServiceModel submissionServiceModel);

    List<SubmissionAllViewModel> findAllSubmissions();

    SubmissionServiceModel getSubmissionById(String id);

    SubmissionServiceModel findById(String id);
}
