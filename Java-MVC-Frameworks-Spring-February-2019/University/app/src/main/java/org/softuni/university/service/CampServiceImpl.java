package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.ServiceConstants;
import org.softuni.university.domain.entities.Camp;
import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.service.CampServiceModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.error.CampNotFoundException;
import org.softuni.university.repository.CampRepository;
import org.softuni.university.validation.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampServiceImpl implements CampService {

    private final CampRepository campRepository;
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidationService userValidation;

    @Autowired
    public CampServiceImpl(
            CampRepository campRepository,
            UserService userService, ModelMapper mapper,
            UserValidationService userValidation
    ) {
        this.campRepository = campRepository;
        this.userService = userService;
        this.mapper = mapper;
        this.userValidation = userValidation;
    }


    @Override
    public void createCamp(CampServiceModel campServiceModel, String name) throws Exception {
        UserServiceModel userModel = userService.findUserByUserName(name);
        if(!userValidation.isValid(userModel)) {
            throw new UsernameNotFoundException(ServiceConstants.USERNAME_NOT_FOUND);
        }

        User user = new User();
        user.setId(userModel.getId());
        Camp camp = this.mapper.map(campServiceModel, Camp.class);
        camp.setUser(user);

        this.campRepository.save(camp);
    }

    @Override
    public List<CampServiceModel> findAllCamps() {
        return campRepository.findAll()
                .stream()
                .map(contact -> mapper.map(contact, CampServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CampServiceModel findCampById(String id) {
        return this.campRepository.findById(id)
                .map(course -> this.mapper.map(course, CampServiceModel.class))
                .orElseThrow(() -> new CampNotFoundException(ServiceConstants.NOT_FOUND_EXCEPTION_WITH_THE_GIVEN_ID));
    }
}
