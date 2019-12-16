package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.constants.RoleConstants;
import org.softuni.university.domain.entities.Role;
import org.softuni.university.domain.models.service.RoleServiceModel;
import org.softuni.university.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(
            RoleRepository roleRepository,
            ModelMapper modelMapper
    ) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role(RoleConstants.ROLE_STUDENT));
            this.roleRepository.saveAndFlush(new Role(RoleConstants.ROLE_PUBLIC_RELATIONS));
            this.roleRepository.saveAndFlush(new Role(RoleConstants.ROLE_CHAIR_OF_A_DEPARTMENT));
            this.roleRepository.saveAndFlush(new Role(RoleConstants.ROLE_DEAN));
            this.roleRepository.saveAndFlush(new Role(RoleConstants.ROLE_PRESIDENT));
        }
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() throws Exception {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) throws Exception {
        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }
}
