package com.anilscript.GetAPIwz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

@Data
@Entity
@Getter
@Setter
@Table(name = "distribution_center_master", schema = "auth")
public class DistributionCenterMaster {

    @Id
    @Column(name="distribution_center_id")
    private int distributionCenterId;

    @Column(name="division_id")
    private int divisionId;

    @Column(name="distribution_center_name")
    private String distributionCenterName;

    @Column(name="distribution_center_code")
    private String distributionCenterCode;

    @Column(name = "division_code")
    private String divisionCode;

    @Transient
    @JsonIgnore
    @Column(name="wkb_geometry")
    private Geometry geom;

    @Column(name="geomextent")
    private String extent;

    @Column(name="pointgeom")
    private String centroid;

}
