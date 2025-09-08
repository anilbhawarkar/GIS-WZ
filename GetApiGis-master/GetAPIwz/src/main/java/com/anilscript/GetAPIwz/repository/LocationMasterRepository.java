package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationMasterRepository extends JpaRepository<LocationMaster, String> {


    LocationMaster findByLocationCode(String locationCode);

    boolean existsByLocationCode(String locationCode);


    boolean existsByDivisionCode(String locationCode);


    List<LocationMaster> findByDivisionCode(String locationCode);

    boolean existsByCircleCode(String locationCode);

    List<LocationMaster> findByCircleCode(String locationCode);

    List<LocationMaster> findByRegionCode(String locationCode);

    boolean existsByRegionCode(String locationCode);
}
