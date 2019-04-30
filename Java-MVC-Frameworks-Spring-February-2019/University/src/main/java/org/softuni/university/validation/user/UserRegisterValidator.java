package org.softuni.university.validation.user;

import org.softuni.university.domain.models.binding.UserRegisterBindingModel;
import org.softuni.university.repository.UserRepository;
import org.softuni.university.validation.ValidationConstants;
import org.softuni.university.validation.annotation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class UserRegisterValidator implements org.springframework.validation.Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) o;

        usernameAlreadyExists(errors, userRegisterBindingModel);
        usernameLength(errors, userRegisterBindingModel);
        passwordsLength(errors, userRegisterBindingModel);
        passwordsDoNotMatch(errors, userRegisterBindingModel);
        emailAlreadyExists(errors, userRegisterBindingModel);
    }

    private void emailAlreadyExists(Errors errors, UserRegisterBindingModel userRegisterBindingModel) {
        if (this.userRepository.findByEmail(userRegisterBindingModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, userRegisterBindingModel.getEmail())
            );
        }
    }

    private void passwordsDoNotMatch(Errors errors, UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }
    }

    private void passwordsLength(Errors errors, UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel.getPassword().length() < 6 || userRegisterBindingModel.getPassword().length() > 20) {
            errors.rejectValue(
                    "username",
                    ValidationConstants.PASSWORDS_LENGTH,
                    ValidationConstants.PASSWORDS_LENGTH
            );
        }
    }

    private void usernameLength(Errors errors, UserRegisterBindingModel userRegisterBindingModel) {
        if (userRegisterBindingModel.getUsername().length() < 6 || userRegisterBindingModel.getUsername().length() > 20) {
            errors.rejectValue(
                    "username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }
    }

    private void usernameAlreadyExists(Errors errors, UserRegisterBindingModel userRegisterBindingModel) {
        if (this.userRepository.findByUsername(userRegisterBindingModel.getUsername()).isPresent()) {
            errors.rejectValue(
                    "username",
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterBindingModel.getUsername()),
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, userRegisterBindingModel.getUsername())
            );
        }
    }
}
