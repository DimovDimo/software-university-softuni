package org.softuni.university.service;

import org.softuni.university.domain.models.service.PollServiceModel;

import java.util.List;

public interface PollService {

    void createPoll(PollServiceModel pollServiceModel, String name) throws Exception;

    List<PollServiceModel> findAllPolls();

    PollServiceModel findPollById(String id);
}
