package com.nafiu.moviereservationservice.auth.service;

import com.nafiu.moviereservationservice.auth.model.User;
import com.nafiu.moviereservationservice.auth.model.UserPrincipal;
import com.nafiu.moviereservationservice.auth.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username: %s not found.".formatted(username))
        );
        return new UserPrincipal(user);
    }



}
