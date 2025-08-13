package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationMasterRepository extends JpaRepository<LocationMaster, String> {


    LocationMaster findByLocationCode(String distributionCenterCode);
}
