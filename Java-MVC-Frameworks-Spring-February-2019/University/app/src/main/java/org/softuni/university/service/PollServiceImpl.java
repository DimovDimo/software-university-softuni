package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ServiceConstants;
import org.softuni.university.domain.entities.Poll;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.domain.models.service.PollServiceModel;
import org.softuni.university.error.PollNotFoundException;
import org.softuni.university.repository.PollRepository;
import org.softuni.university.validation.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;

    @Autowired
    public PollServiceImpl(
            PollRepository pollRepository,
            UserService userService, ModelMapper mapper,
            UserValidationService userValidation
    ) {
        this.pollRepository = pollRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.userValidation = userValidation;
    }


    @Override
    public void createPoll(PollServiceModel pollServiceModel, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new UsernameNotFoundException(ServiceConstants.USERNAME_NOT_FOUND);
        }

        User user = new User();
        user.setId(userModel.getId());
        Poll poll = this.mapper.map(pollServiceModel, Poll.class);
        poll.setUser(user);

        this.pollRepository.save(poll);
    }

    @Override
    public List<PollServiceModel> findAllPolls() {
        return pollRepository.findAll()
                .stream()
                .map(contact -> mapper.map(contact, PollServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public PollServiceModel findPollById(String id) {
        return this.pollRepository.findById(id)
                .map(course -> this.mapper.map(course, PollServiceModel.class))
                .orElseThrow(() -> new PollNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));
    }
}
