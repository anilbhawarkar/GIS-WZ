package com.anilscript.GetAPIwz.loginModule.repository;

import com.anilscript.GetAPIwz.model.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationMaster, String> {

    @Query(value = "SELECT location_code FROM public.location_master WHERE division_code = :division", nativeQuery = true)
    List<String> findDcListByDivision(@Param("division") String division);
}
