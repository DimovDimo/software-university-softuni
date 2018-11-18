package gamestore.web.controllers;

import gamestore.domain.dtos.UserLoginDto;
import gamestore.domain.dtos.UserLogoutDto;
import gamestore.domain.dtos.UserRegisterDto;
import gamestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class GameStoreController implements CommandLineRunner {

    private String loggedInUser;
    private final UserService userService;

    @Autowired
    public GameStoreController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String inputLine = scanner.nextLine();

            String[] inputParams = inputLine.split("\\|");
            switch (inputParams[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto =
                            new UserRegisterDto(inputParams[1], inputParams[2], inputParams[3], inputParams[4]);

                    System.out.println(this.userService.registerUser(userRegisterDto));
                    break;
                case "LoginUser":
                    if (this.loggedInUser == null) {
                        UserLoginDto userLoginDto =
                                new UserLoginDto(inputParams[1], inputParams[2]);

                        String loginResult = this.userService.loginUser(userLoginDto);

                        if (loginResult.contains("Successfully logged in")) {
                            this.loggedInUser = userLoginDto.getEmail();
                        }

                        System.out.println(loginResult);
                    } else {
                        System.out.println("Session is taken.");
                    }

                    break;
                case "Logout":
                    if (this.loggedInUser == null) {
                        System.out.println("Cannot log out. No user was logged in.");
                    } else {
                        String logoutResult = this.userService.logoutUser(new UserLogoutDto(this.loggedInUser));
                        System.out.println(logoutResult);

                        this.loggedInUser = null;
                    }
                    break;
                case "AddGame":
                    if (this.loggedInUser != null && this.userService.isAdmin(this.loggedInUser)) {

                    } else {
                        System.out.println("Cannot log out. No user was logged in.");
                    }
                    break;
            }
        }
    }


}
