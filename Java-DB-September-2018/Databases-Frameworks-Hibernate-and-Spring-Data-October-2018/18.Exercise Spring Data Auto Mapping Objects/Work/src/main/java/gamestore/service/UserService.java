package gamestore.service;

import gamestore.domain.dtos.GameAddDto;
import gamestore.domain.dtos.UserLoginDto;
import gamestore.domain.dtos.UserLogoutDto;
import gamestore.domain.dtos.UserRegisterDto;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logoutUser(UserLogoutDto userLogoutDto);

    boolean isAdmin(String email);

    String addGame(GameAddDto gameAddDto);
}
