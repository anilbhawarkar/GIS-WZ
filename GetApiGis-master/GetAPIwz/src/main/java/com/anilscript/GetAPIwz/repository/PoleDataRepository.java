package com.anilscript.GetAPIwz.repository;
import com.anilscript.GetAPIwz.model.PoleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository("pole_data")
public interface PoleDataRepository extends JpaRepository<PoleData, Long> {

    List<PoleData> findAllByDcCode(String dcCode); // ✅ Will work with dcCode field

//    @Query("SELECT w.dcCode FROM WorkDetails w WHERE w.feederId = :feederId")

//    @Query("select dc_code from work_details where feeder_id= :fdrId")
//    String findAllBydcCode(@Param("fdrID") String fdrId);


//    @Query("SELECT p FROM PoleData p WHERE p.dc_code = :dcCode")
//    List<PoleData> getEmbDataDc(
//            @Param("dc_code") String dcCode
//    );

//    List<PoleData> findAllByDcCode(String dcCode);
}


//@Query("select sum(amount) from #{#entityName} where consumerNo= :consumerNo and code= :code")
//public BigDecimal findTotalAmountByConsumerNoAndCode(@Param("consumerNo") String consumerNo, @Param("code") int code);