package com.example.learn.springcrud.service;

import com.example.learn.springcrud.data.User;
import com.example.learn.springcrud.dto.JwtAuthResponse;
import com.example.learn.springcrud.dto.SignInRequest;
import com.example.learn.springcrud.dto.SignUpRequest;

public interface AuthService {

    JwtAuthResponse signIn(SignInRequest signInRequest);

    User signUp(SignUpRequest signUpRequest);
}
