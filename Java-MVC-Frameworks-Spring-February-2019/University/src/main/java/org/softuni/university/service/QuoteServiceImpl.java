package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Quote;
import org.softuni.university.domain.entities.SelectedQuote;
import org.softuni.university.domain.models.service.QuoteServiceModel;
import org.softuni.university.error.QuoteNotFoundException;
import org.softuni.university.repository.QuoteRepository;
import org.softuni.university.repository.SelectedQuoteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final SelectedQuoteRepository selectedQuoteRepository;
    private final ModelMapper modelMapper;

    public QuoteServiceImpl(
            QuoteRepository quoteRepository,
            SelectedQuoteRepository selectedQuoteRepository,
            ModelMapper modelMapper
    ) {
        this.quoteRepository = quoteRepository;
        this.selectedQuoteRepository = selectedQuoteRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public QuoteServiceModel addQuote(QuoteServiceModel quoteServiceModel) throws Exception {
        Quote quote = this.modelMapper.map(quoteServiceModel, Quote.class);

        return this.modelMapper.map(this.quoteRepository.saveAndFlush(quote), QuoteServiceModel.class);
    }

    @Override
    public List<QuoteServiceModel> findAllQuotes() {
        return this.quoteRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, QuoteServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuoteServiceModel findQuoteById(String id) {
        Quote quote = this.quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("QuoteNotFoundException with the given id was not found!"));

        return this.modelMapper.map(quote, QuoteServiceModel.class);
    }

    @Override
    public QuoteServiceModel editQuote(String id, QuoteServiceModel quoteServiceModel) {
        Quote quote = this.quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("QuoteNotFoundException with the given id was not found!"));

        quoteSets(quoteServiceModel, quote);

        return this.modelMapper.map(this.quoteRepository.saveAndFlush(quote), QuoteServiceModel.class);
    }

    @Override
    public QuoteServiceModel deleteQuote(String id) {
        Quote quote = this.quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("QuoteNotFoundException with the given id was not found!"));

        this.quoteRepository.delete(quote);

        return this.modelMapper.map(quote, QuoteServiceModel.class);
    }

    //                   second |min |hour| day
    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    private void selectQuote() {
        this.selectedQuoteRepository.deleteAll();
        List<QuoteServiceModel> quotes = this.findAllQuotes();

        if (quotesIsEmpty(quotes)) return;

        QuoteServiceModel quote = getRandomQuoteServiceModel(quotes);
        SelectedQuote selectedQuote = getSelectedQuoteSets(quote);

        this.selectedQuoteRepository.saveAndFlush(selectedQuote);
    }

    private SelectedQuote getSelectedQuoteSets(QuoteServiceModel quote) {
        SelectedQuote selectedQuote = new SelectedQuote();
        selectedQuote.setText(quote.getText());
        selectedQuote.setAuthor(quote.getAuthor());
        selectedQuote.setReason(quote.getReason());
        selectedQuote.setPlace(quote.getPlace());
        selectedQuote.setYear(quote.getYear());
        return selectedQuote;
    }

    private QuoteServiceModel getRandomQuoteServiceModel(List<QuoteServiceModel> quotes) {
        Random Dice = new Random();
        int n = Dice.nextInt(quotes.size());
        return quotes.get(n);
    }

    private boolean quotesIsEmpty(List<QuoteServiceModel> quotes) {
        if (quotes.isEmpty()) {
            return true;
        }
        return false;
    }

    private void quoteSets(QuoteServiceModel quoteServiceModel, Quote quote) {
        quote.setText(quoteServiceModel.getText());
        quote.setAuthor(quoteServiceModel.getAuthor());
        quote.setReason(quoteServiceModel.getReason());
        quote.setPlace(quoteServiceModel.getPlace());
        quote.setYear(quoteServiceModel.getYear());
    }
}
