package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.service.SelectedQuoteServiceModel;
import org.softuni.university.repository.SelectedQuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectedQuoteServiceImpl implements SelectedQuoteService {

    private final SelectedQuoteRepository selectedQuoteRepository;
    private final ModelMapper modelMapper;

    public SelectedQuoteServiceImpl(
            SelectedQuoteRepository selectedQuoteRepository,
            ModelMapper modelMapper
    ) {
        this.selectedQuoteRepository = selectedQuoteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SelectedQuoteServiceModel> findAllSelectedQuotes() {
        return this.selectedQuoteRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, SelectedQuoteServiceModel.class))
                .collect(Collectors.toList());
    }
}
