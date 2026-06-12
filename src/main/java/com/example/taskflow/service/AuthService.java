package com.example.taskflow.service;

import com.example.taskflow.Entity.User;
import com.example.taskflow.dto.LoginRequest;
import com.example.taskflow.dto.RegisterRequest;
import com.example.taskflow.repo.UserRepo;
import com.example.taskflow.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {
     @Autowired
    private final UserRepo userRepo;

    private  final JwtUtil jwtUtil;

    private  final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request){
        User user1 = new User();
        user1.setName(request.getName());
        user1.setEmail(request.getEmail());
        user1.setPassword(passwordEncoder.encode(request.getPassword()));

        return (User) userRepo.save(user1);


    }
    public Map<String, Object> login(LoginRequest loginRequest){
        User user1= userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("user not found "));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user1.getPassword())){
            throw  new RuntimeException("password wrong");
        }
        String token = jwtUtil.generateToken(user1.getEmail());
        Map<String,Object> response= new HashMap<>();
        response.put("token",token);
        response.put("user",user1);
        return response;
    }


}
