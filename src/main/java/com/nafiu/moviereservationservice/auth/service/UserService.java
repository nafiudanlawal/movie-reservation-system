package com.nafiu.moviereservationservice.auth.service;

import com.nafiu.moviereservationservice.auth.dto.AuthLoginResponseDto;
import com.nafiu.moviereservationservice.auth.dto.UserLoginDto;
import com.nafiu.moviereservationservice.auth.dto.UserRegistrationDto;
import com.nafiu.moviereservationservice.auth.dto.UserResponseDto;
import com.nafiu.moviereservationservice.auth.mapper.UserMapper;
import com.nafiu.moviereservationservice.auth.model.Role;
import com.nafiu.moviereservationservice.auth.model.User;
import com.nafiu.moviereservationservice.auth.model.UserPrincipal;
import com.nafiu.moviereservationservice.auth.respository.RoleRepository;
import com.nafiu.moviereservationservice.auth.respository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        System.out.printf("Get User: %s %n", username);
        User user = this.userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username: %s not found.".formatted(username))
        );
        return new UserPrincipal(user);
    }



}
