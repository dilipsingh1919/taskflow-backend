package com.example.taskflow.controller;

import com.example.taskflow.dto.LoginRequest;
import com.example.taskflow.dto.RegisterRequest;
import com.example.taskflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody RegisterRequest registerRequest){
return  ResponseEntity.ok(authService.register(registerRequest));
    }
    @PostMapping("/login")
    public  ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
        return  ResponseEntity.ok(authService.login(loginRequest));
    }
}
