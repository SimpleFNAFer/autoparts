package com.services.autoparts.controller;

import com.services.autoparts.AuthService;
import com.services.autoparts.config.jwt.JwtUtils;
import com.services.autoparts.pojo.SignInRequest;
import com.services.autoparts.pojo.SignUpRequest;
import com.services.autoparts.repo.RoleRepository;
import com.services.autoparts.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/register")
    public String registerPage() { return "register"; }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        SignUpRequest signUpRequest = new SignUpRequest(username, password, new ArrayList<>());
        authService.registerUser(signUpRequest);
        return "login";
    }

    @PostMapping("/login")
    public String auth(@RequestParam String username, @RequestParam String password) {
        SignInRequest signInRequest = new SignInRequest(username, password);
        String jwt = authService.authUser(signInRequest);
        return "parts-search";
    }
}
