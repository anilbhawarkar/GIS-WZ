package com.anilscript.GetAPIwz.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "office_geo_master")
public class OfficeGeoMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto primary key

    @Column(name = "sw_locid")
    private String swlocid;

    @Column(name = "loc_type")
    private String loctype;

    @Column(name = "under_office_code")
    private String underOfficeCode;

    @Column(name = "under_office_name")
    private String underOfficeName;

    @Column(name = "location_erp")
    private String locationErp;

    @Column(name = "location_erp_under_office")
    private String locationErpUnderOffice;

    @Column(name = "name_of_location")
    private String nameOfLocation;

    @Column(name = "hr_id")
    private String hrid;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "region")
    private String region;

    @Column(name = "circle")
    private String circle;

    @Column(name = "division")
    private String division;

    @Column(name = "dc")
    private String dc;

    @Column(name = "dc_code")
    private String dcCode;

    @Column(name = "status")
    private String status;

    @Column(name = "verify_date")
    private LocalDate verifyDate;

    @Column(name = "verify_imie")
    private String verifyImie;

    @Column(name = "verify_by_id")
    private String verifyById;

    @Column(name = "water_supply")
    private String waterSupply;

    @Column(name = "type_of_road")
    private String typeOfRoad;

    @Column(name = "compound_wall")
    private String compoundWall;

    @Column(name = "construction_year")
    private String constructionYear;

//    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    @Column(name = "building_image", columnDefinition = "BYTEA")
//    private byte[] buildingImage;



    // Store only the file path or URL, not the actual image data
    @Column(name = "building_image")
    private String buildingImagePath;


    @Column(name = "project_number")
    private String projectNumber;

    @Column(name = "work_type")
    private String workType;

}
