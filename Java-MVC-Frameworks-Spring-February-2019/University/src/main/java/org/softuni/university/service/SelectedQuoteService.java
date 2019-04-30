package org.softuni.university.service;

import org.softuni.university.domain.models.service.SelectedQuoteServiceModel;

import java.util.List;

public interface SelectedQuoteService {

    List<SelectedQuoteServiceModel> findAllSelectedQuotes();
}
