package com.anilscript.GetAPIwz.repository;


import com.anilscript.GetAPIwz.model.WorkDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkDetailsRepository  extends JpaRepository<WorkDetails,Long> {

    @Query("select locationCode from LocationMaster")
    List<String> findAllDcCodes();

//    @Query(value = "select dc_code from public.work_details where feeder_id = :feeder_id", nativeQuery = true)
//    String findAllByfeederId(@Param("feeder_id") String feeder_id);

//    @Query(value = "SELECT dc_code FROM public.work_details WHERE feeder_id = :feederId", nativeQuery = true)
//    List<String> getDcCodesByFeederId(String feederId);

//    @Query(value = "select * from public.work_details w where w.feeder_id= :feeder_id", nativeQuery = true)
//    List<String> getDcCodeFromFdrId(@Param("feeder_id") String feeder_id);


    @Query("SELECT w.dcCode FROM WorkDetails w WHERE w.feederId = :feederId")
    String getDcCodeFromFdrId(@Param("feederId") String feederId);

//    @Query("SELECT w FROM WorkDetails w WHERE w.work_status =''")
//    List<WorkDetails> getWorkDetailsForEmbData();
}
