package com.anilscript.GetAPIwz.controller;
import com.anilscript.GetAPIwz.exception.InvalidInputException;
import com.anilscript.GetAPIwz.exception.ResourceNotFoundException;
import com.anilscript.GetAPIwz.model.*;
import com.anilscript.GetAPIwz.service.RapdrpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class RapdrpController {

    @Autowired
    RapdrpService rapdrpService;

    private static final Logger logger = LoggerFactory.getLogger(RapdrpController.class);

    @GetMapping("/test")
    public String testLogging() {
        logger.info("INFO log");
        logger.debug("DEBUG log");
        logger.error("ERROR log");
        return "Logging works!";
    }

//    @GetMapping("/newcircles")
//    public List<CircleMaster> getCircleListAllNew()
//    {
//        return rapdrpService.getCircleListAllNew();
//    }

    //    @GetMapping("/newdivisions")
//    public List<DivisionMaster> getDivisionListAllNew()
//    {
//        return rapdrpService.getDivisionListAllNew();
//    }


    //    @GetMapping("/dcs")
//    public List<DistributionCenterMaster> getDistributionCenterList() { return rapdrpService.getDistributionCenterList();}
//
//    @GetMapping("/newdc")
//    public List<DistributionCenterMaster> getDcList(@RequestParam("division") String division)
//    {
//        System.out.println("In getDCList API"+"division from user-"+division);
//        return rapdrpService.getDcList(division);
//    }

    //simple way to write any function
    /*
    @GetMapping("/circles")
    public ResponseEntity<List<CircleMaster>> getCircleList(@RequestParam String regionCode)
    {
        final String methodName = "getCircleList() :";
        logger.info(methodName + "called for division code :" + regionCode);

        //Validate input
        if(regionCode == null | regionCode.trim().isEmpty())
        {
            logger.warn(methodName + "Invalid region code provided");
            return ResponseEntity.badRequest().build();
        }

        try{
            // Log request
            logger.info(methodName + "fetching circles for region code: "+regionCode);

            // call service layer
            List<CircleMaster> circleList = rapdrpService.getCircleList(regionCode);

            //handle empty result
            if (circleList == null | circleList.isEmpty())
            {
                logger.info(methodName + "No circles found for region code:"+regionCode);
                return ResponseEntity.notFound().build();//404 not found
            }

            // return data with 200 ok
            return ResponseEntity.ok(circleList);
        }
        catch (Exception ex)
        {
            logger.error(methodName + "Error occurred while fetching circles", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
*/
    @GetMapping("/circles")
    public ResponseEntity<List<CircleMaster>> getCircleList(@RequestParam String regionCode)
    {
        final String methodName = "getCircleList()";
        logger.info("{}  called for region code : {}",methodName,regionCode);

        // input validation
        if(regionCode == null || regionCode.trim().isEmpty())
        {
            throw new InvalidInputException("Region code can not be empty");
        }

        logger.info("Fetching circles for region code: {}",regionCode);

        List<CircleMaster> circleList = rapdrpService.getCircleList(regionCode);

        if(circleList == null || circleList.isEmpty())
        {
            throw new ResourceNotFoundException("No circle found for region code: "+ regionCode);
        }
        return ResponseEntity.ok(circleList);
    }


    @RequestMapping("/divisions")
    public ResponseEntity<List<DivisionMaster>> getDivisionList(@RequestParam String circleCode)
    {
        final String methodName = "getDivisionList()";
        logger.info(methodName + "is called for circle code :"+circleCode);

        // input validation
        if(circleCode == null || circleCode.isEmpty())
        {
            throw new InvalidInputException("Circle code can not be empty");
        }

        logger.info(methodName+" Fetching divisions for circle code: "+circleCode);

        List<DivisionMaster> divisionList = rapdrpService.getDivisionList(circleCode);
        if (divisionList == null || divisionList.isEmpty())
        {
            throw new ResourceNotFoundException("No Division found for circle code:"+ circleCode);
        }
        return ResponseEntity.ok(divisionList);
    }


    @GetMapping("/dc")
    public List<DistributionCenterMaster> getDcListByDivision(@RequestParam String divisionCode)
    {
        final String methodName = "getDcListByDivision()";
        logger.info(methodName + "is called for division code: "+divisionCode);

        // input validation
        if(divisionCode ==  null || divisionCode.isEmpty())
        {
            throw new InvalidInputException("Division code can not be empty");
        }

        logger.info(methodName + "Fetching Distribution center for division code: "+ divisionCode);


         List<DistributionCenterMaster> dcList = rapdrpService.getDcListByDivision(divisionCode);

         if(dcList == null || dcList.isEmpty())
         {
             throw new ResourceNotFoundException("No Distribution Center found for division code: "+divisionCode);
         }
        return dcList;
    }


    @PostMapping("/getdashboardsearch")
    public List<Dashboard> getDashboardSearch(@RequestBody RapdrpLocationModel feederSearch)
    {
        final String methodName = "getDashboardSearch()";
        logger.info(methodName+" called for parameter :"+feederSearch.toString());

        if (feederSearch == null)
        {
            throw new InvalidInputException("Given parameters are null");
        }
        List<Dashboard> dashboards = rapdrpService.getDashboardSearch(feederSearch);

        if (dashboards == null || dashboards.isEmpty())
        {
            throw new ResourceNotFoundException("No Dashboard found for parameter:"+feederSearch.toString());
        }
        return dashboards;
    }

    @PostMapping("/getdtrcapacitywisecount")
    public List<FeederDtrCapacity> getDtrCapacityWiseCount(@RequestBody RapdrpLocationModel feederSearch)
    {
        final String methodName = "getDtrCapacityWiseCount()";
        logger.info(methodName+" called for parameters: "+feederSearch);

        if (feederSearch == null)
        {
            throw new InvalidInputException("Given parameters are null");
        }

        List<FeederDtrCapacity> data = rapdrpService.getDtrCapacityWiseCount(feederSearch);

        if(data == null || data.isEmpty())
        {
            throw new ResourceNotFoundException("DTR wise capacity wise count not found");
        }
        return data;
    }

    @PostMapping("/getsubstation")
    public List<MpSubStation> getSubStation(@RequestBody RapdrpLocationModel feederSearch)
    {
        final String methodName = "getSubStation()";
        logger.info(methodName+" called for parameter :"+feederSearch.toString());

        if (feederSearch == null)
        {
            throw new InvalidInputException("Given parameters are null");
        }

        List<MpSubStation> data = rapdrpService.getSubStation(feederSearch);

        if(data == null || data.isEmpty())
        {
            throw new ResourceNotFoundException("No substation found for given parameters: "+feederSearch.toString());
        }
        return data;
    }

    @PostMapping("/getfeedercountdashboard_11kv")
    public List<FeederSurveyCountModel> getFeederCountDashboard_11kv(@RequestBody SurveySearchModel feederSearch)
    {
        final String methodName = "getFeederCountDashboard_11kv()";
        logger.info(methodName+" called for parameter :"+feederSearch.toString());

        if (feederSearch == null)
        {
            throw new InvalidInputException("Given parameters are null");
        }

        List<FeederSurveyCountModel> data = rapdrpService.getFeederCountDashboard_11kv(feederSearch);

        if (data == null || data.isEmpty())
        {
            throw new ResourceNotFoundException("Feeder count dashboard for 11kv not found for given parameters: "+feederSearch.toString());
        }
        return data;
    }

    @PostMapping("/getRapdrpDTRMapview")
    public Object getRapdrpDTRMapView(@RequestBody RapdrpLocationModel model)
    {
        final String methodName = "getRapdrpDTRMapView()";
        logger.info(methodName+" called for parameter :"+model.toString());

        if (model == null)
        {
            throw new InvalidInputException("Given parameters are null");
        }

        Object data = rapdrpService.getRapdrpDTRMapview(model);

        if (data == null)
        {
            throw new ResourceNotFoundException("No Mapview available for given parameters: "+model);
        }
        return data;
    }

    @PostMapping("/getRapdrpPole11kvMapview")
    public Object getRapdrpPole11kvMapview(@RequestBody RapdrpLocationModel model)
    {
        final String methodName = "getRapdrpPole11kvMapview()";
        logger.info("{} called for parameter {}",methodName,model);

        if(model == null)
        {
            throw new InvalidInputException("Given parameters are null");
        }

        Object data = rapdrpService.getRapdrpPole11kvMapview(model);

        if(data == null)
        {
            throw new ResourceNotFoundException("No Mapview available for given parameters: "+model);
        }
        return rapdrpService.getRapdrpPole11kvMapview(model);
    }

    @PutMapping("/updateestimateno")
    public Object updateEstimateNo(@RequestBody UpdateEstimateModel model)
    {
       return rapdrpService.updateEstimateNo(model);
//        return "message: Estimate Number updated successfully"
    }

    @GetMapping("/getpoledata")
    public UpdateEstimateModel getPoleData(@RequestBody UpdateEstimateModel model)
    {
        return rapdrpService.getPoleData(model);
    }

    @GetMapping("/getdataestimate")
    public List<SupportStructureModel> getDataFromEstimateNo(@RequestParam String estimateno)
    {
        System.out.println("Estimate Number = "+estimateno);
        return rapdrpService.getDataFromEstimateNo(estimateno);
    }

    @PostMapping("/getfeedercountdashboard_33kv")
    public List<FeederSurveyCountModel> getFeederCountDashboard_33kv(@RequestBody SurveySearchModel feederSearch) {
        return this.rapdrpService.getFeederCountDashboard_33kv(feederSearch);
    }

    @PostMapping("/getdtrcapacitycount_feederlength")
    public List<feeder_Dtr_Capacity> getDtrCapacityCount_FeederLength(@RequestBody RapdrpLocationModel model) {
        return this.rapdrpService.getDtrCapacityCount_FeederLength(model);
    }

    @PostMapping("/getRapdrp33kvSubstationMapview")
    public Object getRapdrp33kvSubstationMapview(@RequestBody RapdrpLocationModel model) {
        return rapdrpService.getRapdrp33kvSubstationMapview(model);
    }

    @PostMapping("/getRapdrp132kvSubstationMapview")
    public Object getRapdrp132kvSubstationMapview(@RequestBody RapdrpLocationModel model) {
        return rapdrpService.getRapdrp132kvSubstationMapview(model);
    }

    @PostMapping("/getRapdrp33kvHTLineMapview")
    public Object getRapdrp33kvHTLineMapview(@RequestBody RapdrpLocationModel model) {
        return rapdrpService.getRapdrp33kvHTLineMapview(model);
    }

    @PostMapping("/getRapdrpPole33kvMapview")
    public Object getRapdrpPole33kvMapview(@RequestBody RapdrpLocationModel model) {
        return rapdrpService.getRapdrpPole33kvMapview(model);
    }

    @PostMapping("/getRapdrpConsumerMapview")
    public Object getRapdrpConsumerMapview(@RequestBody RapdrpLocationModel model) {
        return rapdrpService.getRapdrpConsumerMapview(model);
    }

    @GetMapping("/getsscapacityptrlist/{code_of_feeder}")
    public List<DashboardSummary_33kvModel> getSS_CapacityPTRList(@PathVariable String code_of_feeder) {
        return rapdrpService.getSS_CapacityPTRList(code_of_feeder);
    }

    @GetMapping("/hierarchy")
    public LocationMaster gethierarchy(@RequestParam String distributionCenterCode)
    {
        final String methodName ="gethirarchy()";
        logger.info("{} called for the distribution code : {}", methodName,distributionCenterCode);

        if(distributionCenterCode == null || distributionCenterCode.trim().isEmpty())
        {
            throw new InvalidInputException("Distribution centre code can not be null or empty");
        }

        LocationMaster data = rapdrpService.getLocationMaster(distributionCenterCode);

        return data;
    }

    @GetMapping("/dtrList")
    public List<Map<String, Object>> getDtrList(@RequestParam String feeder_code)
    {
        final String methodName = "getDtrList()";
        logger.info("{} called for feeder code : {}",methodName, feeder_code);

        if(feeder_code == null || feeder_code.isEmpty())
        {
            throw  new InvalidInputException("Feeder code can not be null or empty");
        }

        List<Map<String, Object>> dtrList = rapdrpService.getDtrList(feeder_code);

        System.out.println("DTR list size = "+dtrList.size());
        return dtrList;
    }
}

