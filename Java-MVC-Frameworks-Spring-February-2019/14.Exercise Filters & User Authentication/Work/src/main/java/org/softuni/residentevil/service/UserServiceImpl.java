package org.softuni.residentevil.service;

import org.modelmapper.ModelMapper;
import org.softuni.residentevil.domain.entities.Role;
import org.softuni.residentevil.domain.entities.User;
import org.softuni.residentevil.domain.model.service.UserServiceModel;
import org.softuni.residentevil.repository.RoleRepository;
import org.softuni.residentevil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        this.seedRolesInDb();

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.encoder.encode(userServiceModel.getPassword()));

        this.giveRolesToUser(user);

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        try {
            this.userRepository.saveAndFlush(user);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository
                .findUserByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }

    private void seedRolesInDb() {
        if (this.roleRepository.count() == 0){
            Role admin = new Role();
            admin.setAuthority("ROLE_ADMIN");
            this.roleRepository.saveAndFlush(admin);

            Role moderator = new Role();
            moderator.setAuthority("ROLE_MODERATOR");
            this.roleRepository.saveAndFlush(moderator);

            Role user = new Role();
            user.setAuthority("ROLE_USER");
            this.roleRepository.saveAndFlush(user);
        }
    }

    private void giveRolesToUser(User user){
        if (this.userRepository.count() == 0){
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_ADMIN"));
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_MODERATOR"));
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_USER"));
        } else {
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_USER"));
        }
    }
}
