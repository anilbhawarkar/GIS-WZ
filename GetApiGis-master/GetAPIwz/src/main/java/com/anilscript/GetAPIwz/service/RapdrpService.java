package com.anilscript.GetAPIwz.service;

import com.anilscript.GetAPIwz.model.*;
import com.anilscript.GetAPIwz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RapdrpService {

    @Autowired
    CircleMasterRepository circleMasterRepository;

    @Autowired
    DivisionMasterRepository divisionMasterRepository;

    @Autowired
    DistributionCenterRepository distributionCenterRepository;

    @Autowired
    RapdrpRepository rapdrpRepository;

    @Autowired
    LocationMasterRepository locationMasterRepository;



    public List<CircleMaster> getCircleListAllNew() {
        return circleMasterRepository.findAll();
    }
    public List<CircleMaster> getCircleList(String regionCode) {
        return circleMasterRepository.findByRegionCode(regionCode);
    }

    public List<DivisionMaster> getDivisionListAllNew() {
        return divisionMasterRepository.findAll();
    }

//    public List<DivisionMaster> getDivisionList(int circleCode) {
//        return divisionMasterRepository.findAllByCircleCode(circleCode);
//    }

    public List<DistributionCenterMaster> getDistributionCenterList() { return distributionCenterRepository.findAll();    }

    public List<Dashboard> getDashboardSearch(RapdrpLocationModel feederSearch) {
        return rapdrpRepository.getDashboardSearch(feederSearch);
    }

    public List<FeederDtrCapacity> getDtrCapacityWiseCount(RapdrpLocationModel feederSearch) {
        return rapdrpRepository.getDtrCapacityWiseCount(feederSearch);
    }

    public List<MpSubStation> getSubStation(RapdrpLocationModel feederSearch) {
        return rapdrpRepository.getSubStation(feederSearch);
    }

    public List<FeederSurveyCountModel> getFeederCountDashboard_11kv(SurveySearchModel feederSearch) {
        return rapdrpRepository.getFeederCountDashboard_11kv(feederSearch);
    }

    public Object getRapdrpDTRMapview(RapdrpLocationModel model) {
        return rapdrpRepository.getRapdrpDTRMapview(model);
    }

    public Object getRapdrpPole11kvMapview(RapdrpLocationModel model) {
       return rapdrpRepository.getRapdrpPole11kvMapview(model);
    }

    public Object updateEstimateNo(UpdateEstimateModel model) {
//        String code_of_distribution_center = model.getCode_of_distribution_center();
//        String code_of_feeder = model.getCode_of_feeder();
//        String pole_code= model.getPole_code();
//        String estimateno= model.getEstimateno();
        return rapdrpRepository.updateEstimateNo(model);
    }

    public UpdateEstimateModel getPoleData(UpdateEstimateModel model)
    {
        return rapdrpRepository.getPoleData(model);
    }

    public List<DistributionCenterMaster> getDcList(String division) {

        return rapdrpRepository.getDcList(division);
    }


    public List<DivisionMaster> getDivisionList(String circleCode) {
        return divisionMasterRepository.findAllByCircleCode(circleCode);
    }

    public List<DistributionCenterMaster> getDcListByDivision(String divisionCode) {
        return distributionCenterRepository.findAllByDivisionCode(divisionCode);
    }

    public List<SupportStructureModel> getDataFromEstimateNo(String estimateno) {
        return rapdrpRepository.getDataFfromEstimateno(estimateno);
    }

    public List<FeederSurveyCountModel> getFeederCountDashboard_33kv(SurveySearchModel feederSearch) {
        return rapdrpRepository.getFeederCountDashboard_33kv(feederSearch);
    }

    public List<feeder_Dtr_Capacity> getDtrCapacityCount_FeederLength(RapdrpLocationModel model) {
        return rapdrpRepository.getDtrCapacityCount_FeederLength(model);
    }

    public Object getRapdrp33kvSubstationMapview(RapdrpLocationModel model) {
        return rapdrpRepository.getRapdrp33kvSubstationMapview(model);
    }

    public Object getRapdrp132kvSubstationMapview(RapdrpLocationModel model) {
        return rapdrpRepository.getRapdrp132kvSubstationMapview(model);
    }

    public Object getRapdrp33kvHTLineMapview(RapdrpLocationModel model) {
        return rapdrpRepository.getRapdrp33kvHTLineMapview(model);
    }

    public Object getRapdrpPole33kvMapview(RapdrpLocationModel model) {
        return rapdrpRepository.getRapdrpPole33kvMapview(model);
    }

    public Object getRapdrpConsumerMapview(RapdrpLocationModel model) {
        return rapdrpRepository.getRapdrpConsumerMapview(model);
    }

    public List<DashboardSummary_33kvModel> getSS_CapacityPTRList(String code_of_feeder) {
        return rapdrpRepository.getSS_CapacityPTRList(code_of_feeder);
    }

    public LocationMaster getLocationMaster(String locationCode)
    {
//        boolean exists = userRepository.existsByEmail("test@email.com");
        boolean exists = locationMasterRepository.existsByLocationCode(locationCode);

        System.out.println("Data Status for DC = "+exists);

        if(exists) {
            LocationMaster data = locationMasterRepository.findByLocationCode(locationCode);
            return data;
        }

        exists = locationMasterRepository.existsByDivisionCode(locationCode);
        System.out.println("Data Status for Division = "+exists);

        if(exists) {
            List<LocationMaster> data = locationMasterRepository.findByDivisionCode(locationCode);
            for (LocationMaster location : data)
            {
                return location;
            }
        }

        exists = locationMasterRepository.existsByCircleCode(locationCode);
        System.out.println("Data Status for Circle = "+exists);
        if(exists) {
            List<LocationMaster> data = locationMasterRepository.findByCircleCode(locationCode);
            for (LocationMaster location : data)
            {
                return location;
            }
        }
        return null;
    }

    public List<Map<String, Object>> getDtrList(String feederCode) {
        return rapdrpRepository.findByFeederCode(feederCode);
    }

    public List<Map<String, Object>> getDtrMapView(String dtrUniqueCode) {
        return rapdrpRepository.getDtrMapView(dtrUniqueCode);
    }

    public Object getLTkvLineMapview(RapdrpLocationModel model) {
        return rapdrpRepository.getLTkvLineMapview(model);
    }
}
