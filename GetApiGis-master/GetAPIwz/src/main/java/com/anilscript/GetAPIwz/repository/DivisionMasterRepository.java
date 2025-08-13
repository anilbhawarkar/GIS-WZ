package com.anilscript.GetAPIwz.repository;

import com.anilscript.GetAPIwz.model.DivisionMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionMasterRepository extends JpaRepository<DivisionMaster, Integer> {
    List<DivisionMaster> findAllByCircleCode(String circleCode);

//    void findAllByCircleCode(int circleCode);
}
