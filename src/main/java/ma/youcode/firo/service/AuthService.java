package ma.youcode.firo.service;

import ma.youcode.firo.dto.LoginRequest;
import ma.youcode.firo.dto.RegisterRequest;
import ma.youcode.firo.model.User;
import ma.youcode.firo.repository.UserRepository;
import ma.youcode.firo.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

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

    // authentication process logic using auth manager
    public String login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                // passing user credentials wrapped inside the UsernamepasswordAuthenticationToken class
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );
        // we can now be sure if the user is authenticated
        //  store the return type inside the spring security context
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return jwtProvider.generateToken(authenticate);
    }
}
