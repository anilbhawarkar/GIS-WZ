package com.anilscript.GetAPIwz.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "location_master")
@Getter
@Setter
@ToString
public class LocationMaster {


    @Column(name = "region_name")
    private String regionName;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "circle_code")
    private String circleCode;

    @Column(name = "circle_name")
    private String circleName;

    @Column(name="division_code")
    private String divisionCode;

    @Column(name="division_name")
    private String divisionName;

    @Id
    @Column(name = "location_code")
    private String locationCode;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "area_type")
    private String areaType;


}

