package org.example.springlesson.controller;

import org.example.springlesson.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

private static final JwtUtil jwtUtil = new JwtUtil();

    @PostMapping ("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password){
        if("admin".equals(username) && "1234".equals(password)){
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestHeader("Authorization") String authHeader){
        try {
            String token = authHeader.replace("Bearer ", "");
            String username = jwtUtil.validateTokenAndGetUsername(token);
            return ResponseEntity.ok("Welcome " + username + "!");
        } catch (Exception e){
            return ResponseEntity.status(403).body("Invalid token");
        }
    }
}
