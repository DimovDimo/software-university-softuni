package org.softuni.exam.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.Problem;
import org.softuni.exam.domain.models.service.ProblemServiceModel;
import org.softuni.exam.domain.models.view.ProblemAllViewModel;
import org.softuni.exam.repository.ProblemRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;

    private final ModelMapper modelMapper;

    @Inject
    public ProblemServiceImpl(ProblemRepository problemRepository, ModelMapper modelMapper) {
        this.problemRepository = problemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProblemServiceModel createProblem(ProblemServiceModel problemServiceModel) {
        return this.modelMapper.map(
                this.problemRepository
                .save(this.modelMapper.map(problemServiceModel, Problem.class)),
                        ProblemServiceModel.class);
    }

    @Override
    public List<ProblemAllViewModel> findAllProblems() {
        return this.problemRepository.findAll()
                .stream()
                .map(problem -> this.modelMapper.map(problem, ProblemAllViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProblemServiceModel getProblemById(String id) {
        return this.modelMapper
                .map(this.problemRepository.findById(id), ProblemServiceModel.class);
    }

    @Override
    public ProblemServiceModel findById(String id) {
        Problem problem = this.problemRepository.findById(id);

        if (problem == null) {
            return null;
        }

        return this.modelMapper.map(problem, ProblemServiceModel.class);
    }
}
