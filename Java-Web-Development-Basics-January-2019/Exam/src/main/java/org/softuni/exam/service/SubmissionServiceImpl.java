package org.softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.Submission;
import org.softuni.exam.domain.models.service.SubmissionServiceModel;
import org.softuni.exam.domain.models.view.SubmissionAllViewModel;
import org.softuni.exam.repository.SubmissionRepository;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;

    private final ModelMapper modelMapper;

    @Inject
    public SubmissionServiceImpl(SubmissionRepository submissionRepository, ModelMapper modelMapper) {
        this.submissionRepository = submissionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubmissionServiceModel createSubmission(SubmissionServiceModel submissionServiceModel) {
        setResult(submissionServiceModel);
        setCreatedOn(submissionServiceModel);

        return this.modelMapper.map(
                this.submissionRepository
                .save(this.modelMapper.map(submissionServiceModel, Submission.class)),
                        SubmissionServiceModel.class);
    }

    @Override
    public List<SubmissionAllViewModel> findAllSubmissions() {
        return this.submissionRepository.findAll()
                .stream()
                .map(submission -> this.modelMapper.map(submission, SubmissionAllViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubmissionServiceModel getSubmissionById(String id) {
        return this.modelMapper
                .map(this.submissionRepository.findById(id), SubmissionServiceModel.class);
    }

    @Override
    public SubmissionServiceModel findById(String id) {
        Submission submission = this.submissionRepository.findById(id);

        if (submission == null) {
            return null;
        }

        return this.modelMapper.map(submission, SubmissionServiceModel.class);
    }

    private void setCreatedOn(SubmissionServiceModel submissionServiceModel) {
        LocalDateTime now = LocalDateTime.now();
        submissionServiceModel.setCreatedOn(now);
    }

    private void setResult(SubmissionServiceModel submissionServiceModel) {
        Random Dice = new Random();

        int maxPoints = submissionServiceModel.getProblem().getPoints();
        int resultPoints = Dice.nextInt(maxPoints);
        int currentMaxPoints = submissionServiceModel.getAchievedResult();

        if(resultPoints > currentMaxPoints){
            submissionServiceModel.setAchievedResult(resultPoints);
        }
    }
}
