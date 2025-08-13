package com.anilscript.GetAPIwz.loginModule.repository;

import com.anilscript.GetAPIwz.loginModule.model.LocationDeatilsFromLocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDeatilsFromLocationModelRepository extends JpaRepository<LocationDeatilsFromLocationModel, Long> {

}
