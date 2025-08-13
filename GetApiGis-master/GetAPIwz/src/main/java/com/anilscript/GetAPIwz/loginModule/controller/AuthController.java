package com.anilscript.GetAPIwz.loginModule.controller;

import com.anilscript.GetAPIwz.loginModule.model.User;
import com.anilscript.GetAPIwz.loginModule.repository.LocationDeatilsFromLocationModelRepository;
import com.anilscript.GetAPIwz.loginModule.repository.UserRepository;
import com.anilscript.GetAPIwz.loginModule.service.CustomUserDetailsService;
import com.anilscript.GetAPIwz.loginModule.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    @Autowired
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    LocationDeatilsFromLocationModelRepository locationDeatilsFromLocationModelRepository;
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }
//new
@Autowired
private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        User user = customUserDetailsService.getUserEntityByUsername(username);
        String token = jwtUtil.generateToken(userDetails, user.getRole(), user.getAccessLevel());
        return Map.of("token", token);
    }


//    Old working
//    @PostMapping("/login")
//    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
//        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        String token = jwtUtil.generateToken(userDetails.getUsername());
//        System.out.println(Map.of("token",token));
//        return Map.of("token", token);
//    }


    public String changePassword(@RequestParam String username, @RequestParam String password)
    {

        return "";
    }

}


