package com.services.autoparts;

import com.services.autoparts.model.user.User;
import com.services.autoparts.model.user.UserDetailsImplemented;
import com.services.autoparts.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserDetailsServiceImplemented implements UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) throw new UsernameNotFoundException("User "+username+" not found");

        return new UserDetailsImplemented(user.get());
    }
}
