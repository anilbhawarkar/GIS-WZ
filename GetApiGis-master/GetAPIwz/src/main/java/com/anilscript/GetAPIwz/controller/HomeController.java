package com.anilscript.GetAPIwz.controller;

import com.anilscript.GetAPIwz.loginModule.model.User;
import com.anilscript.GetAPIwz.loginModule.repository.LocationRepository;
import com.anilscript.GetAPIwz.loginModule.repository.UserRepository;
import com.anilscript.GetAPIwz.model.LocationMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class HomeController {

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping("/")
    public String index()
    {
        return "index.html";
    }

//    private static final Logger logger = LoggerFactory.getLogger(RapdrpController.class);

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @GetMapping("/getdcfromdivision")
    public List<String> findDcFromDivision(@RequestParam String division)
    {
        final String methodName = "findDcFromDivision() :";
        logger.info(methodName +"called for division code :"+division);
        List<String> dcList = locationRepository.findDcListByDivision(division);
        System.out.println(dcList);
        return dcList;
    }
}
