package com.anilscript.GetAPIwz.loginModule.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // generates secure random key
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Your new secure JWT key: " + base64Key);
    }
}
