package org.example.springlesson.controller;

import org.example.springlesson.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/auth")
public class AuthController {

private static final JwtUtil jwtUtil = new JwtUtil();

    @PostMapping ("/admin/login")
    public ResponseEntity<String> adminLogin(@RequestParam String username, @RequestParam String password){
        if("admin".equals(username) && "1234".equals(password)){
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping ("/moderator/login")
    public ResponseEntity<String> moderatorLogin(@RequestParam String username, @RequestParam String password){
        if("moderator".equals(username) && "1111".equals(password)){
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/admin/hello")
    public ResponseEntity<String> adminHello(@RequestHeader("Authorization") String authHeader){
        try {
            String token = authHeader.replace("Bearer ", "");
            String username = jwtUtil.validateTokenAndGetUsername(token);
            return ResponseEntity.ok("Welcome " + username + "! " + DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now()));
        } catch (Exception e){
            return ResponseEntity.status(403).body("Invalid token");
        }
    }

    @GetMapping("/moderator/hello")
    public ResponseEntity<String> moderatorHello(@RequestHeader("Authorization") String authHeader){
        try {
            String token = authHeader.replace("Bearer ", "");
            String username = jwtUtil.validateTokenAndGetUsername(token);
            return ResponseEntity.ok("Welcome " + username + "! " + DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now()));
        } catch (Exception e){
            return ResponseEntity.status(403).body("Invalid token");
        }
    }
}
