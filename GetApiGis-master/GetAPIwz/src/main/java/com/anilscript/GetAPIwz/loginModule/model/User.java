package com.anilscript.GetAPIwz.loginModule.model;

import com.anilscript.GetAPIwz.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_master", schema = "auth")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String locationCode;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String accessLevel;
//    e.g. FULL_ACCESS

    @Column(unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING) // important
//    @Column(nullable = false)
    private UserStatus status;

    // e.g., ROLE_USER, ROLE_ADMIN

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    // ===== Lifecycle hooks to auto-manage timestamps =====
    @PrePersist
    protected void onCreate() {
        createdOn = LocalDateTime.now();
        updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = LocalDateTime.now();
    }


}

