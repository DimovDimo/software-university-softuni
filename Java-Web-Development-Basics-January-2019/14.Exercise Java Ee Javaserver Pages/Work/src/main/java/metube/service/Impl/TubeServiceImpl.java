package metube.service.Impl;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.repository.Interfaces.TubeRepository;
import metube.service.Interfaces.TubeService;
import metube.util.ModelMapper;

import javax.inject.Inject;
import javax.xml.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, Validator validator) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) {
        this.tubeRepository.save(
                this.modelMapper
                        .map(tubeServiceModel, Tube.class));
    }

    @Override
    public TubeServiceModel findTubeByName(String name) {
        Tube tube = this.tubeRepository
                .findByName(name)
                .orElse(null);

        if (tube.equals(null)) {
            throw new IllegalArgumentException("Tube is null.");
        }

        return this.modelMapper
                .map(tube, TubeServiceModel.class);
    }

    @Override
    public List<TubeServiceModel> findAllTubes() {
        return this.tubeRepository.findAll().stream()
                .map(t -> this.modelMapper
                        .map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }
}
