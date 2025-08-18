package com.anilscript.GetAPIwz.model;

//public class DTRModel {
//}
//package com.example.electric.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Data
public class DTRModel {

    @Id
    @Column(name = "ogc_fid", nullable = false)
    private Integer ogcFid;

    @Column(name = "ancillaryr")
    private Integer ancillaryr;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "subtypecd")
    private Long subtypecd;

    @Column(name = "feeder_code")
    private String feederCode;

    @Column(name = "feeder_name")
    private String feederName;

    @Column(name = "operatingv")
    private Long operatingv;

    @Column(name = "comments")
    private String comments;

    @Column(name = "jobid")
    private String jobid;

    @Column(name = "installati")
    private LocalDate installati;

    @Column(name = "electrictr")
    private Long electrictr;

    @Column(name = "feederinfo")
    private Long feederinfo;

    @Column(name = "symbolrota")
    private BigDecimal symbolrota;

    @Column(name = "facilityid")
    private String facilityid;

    @Column(name = "status")
    private String status;

    @Column(name = "htconfigur")
    private String htconfigur;

    @Column(name = "ltconfigur")
    private String ltconfigur;

    @Column(name = "htgroundre")
    private BigDecimal htgroundre;

    @Column(name = "ltgroundre")
    private BigDecimal ltgroundre;

    @Column(name = "htground_1")
    private BigDecimal htground1;

    @Column(name = "ltground_1")
    private BigDecimal ltground1;

    @Column(name = "htprotecti")
    private String htprotecti;

    @Column(name = "locationty")
    private String locationty;

    @Column(name = "overloadli")
    private BigDecimal overloadli;

    @Column(name = "labeltext")
    private String labeltext;

    @Column(name = "coolingtyp")
    private String coolingtyp;

    @Column(name = "htratedvol")
    private Long htratedvol;

    @Column(name = "ltratedvol")
    private Long ltratedvol;

    @Column(name = "tapstype")
    private String tapstype;

    @Column(name = "volumeoilt")
    private BigDecimal volumeoilt;

    @Column(name = "manufactur")
    private String manufactur;

    @Column(name = "yearmanufa")
    private LocalDate yearmanufa;

    @Column(name = "maxtap")
    private BigDecimal maxtap;

    @Column(name = "mintap")
    private BigDecimal mintap;

    @Column(name = "step")
    private String step;

    @Column(name = "adoptedtap")
    private BigDecimal adoptedtap;

    @Column(name = "vectorgrou")
    private String vectorgrou;

    @Column(name = "normaltapn")
    private BigDecimal normaltapn;

    @Column(name = "earthtype")
    private String earthtype;

    @Column(name = "typeofbush")
    private String typeofbush;

    @Column(name = "oillevelin")
    private String oillevelin;

    @Column(name = "breatherav")
    private String breatherav;

    @Column(name = "existingas")
    private String existingas;

    @Column(name = "ratedkva")
    private Long ratedkva;

    @Column(name = "phasedesig")
    private Long phasedesig;

    @Column(name = "nominalvol")
    private Long nominalvol;

    @Column(name = "ltprotecti")
    private String ltprotecti;

    @Column(name = "ltvoltage")
    private Long ltvoltage;

    @Column(name = "switchtype")
    private String switchtype;

    @Column(name = "owner")
    private String owner;

    @Column(name = "grossweigh")
    private BigDecimal grossweigh;

    @Column(name = "cust_class")
    private String custClass;

    @Column(name = "ironloss")
    private BigDecimal ironloss;

    @Column(name = "copperloss")
    private BigDecimal copperloss;

    @Column(name = "lvfeederpr")
    private String lvfeederpr;

    @Column(name = "transmount")
    private String transmount;

    @Column(name = "coretype")
    private String coretype;

    @Column(name = "ndfis_code")
    private String ndfisCode;

    @Column(name = "capacity")
    private BigDecimal capacity;

    @Column(name = "htvoltage")
    private Long htvoltage;

    @Column(name = "impedence")
    private BigDecimal impedence;

    @Column(name = "relsupport")
    private String relsupport;

    @Column(name = "relundergr")
    private String relundergr;

    @Column(name = "loadtapcha")
    private String loadtapcha;

    @Column(name = "relelectri")
    private String relelectri;

    @Column(name = "serialno")
    private String serialno;

    @Column(name = "weightofoi")
    private String weightofoi;

    @Column(name = "corewindin")
    private String corewindin;

    @Column(name = "ratedkwa65")
    private Long ratedkwa65;

    @Column(name = "htcurrent")
    private String htcurrent;

    @Column(name = "ltcurrent")
    private String ltcurrent;

    @Column(name = "coilweight")
    private String coilweight;

    @Column(name = "standbyava")
    private String standbyava;

    @Column(name = "peakload")
    private String peakload;

    @Column(name = "streetligh")
    private String streetligh;

    @Column(name = "earthingco")
    private String earthingco;

    @Column(name = "boxavailab")
    private String boxavailab;

    @Column(name = "dtridno")
    private String dtridno;

    @Column(name = "noofmainca")
    private Long noofmainca;

    @Column(name = "noofdistri")
    private Long noofdistri;

    @Column(name = "repaired")
    private String repaired;

    @Column(name = "estimateno")
    private String estimateno;

    @Column(name = "estimatesa")
    private LocalDate estimatesa;

    @Column(name = "ponumber")
    private String ponumber;

    @Column(name = "purchaseda")
    private LocalDate purchaseda;

    @Column(name = "commission")
    private LocalDate commission;

    @Column(name = "guaranteee")
    private LocalDate guaranteee;

    @Column(name = "repairedby")
    private String repairedby;

    @Column(name = "protection")
    private String protection;

    @Column(name = "temp_surve")
    private String tempSurve;

    @Column(name = "ltcktfeede")
    private String ltcktfeede;

    @Column(name = "location_n")
    private String locationN;

    @Column(name = "coiltype")
    private String coiltype;

    @Column(name = "workorderi")
    private String workorderi;

    @Column(name = "seqnoinfee")
    private Long seqnoinfee;

    @Column(name = "paintcode")
    private String paintcode;

    @Column(name = "inventory_")
    private String inventory;

    @Column(name = "sealed")
    private String sealed;

    @Column(name = "projectno")
    private String projectno;

    @Column(name = "taskno")
    private String taskno;

    @Column(name = "assetlinei")
    private String assetlinei;

    @Column(name = "wkb_geometry")
    private Geometry wkbGeometry;

    @Column(name = "town_id")
    private String townId;

    @Column(name = "subdivision_id")
    private String subdivisionId;

    @Column(name = "division_id")
    private String divisionId;

    @Column(name = "circle_id")
    private String circleId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_datetime")
    private LocalDate createdDatetime;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_datetime")
    private LocalDate modifiedDatetime;

    @Column(name = "dtr_status")
    private Integer dtrStatus;

    @Column(name = "dtr_code")
    private String dtrCode;

    @Column(name = "dtr_name")
    private String dtrName;

    @Column(name = "dtr_phase")
    private Integer dtrPhase;

    @Column(name = "dtr_capacity")
    private Integer dtrCapacity;

    @Column(name = "dtr_make")
    private String dtrMake;

    @Column(name = "dtr_serial_number")
    private String dtrSerialNumber;

    @Column(name = "dtr_metered")
    private Integer dtrMetered;

    @Column(name = "meter_make")
    private Integer meterMake;

    @Column(name = "meter_serial_number")
    private String meterSerialNumber;

    @Column(name = "meter_capacity")
    private Integer meterCapacity;

    @Column(name = "ct_ratio")
    private Integer ctRatio;

    @Column(name = "pole_code")
    private String poleCode;

    @Column(name = "sub_station_code")
    private String subStationCode;

    @Column(name = "parent_pole_code")
    private String parentPoleCode;

    @Column(name = "pole_ab_switch")
    private Integer poleAbSwitch;

    @Column(name = "pole_type")
    private Integer poleType;

    @Column(name = "support_structure_type")
    private Integer supportStructureType;

    @Column(name = "conductor_type")
    private Integer conductorType;

    @Column(name = "conductor_size")
    private Integer conductorSize;

    @Column(name = "objectid")
    private String objectid;

    @Column(name = "feeder_type")
    private String feederType;

    @Column(name = "survey_type")
    private String surveyType;

    @Column(name = "dtr_capacity_value")
    private Integer dtrCapacityValue;

    @Column(name = "dtr_unique_code")
    private String dtrUniqueCode;

    @Column(name = "subdivision_ids")
    private String subdivisionIds;

    @Column(name = "circle_name")
    private String circleName;

    @Column(name = "division_name")
    private String divisionName;

    @Column(name = "subdivision_name")
    private String subdivisionName;

    @Column(name = "town_name")
    private String townName;

    @Column(name = "is_inside_substation")
    private Boolean isInsideSubstation;

    @Column(name = "ss_name")
    private String ssName;

    @Column(name = "ss_code")
    private String ssCode;

    @Column(name = "wkb_geometry_bck")
    private Geometry wkbGeometryBck;

    @Column(name = "geom")
    private String geom;

    @Column(name = "feeder_code_bck")
    private String feederCodeBck;

    @Column(name = "feeder_name_bck")
    private String feederNameBck;

    @Column(name = "sub_station_code_test")
    private String subStationCodeTest;

    @Column(name = "data_type")
    private String dataType;

    @Column(name = "dtr_metered_present")
    private String dtrMeteredPresent;

    @Column(name = "meter_make_present")
    private String meterMakePresent;

    @Column(name = "meter_capacity_present")
    private String meterCapacityPresent;

    @Column(name = "ct_ratio_present")
    private String ctRatioPresent;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "dtr_capacity_present")
    private String dtrCapacityPresent;

    @Column(name = "no_of_consumer_connected")
    private Integer noOfConsumerConnected;

    @Column(name = "connected_load_of_consumers")
    private BigDecimal connectedLoadOfConsumers;

    @Column(name = "created_by_username")
    private String createdByUsername;

    @Column(name = "support_structure_type_present")
    private String supportStructureTypePresent;

    @Column(name = "pole_type_present")
    private String poleTypePresent;

    @Column(name = "conductor_type_present")
    private String conductorTypePresent;

    @Column(name = "pole_ab_switch_present")
    private String poleAbSwitchPresent;

    @Column(name = "conductor_size_present")
    private String conductorSizePresent;

    @Column(name = "dtr_phase_present")
    private String dtrPhasePresent;

    @Column(name = "scheme_type")
    private Integer schemeType;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "is_mobile_servey")
    private Boolean isMobileServey;

    @Column(name = "wkb_geometry_json")
    private String wkbGeometryJson;

    @Column(name = "sub_station_code_bak")
    private String subStationCodeBak;

    @Column(name = "actiontimestamp")
    private LocalDateTime actiontimestamp;

    @Column(name = "groupid")
    private String groupid;

    @Column(name = "dtr_length_existing")
    private BigDecimal dtrLengthExisting;

    @Column(name = "dtr_length_proposed")
    private BigDecimal dtrLengthProposed;
}
