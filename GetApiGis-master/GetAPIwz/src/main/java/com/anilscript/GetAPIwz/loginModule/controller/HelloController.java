package com.anilscript.GetAPIwz.loginModule.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello API called");
        return "Hello from secured endpoint!";
    }
}