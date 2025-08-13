package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.OfficeGeoMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeGeoRepository extends JpaRepository<OfficeGeoMaster, Long> {
    List<OfficeGeoMaster> findByCircle(String circle);
}
