package com.anilscript.GetAPIwz.loginModule.service;

import com.anilscript.GetAPIwz.loginModule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;


        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            com.anilscript.GetAPIwz.loginModule.model.User user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            System.out.println("User details = "+ user.toString());
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(((com.anilscript.GetAPIwz.loginModule.model.User) user).getRole()))
            );
        }

        public com.anilscript.GetAPIwz.loginModule.model.User getUserEntityByUsername(String username) {
            return userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
    }


    //old woring

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        com.anilscript.login_project.loginModule.model.User user = userRepo.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
//        );
//    }
//}