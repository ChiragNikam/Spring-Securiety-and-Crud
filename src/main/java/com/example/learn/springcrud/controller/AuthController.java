package com.example.learn.springcrud.controller;

import com.example.learn.springcrud.data.User;
import com.example.learn.springcrud.dto.JwtAuthResponse;
import com.example.learn.springcrud.dto.SignInRequest;
import com.example.learn.springcrud.dto.SignUpRequest;
import com.example.learn.springcrud.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/signIn")
    public ResponseEntity<?> login(@RequestBody SignInRequest signInRequest){
        JwtAuthResponse response = new JwtAuthResponse();
        try {
            response = authService.signIn(signInRequest);
        } catch (Exception e){
            return ResponseEntity.ok(e.getLocalizedMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/signUp")
    public ResponseEntity<User> register(@RequestBody SignUpRequest signUpData){
        return ResponseEntity.ok(authService.signUp(signUpData));
    }
}

