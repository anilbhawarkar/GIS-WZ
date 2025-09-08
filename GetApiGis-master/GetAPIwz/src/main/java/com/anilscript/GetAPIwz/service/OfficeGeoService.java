package com.anilscript.GetAPIwz.service;

import com.anilscript.GetAPIwz.model.OfficeGeoMaster;
import com.anilscript.GetAPIwz.repository.OfficeGeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeGeoService {

    @Autowired
    OfficeGeoRepository officeGeoRepository;

    public OfficeGeoService(OfficeGeoRepository officeGeoRepository) {
        this.officeGeoRepository = officeGeoRepository;
    }

    public List<OfficeGeoMaster> getAllOffices() {
        return officeGeoRepository.findAll();
    }


    public Optional<OfficeGeoMaster> getOfficeById(Long id) {
        return officeGeoRepository.findById(id);
    }

//
//    public OfficeGeoMaster createOffice(OfficeGeoMaster officeGeoMaster) {
//        return officeGeoRepository.save(officeGeoMaster);
//    }
        public OfficeGeoMaster createOffice(OfficeGeoMaster officeGeoMaster) {
            return officeGeoRepository.save(officeGeoMaster);
        }



    public OfficeGeoMaster updateOffice(Long id, OfficeGeoMaster officeGeoMaster) {
        return officeGeoRepository.findById(id).map(existing -> {
            // Copy fields manually or use BeanUtils
            existing.setSwlocid(officeGeoMaster.getSwlocid());
            existing.setLoctype(officeGeoMaster.getLoctype());
            existing.setUnderOfficeCode(officeGeoMaster.getUnderOfficeCode());
            existing.setUnderOfficeName(officeGeoMaster.getUnderOfficeName());
            existing.setLocationErp(officeGeoMaster.getLocationErp());
            existing.setLocationErpUnderOffice(officeGeoMaster.getLocationErpUnderOffice());
            existing.setNameOfLocation(officeGeoMaster.getNameOfLocation());
            existing.setHrid(officeGeoMaster.getHrid());
            existing.setLat(officeGeoMaster.getLat());
            existing.setLng(officeGeoMaster.getLng());
            existing.setRegion(officeGeoMaster.getRegion());
            existing.setCircle(officeGeoMaster.getCircle());
            existing.setDivision(officeGeoMaster.getDivision());
            existing.setDc(officeGeoMaster.getDc());
            existing.setDcCode(officeGeoMaster.getDcCode());
            existing.setStatus(officeGeoMaster.getStatus());
            existing.setVerifyDate(officeGeoMaster.getVerifyDate());
            existing.setVerifyImie(officeGeoMaster.getVerifyImie());
            existing.setVerifyById(officeGeoMaster.getVerifyById());
            existing.setWaterSupply(officeGeoMaster.getWaterSupply());
            existing.setTypeOfRoad(officeGeoMaster.getTypeOfRoad());
            existing.setCompoundWall(officeGeoMaster.getCompoundWall());
            existing.setConstructionYear(officeGeoMaster.getConstructionYear());
            existing.setBuildingImagePath(officeGeoMaster.getBuildingImagePath());
            existing.setProjectNumber(officeGeoMaster.getProjectNumber());
            existing.setWorkType(officeGeoMaster.getWorkType());
            return officeGeoRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Office not found with id " + id));
    }


    public void deleteOffice(Long id) {
        if (!officeGeoRepository.existsById(id)) {
            throw new RuntimeException("Office not found with id " + id);
        }
        officeGeoRepository.deleteById(id);
    }

    public List<OfficeGeoMaster> getOfficeByCircleCode(String circle) {
        return officeGeoRepository.findByCircle(circle);
    }
}
