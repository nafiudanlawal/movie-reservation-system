package com.nafiu.moviereservationservice.auth;

import com.nafiu.moviereservationservice.auth.dto.AuthLoginResponseDto;
import com.nafiu.moviereservationservice.auth.dto.UserLoginDto;
import com.nafiu.moviereservationservice.auth.dto.UserRegistrationDto;
import com.nafiu.moviereservationservice.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/auth",produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthLoginResponseDto userLogin(@Valid @RequestBody UserLoginDto userLoginDto){
        return this.authService.authenticateUser(userLoginDto);
    }

    @PostMapping("/register")
    public AuthLoginResponseDto userRegister(@Valid @RequestBody UserRegistrationDto userRegistrationDto){
        return this.authService.registerUser(userRegistrationDto);
    }

}
