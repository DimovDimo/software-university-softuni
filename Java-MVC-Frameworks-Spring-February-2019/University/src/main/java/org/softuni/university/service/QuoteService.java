package org.softuni.university.service;

import org.softuni.university.domain.models.service.QuoteServiceModel;

import java.util.List;

public interface QuoteService {

    QuoteServiceModel addQuote(QuoteServiceModel quoteServiceModel) throws Exception;

    List<QuoteServiceModel> findAllQuotes();

    QuoteServiceModel findQuoteById(String id);

    QuoteServiceModel editQuote(String id, QuoteServiceModel quoteServiceModel);

    QuoteServiceModel deleteQuote(String id);
}
