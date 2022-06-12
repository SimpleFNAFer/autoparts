package com.services.autoparts;

import com.services.autoparts.config.jwt.JwtUtils;
import com.services.autoparts.model.user.Role;
import com.services.autoparts.model.user.User;
import com.services.autoparts.model.user.UserDetailsImplemented;
import com.services.autoparts.pojo.SignInRequest;
import com.services.autoparts.pojo.SignUpRequest;
import com.services.autoparts.repo.RoleRepository;
import com.services.autoparts.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public void registerUser(SignUpRequest signUpRequest) {
        if(!userRepository.existsByUsername(signUpRequest.getUsername())){
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

            Role userRole = roleRepository.findById(2L).get();
            List<Role> roles = new ArrayList<>();
            roles.add(userRole);

            user.setRoles(roles);
            userRepository.save(user);
        }
    }

    public String authUser (SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        passwordEncoder.encode(signInRequest.getPassword())
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return jwt;
    }
}
