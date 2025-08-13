package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.DistributionCenterMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionCenterRepository extends JpaRepository<DistributionCenterMaster, Integer> {
    List<DistributionCenterMaster> findAllByDivisionCode(String divisionCode);
}
