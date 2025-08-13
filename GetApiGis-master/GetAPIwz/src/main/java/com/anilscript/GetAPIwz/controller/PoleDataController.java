package com.anilscript.GetAPIwz.controller;


import com.anilscript.GetAPIwz.model.PoleData;
import com.anilscript.GetAPIwz.model.RapdrpLocationModel;
import com.anilscript.GetAPIwz.model.WorkDetails;
import com.anilscript.GetAPIwz.service.PoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class PoleDataController {
    @Autowired
    private PoleDataService poleDataService;



    // Accept form data as input
    @PostMapping("/fetchEmbData")
    public String fetchAndSaveData() // String fdr_id, String username ,String password
    {

        poleDataService.fetchAndSavePoleData();
        return "Data fetched and saved successfully";
    }

// ADMIN LEVEL API
    @PostMapping("/-fetchEmbDataForAllFdr")
    public String fetchAndSaveDataForAllFdr() // String fdr_id, String username ,String password
    {

        poleDataService.fetchAndSavePoleDataForAllFdr();
        return "Data fetched and saved successfully for all FdrIds";
    }

    @PostMapping("/getembdata")
    public List<PoleData> getEmbData(@RequestBody RapdrpLocationModel model){

        return poleDataService.getEmbData(model);
    }

    @PostMapping("/getembdatafromdc")
    public List<PoleData> getEmbDataFromDc(@RequestBody RapdrpLocationModel model){

        return poleDataService.getEmbDataFromDc(model);
    }

//    @PostMapping("/getembdatadc")
//    public List<PoleData> getEmbDataDc(@RequestBody RapdrpLocationModel model){
//
//        return poleDataService.getEmbDataDc(model);
//    }
//    @GetMapping("/all")
//    public List<PoleData> getAllData() {
//        return poleDataService.getAllPoleData();
//    }
}
