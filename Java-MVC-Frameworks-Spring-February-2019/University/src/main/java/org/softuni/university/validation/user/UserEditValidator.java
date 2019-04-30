package org.softuni.university.validation.user;

import org.softuni.university.domain.entities.User;
import org.softuni.university.domain.models.binding.UserEditBindingModel;
import org.softuni.university.repository.UserRepository;
import org.softuni.university.validation.ValidationConstants;
import org.softuni.university.validation.annotation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

@Validator
public class UserEditValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserEditValidator(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEditBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEditBindingModel userEditBindingModel = (UserEditBindingModel) o;
        User user = this.userRepository.findByUsername(userEditBindingModel.getUsername()).orElse(null);

        passwordMatches(errors, userEditBindingModel, user);
        passwordsEquals(errors, userEditBindingModel);
        emailAlreadyExists(errors, userEditBindingModel, user);
    }

    private void emailAlreadyExists(Errors errors, UserEditBindingModel userEditBindingModel, User user) {
        if (!user.getEmail().equals(userEditBindingModel.getEmail()) && this.userRepository.findByEmail(userEditBindingModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userEditBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userEditBindingModel.getEmail())
            );
        }
    }

    private void passwordsEquals(Errors errors, UserEditBindingModel userEditBindingModel) {
        if (userEditBindingModel.getPassword() != null && !userEditBindingModel.getPassword().equals(userEditBindingModel.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }
    }

    private void passwordMatches(Errors errors, UserEditBindingModel userEditBindingModel, User user) {
        if (!this.bCryptPasswordEncoder.matches(userEditBindingModel.getOldPassword(), user.getPassword())) {
            errors.rejectValue(
                    "oldPassword",
                    ValidationConstants.WRONG_PASSWORD,
                    ValidationConstants.WRONG_PASSWORD
            );
        }
    }
}
