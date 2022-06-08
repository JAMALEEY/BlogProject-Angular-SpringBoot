package ma.youcode.firo.controller;

import ma.youcode.firo.dto.LoginRequest;
import ma.youcode.firo.dto.RegisterRequest;
import ma.youcode.firo.model.User;
import ma.youcode.firo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    // autowire the service
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
       return authService.login(loginRequest);
    }

}
