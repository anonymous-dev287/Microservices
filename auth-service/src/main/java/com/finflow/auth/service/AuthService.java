package com.finflow.auth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finflow.commonlib.security.JwtUtil;
import com.finflow.auth.dto.AuthRequest;
import com.finflow.auth.dto.AuthResponse;
import com.finflow.auth.dto.RegisterRequest;
import com.finflow.auth.model.Role;
import com.finflow.auth.model.User;
import com.finflow.auth.repository.UserRepository;
//import com.finflow.auth.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	@Autowired
    private final UserRepository userRepository;
	@Autowired
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
	

//    public String register(RegisterRequest request) {
//        User user = User.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword())) // Encrypt password
//                .roles(Collections.singleton(Role.CUSTOMER))
//                .build();
//        userRepository.save(user);
//        return "User registered successfully!";
//    }
    public String register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // ✅ Encrypt password
                .roles(Collections.singleton(Role.CUSTOMER))
                .build();
        userRepository.save(user);
        return "User registered successfully!";
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
    
    public String authenticate(String username, String password) {
        // ✅ Generate token using `common-lib`
        return jwtUtil.generateToken(username);
    }
}
