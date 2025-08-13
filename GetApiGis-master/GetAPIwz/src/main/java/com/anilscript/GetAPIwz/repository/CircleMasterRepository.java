package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.CircleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleMasterRepository extends JpaRepository<CircleMaster, Integer> {

      List<CircleMaster> findByRegionCode(String regionCode);
}
