package com.anilscript.GetAPIwz.loginModule.model;

import com.anilscript.GetAPIwz.loginModule.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seedData() {
        String defaultUsername = "admin";

        if (userRepository.findByUsername(defaultUsername).isEmpty()) {
            User user = new User();
            user.setUsername(defaultUsername);
            user.setPassword(passwordEncoder.encode("admin123")); // Encrypted
            user.setEmail("admin@gmail.com");
            user.setRole("ADMIN");
            user.setAccessLevel("ADMIN_ACCESS");
            user.setLocationCode("Admin");
            user.setLocationName("Admin");

            userRepository.save(user);
            System.out.println("✅ Default admin user created: username=admin, password=admin123");
        } else {
            System.out.println("ℹ️ Default admin user already exists.");
        }
    }
}

