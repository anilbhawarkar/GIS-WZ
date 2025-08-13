package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.SubStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubStationRepository extends JpaRepository<SubStation, Long> {
}
