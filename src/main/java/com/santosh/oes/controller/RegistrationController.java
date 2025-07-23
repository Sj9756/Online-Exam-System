package com.santosh.oes.controller;


import com.santosh.oes.model.UserOes;
import com.santosh.oes.service.interfaces.UserOesService;
import com.santosh.oes.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class RegistrationController {
    @Autowired
    UserOesService userOesService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signupWithEmailPassword(@RequestBody UserOes userOes){
        if (userOesService.isPresent(userOes.getUsername())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with this email already exists");
        }
        userOesService.save(userOes);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public  ResponseEntity<String> loginWithEmailPassword(@RequestBody UserOes userOes){
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userOes.getUsername(),userOes.getPassword()));
        if (authentication.isAuthenticated()){
            String token=jwtUtil.createToken(userOes.getUsername());
        return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User Not Found");
    }


}
