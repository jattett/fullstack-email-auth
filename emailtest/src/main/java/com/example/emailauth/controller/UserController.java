package com.example.emailauth.controller;
import com.example.emailauth.model.User;
import com.example.emailauth.repository.UserRepository;
import com.example.emailauth.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:5173") 
public class UserController {
    
    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User requestUser) {
        String email = requestUser.getEmail();
        String name = requestUser.getName();
        
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            return "이미 가입된 이메일입니다.";
        }
    
        // 토큰 생성 등은 User 생성자에서 처리됐다고 가정
        User user = new User(email, name);
        userRepository.save(user);
        emailService.sendVerificationEmail(email, user.getToken());
    
        return "이메일 전송 링크 완료";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam String token) {
        Optional<User> userOpt = userRepository.findByToken(token);
        if(userOpt.isEmpty()) {
            return "유효하지 않는 토큰입니다.";
        }

        User user = userOpt.get();
        user.setVerified(true);
        userRepository.save(user);

        return "이메일 인증이 완료되었습니다. 회원가입 성공!";
    }
    
}
