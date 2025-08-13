package com.anilscript.GetAPIwz.loginModule.model;

import jakarta.persistence.*;
import lombok.Data;


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

    private String email;


    // e.g., ROLE_USER, ROLE_ADMIN


}

