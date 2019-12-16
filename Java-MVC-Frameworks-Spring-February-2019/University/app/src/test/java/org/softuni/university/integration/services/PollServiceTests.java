package org.softuni.university.integration.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.softuni.university.domain.entities.Poll;
import org.softuni.university.domain.models.service.PollServiceModel;
import org.softuni.university.repository.PollRepository;
import org.softuni.university.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PollServiceTests {
    @Autowired
    private PollService service;

    @MockBean
    private PollRepository mockPollRepository;

    private List<Poll> polls;

    @Before
    public void setupTest() {
        polls = new ArrayList<>();
    }

    @Test(expected = Exception.class)
    public void createPoll_whenNull_throw() throws Exception {
        PollServiceModel pollServiceModel = new PollServiceModel();
        pollServiceModel.setLiking("Liking");
        pollServiceModel.setNotLiking("Not Liking");

        service.createPoll(pollServiceModel, "test");

        assertEquals(1, mockPollRepository.findAll().size());
    }

    @Test(expected = Exception.class)
    public void createPoll_whenNameNull_throw() throws Exception {
        PollServiceModel pollServiceModel = new PollServiceModel();
        pollServiceModel.setLiking("Liking");
        pollServiceModel.setNotLiking("Not Liking");

        service.createPoll(pollServiceModel, null);

        assertEquals(1, mockPollRepository.findAll().size());
    }

    @Test(expected = Exception.class)
    public void createPoll_whenPollServiceModelNull_throw() throws Exception {
        service.createPoll(null, "test");
    }

    @Test
    public void findAllPolls_whenEmpty_returnEmpty() throws Exception {
        when(mockPollRepository.findAll())
                .thenReturn(polls);

        var result = service.findAllPolls();

        assertEquals(0, result.size());
    }

    @Test(expected = Exception.class)
    public void findPollById_whenNull_throw() throws Exception {
        when(mockPollRepository.findAll())
                .thenReturn(polls);

        var result = service.findPollById("Empty");

        assertNull(result);
    }

    @Test(expected = Exception.class)
    public void findPollById_when1Poll_return1Poll() throws Exception {
        Poll poll = new Poll();
        poll.setId("Id");
        polls.add(poll);

        when(mockPollRepository.findAll())
                .thenReturn(polls);

        var result = service.findPollById("Id");

        assertNotNull(result);
    }
}
