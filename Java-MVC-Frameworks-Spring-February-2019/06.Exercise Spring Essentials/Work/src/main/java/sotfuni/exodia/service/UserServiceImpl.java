package sotfuni.exodia.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sotfuni.exodia.domain.entities.User;
import sotfuni.exodia.domain.models.service.UserServiceModel;
import sotfuni.exodia.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));

        try{
            this.userRepository.saveAndFlush(user);

            return true;
        }catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public UserServiceModel loginUser(UserServiceModel userServiceModel) {
        User user = this.userRepository.
                findByUsername(userServiceModel.getUsername())
                .orElse(null);

        if(user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()))){
            return null;
        }

        return this.modelMapper.map(user, UserServiceModel.class);
    }
}
