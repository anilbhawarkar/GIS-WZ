package com.anilscript.GetAPIwz.controller;
import com.anilscript.GetAPIwz.model.RapdrpLocationModel;
import com.anilscript.GetAPIwz.model.WorkDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.anilscript.GetAPIwz.service.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class WorkDetailsController {
    @Autowired
    private WorkDetailsService workDetailsService;

    // ADMIN LEVEL API
    @GetMapping("/-fetchandsaveWorkDetails")
    public String fetchAndSaveWorkDetails() {
        workDetailsService.fetchAndSaveWorkDetails();
        return "Work details saved successfully!";
    }

    @PostMapping("/getWorkDetails")
    public List<WorkDetails> getWorkDetails(@RequestBody RapdrpLocationModel model){

        return workDetailsService.getWorkDetails(model);
    }

    @PostMapping("/getdccodefromfdrid")
    public String getDcCodeFromFdrId(@RequestParam String fdrId)
    {
        return workDetailsService.getDcCodeFromFdrId(fdrId);
    }

    @GetMapping("/getworkdetailsforembdata")
    public List<WorkDetails> getWorkDetailsForEmbData()
    {
        return workDetailsService.getWorkDetailsForEmbData();
    }
}
