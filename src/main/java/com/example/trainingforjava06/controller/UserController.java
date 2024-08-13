package com.example.trainingforjava06.controller;

import com.example.trainingforjava06.dto.LoginDTO;
import com.example.trainingforjava06.entity.User;
import com.example.trainingforjava06.repository.UserRepository;
import com.example.trainingforjava06.response.UserResponse;
import com.example.trainingforjava06.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    SecurityUtil securityUtil;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("successfuly of register");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        //nạp username, password vào security
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        //xác thực người dùng => cần viết hàm loadUserByUsername để springsercurity biết lấy user ở đâu để so sánh
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        //ham tao token
        String access_token = securityUtil.createToken(authentication);

        return ResponseEntity.ok(access_token);
    }

    @GetMapping("/infoUser")
    public ResponseEntity<?> getInfoUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findBySdt(currentPrincipalName);
        UserResponse userResponse= UserResponse.builder()
                .sdt(user.getSdt())
                .name(user.getName())
                .role(user.getRoles().getName())
                .build();
        return ResponseEntity.ok(userResponse);
    }

}
