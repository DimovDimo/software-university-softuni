package org.softuni.university.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.models.binding.UserEditBindingModel;
import org.softuni.university.domain.models.binding.UserRegisterBindingModel;
import org.softuni.university.domain.models.service.UserServiceModel;
import org.softuni.university.domain.models.view.UserAllViewModel;
import org.softuni.university.domain.models.view.UserProfileViewModel;
import org.softuni.university.service.UserService;
import org.softuni.university.validation.user.UserEditValidator;
import org.softuni.university.validation.user.UserRegisterValidator;
import org.softuni.university.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;
    private final UserEditValidator userEditValidator;

    @Autowired
    public UserController(
            UserService userService,
            ModelMapper modelMapper,
            UserRegisterValidator userRegisterValidator,
            UserEditValidator userEditValidator
    ) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = userRegisterValidator;
        this.userEditValidator = userEditValidator;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Register")
    public ModelAndView register(
            ModelAndView modelAndView,
            @ModelAttribute(name = "model") UserRegisterBindingModel model
    ) {
        modelAndView.addObject("model", model);
        return super.view("user/register", modelAndView);
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(
            ModelAndView modelAndView,
            @ModelAttribute(name = "model") UserRegisterBindingModel model,
            BindingResult bindingResult
    ) throws Exception {
        if (bindingResultHasErrors(modelAndView, model, bindingResult)){
            return super.view("user/register", modelAndView);
        }

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);

        return super.redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Login")
    public ModelAndView login() {
        return super.view("user/login");
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Profile")
    public ModelAndView profile(Principal principal, ModelAndView modelAndView) {
        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());
        UserProfileViewModel model = this.modelMapper.map(userServiceModel, UserProfileViewModel.class);
        modelAndView.addObject("model", model);

        return super.view("user/profile", modelAndView);
    }

    @GetMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Edit profile")
    public ModelAndView editProfile(
            Principal principal,
            ModelAndView modelAndView,
            @ModelAttribute(name = "model") UserEditBindingModel model
    ) {
        UserServiceModel userServiceModel = this.userService.findUserByUserName(principal.getName());
        model = this.modelMapper.map(userServiceModel, UserEditBindingModel.class);
        model.setPassword(null);
        modelAndView.addObject("model", model);

        return super.view("user/edit-profile", modelAndView);
    }

    @PatchMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editProfileConfirm(ModelAndView modelAndView, @ModelAttribute(name = "model") UserEditBindingModel model, BindingResult bindingResult) {
        if (editValidator(modelAndView, model, bindingResult)){
            return super.view("user/edit-profile", modelAndView);
        }

        UserServiceModel userServiceModel = this.modelMapper.map(model, UserServiceModel.class);
        this.userService.editUserProfile(userServiceModel, model.getOldPassword());

        return super.redirect("/users/profile");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    @PageTitle("All users")
    public ModelAndView allUsers(ModelAndView modelAndView) {
        List<UserAllViewModel> users = findAllUsers();
        modelAndView.addObject("users", users);

        return super.view("user/all-users", modelAndView);
    }

    @PostMapping("/set-student/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setStudent(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "student");

        return super.redirect("/users/all");
    }

    @PostMapping("/set-public-relations/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setPublicRelations(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "public");

        return super.redirect("/users/all");
    }

    @PostMapping("/set-chair-of-a-department/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setChair(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "chair");

        return super.redirect("/users/all");
    }

    @PostMapping("/set-dean/{id}")
    @PreAuthorize("hasRole('ROLE_DEAN')")
    public ModelAndView setDean(@PathVariable String id) throws Exception {
        this.userService.setUserRole(id, "dean");

        return super.redirect("/users/all");
    }

    private List<UserAllViewModel> findAllUsers() {
        return this.userService.findAllUsers()
                .stream()
                .map(u -> {
                    UserAllViewModel user = this.modelMapper.map(u, UserAllViewModel.class);
                    user.setAuthorities(u.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet()));
                    return user;
                })
                .collect(Collectors.toList());
    }

    private boolean bindingResultHasErrors(ModelAndView modelAndView, @ModelAttribute(name = "model") UserRegisterBindingModel model, BindingResult bindingResult) {
        this.userRegisterValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);

            return true;
        }

        return false;
    }

    private boolean editValidator(ModelAndView modelAndView, @ModelAttribute(name = "model") UserEditBindingModel model, BindingResult bindingResult) {
        this.userEditValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setOldPassword(null);
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);

            return true;
        }
        return false;
    }
}
