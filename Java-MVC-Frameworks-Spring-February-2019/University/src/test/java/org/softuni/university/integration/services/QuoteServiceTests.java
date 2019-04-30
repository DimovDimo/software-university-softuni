package org.softuni.university.integration.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Quote;
import org.softuni.university.domain.models.service.QuoteServiceModel;
import org.softuni.university.repository.QuoteRepository;
import org.softuni.university.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuoteServiceTests {
    @Autowired
    private QuoteService service;

    @MockBean
    private QuoteRepository mockQuoteRepository;

    @MockBean
    private QuoteServiceModel mockQuoteServiceModel;

    private List<Quote> quotes;

    @Before
    public void setupTest() {
        quotes = new ArrayList<>();

        when(mockQuoteRepository.findAll())
                .thenReturn(quotes);
    }

    @Test(expected = Exception.class)
    public void addQuote_whenNull_throw() throws Exception {
        QuoteServiceModel quote = new QuoteServiceModel();

        service.addQuote(quote);

        verify(mockQuoteRepository)
                .save(any());
    }

    @Test
    public void findAllQuotes_when1Quotes_return1Quotes() {
        String quoteName = "quote 1";

        Quote quote = new Quote() {{
            setText(quoteName);
            setAuthor(quoteName);
        }};

        quotes.add(quote);

        var result = service.findAllQuotes();
        QuoteServiceModel quoteResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(quoteName, quoteResult.getText());
        assertEquals(quoteName, quoteResult.getAuthor());
    }

    @Test
    public void findAllQuotes_whenNoQuotes_returnEmptyQuotes() {
        quotes.clear();
        var result = service.findAllQuotes();
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void findQuoteById_when1Quotes_return1Quotes() {
        String quoteName = "quote 1";
        String quoteId = "quote Id";

        Quote quote = new Quote() {{
            setText(quoteName);
            setAuthor(quoteName);
            setId(quoteId);
        }};

        quotes.add(quote);

        when(mockQuoteRepository.findById(quoteId))
                .thenReturn(Optional.of(quote));

        var result = service.findAllQuotes();
        QuoteServiceModel quoteResult = result.get(0);

        assertEquals(1, result.size());
        assertEquals(quoteName, quoteResult.getText());
        assertEquals(quoteName, quoteResult.getAuthor());
    }

    @Test(expected = Exception.class)
    public void findQuoteById_whenNoQuote_ThrowException() {
        service.findQuoteById("Empty");
    }

    @Test(expected = Exception.class)
    public void editQuote_whenNoQuotes_ThrowException() {
        String quoteName = "quote 1";
        String quoteId = "quote Id";

        Quote quote = new Quote() {{
            setText(quoteName);
            setAuthor(quoteName);
            setId(quoteId);
        }};

        quotes.add(quote);

        when(mockQuoteRepository.findById(quoteId))
                .thenReturn(Optional.of(quote));

        var result = service.editQuote(quoteId, mockQuoteServiceModel);
    }

    @Test
    public void deleteQuote_when1Quotes_return0Quotes() {
        String quoteName = "quote 1";
        String quoteId = "quote Id";

        Quote quote = new Quote() {{
            setText(quoteName);
            setAuthor(quoteName);
            setId(quoteId);
        }};

        quotes.add(quote);

        when(mockQuoteRepository.findById(quoteId))
                .thenReturn(Optional.of(quote));

        service.deleteQuote(quoteId);
        quotes.clear();

        var result = service.findAllQuotes();

        assertTrue(String.valueOf(result.isEmpty()), true);
    }

    @Test(expected = Exception.class)
    public void deleteQuote_whenNoQuotes_ThrowException() {
        service.deleteQuote("Empty");
    }
}
