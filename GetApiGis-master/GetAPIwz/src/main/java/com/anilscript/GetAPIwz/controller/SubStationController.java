package com.anilscript.GetAPIwz.controller;

import com.anilscript.GetAPIwz.model.SubStation;
import com.anilscript.GetAPIwz.service.SubStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class SubStationController {

    @Autowired
    private SubStation subStation;

    @Autowired
    private SubStationService subStationService;

    @GetMapping("/allsubstation")
    public List<SubStation> getAllSubStation()
    {
        System.out.println("in getallsubstation");
        return subStationService.getAllSubStation();
    }


}
