package com.anilscript.GetAPIwz.loginModule.model;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "user_details", schema = "auth")
@Data
//@Getter
//@Setter
//@ToString
public class LocationDeatilsFromLocationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String locationCode;
    private String locationName;
    private String password;
    private String role;
    private String email;
    private String accessLevel;

}