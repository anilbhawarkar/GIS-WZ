package com.anilscript.GetAPIwz.loginModule.controller;

import com.anilscript.GetAPIwz.loginModule.model.LocationDeatilsFromLocationModel;
import com.anilscript.GetAPIwz.loginModule.model.User;
import com.anilscript.GetAPIwz.loginModule.repository.LocationDeatilsFromLocationModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    AuthController authController;

    @Autowired
    LocationDeatilsFromLocationModelRepository locationDeatilsFromLocationModelRepository;
    @GetMapping("/registeruserbylocationdata")
    public String registerUserByLocationData()
    {
        List<LocationDeatilsFromLocationModel> locationUsers = locationDeatilsFromLocationModelRepository.findAll();
        System.out.println("User List size"+locationUsers.size());

        if(locationUsers.size()>0)
        {
            for (LocationDeatilsFromLocationModel user : locationUsers)
            {
                User locationUser = new User();
                locationUser.setUsername(user.getUsername());
                locationUser.setLocationCode(user.getLocationCode());
                locationUser.setLocationName(user.getLocationName());
                locationUser.setPassword(user.getPassword());
                locationUser.setRole(user.getRole());
                locationUser.setAccessLevel(user.getAccessLevel());
                locationUser.setEmail(user.getEmail());

                System.out.println(locationUser.toString());
//                System.out.println(user.getLocationName());
                authController.register(locationUser);
            }
        }
        return "User Created from user-master table";

    }
}
