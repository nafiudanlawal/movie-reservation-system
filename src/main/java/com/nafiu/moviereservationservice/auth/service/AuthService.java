package com.nafiu.moviereservationservice.auth.service;

import com.nafiu.moviereservationservice.auth.dto.AuthLoginResponseDto;
import com.nafiu.moviereservationservice.auth.dto.UserLoginDto;
import com.nafiu.moviereservationservice.auth.dto.UserRegistrationDto;
import com.nafiu.moviereservationservice.auth.mapper.AuthDtoMapper;
import com.nafiu.moviereservationservice.auth.model.User;
import com.nafiu.moviereservationservice.auth.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthDtoMapper authDtoMapper;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AuthDtoMapper authDtoMapper
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authDtoMapper = authDtoMapper;
    }

    public AuthLoginResponseDto authenticateUser(UserLoginDto userLoginDto) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.username(),
                userLoginDto.password()
        ));
        User user = this.userRepository.findUserByUsername(userLoginDto.username()).orElseThrow(() ->
                new BadCredentialsException("Invalid user")
        );
        String jwtToken = jwtService.generateToken(user);
        return new AuthLoginResponseDto(
                jwtToken,
                "login success"
        );
    }

    public AuthLoginResponseDto registerUser(UserRegistrationDto userRegistrationDto) throws DuplicateKeyException{
        // check if username exists
        var userOptional = this.userRepository.findUserByUsername(userRegistrationDto.username());
        if(userOptional.isPresent()){
            throw new DuplicateKeyException("username already exists");
        }

        User user = authDtoMapper.userFromUserRegistrationDto(userRegistrationDto, "ROLE_USER");
        user.setPassword(this.passwordEncoder.encode(userRegistrationDto.password()));
        this.userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthLoginResponseDto(
                jwtToken,
                "registration success"
        );
    }
}
