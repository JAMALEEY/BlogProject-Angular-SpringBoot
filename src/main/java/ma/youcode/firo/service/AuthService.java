package ma.youcode.firo.service;


import ma.youcode.firo.dto.LoginRequest;
import ma.youcode.firo.dto.RegisterRequest;
import ma.youcode.firo.model.User;
import ma.youcode.firo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserName(registerRequest.getUsername());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);

    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public void login(LoginRequest loginRequest) {

    }
}
