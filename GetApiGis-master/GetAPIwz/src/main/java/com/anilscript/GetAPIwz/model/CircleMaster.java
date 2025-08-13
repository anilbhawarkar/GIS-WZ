package com.anilscript.GetAPIwz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "circle_master", schema = "auth")
@Getter
@Setter
public class CircleMaster {
    @Id
    @Column(name="circle_id")
    private int circleId;

    @Column(name="state_id")
    private int stateId;

    @Column(name = "circle_name")
    private String circleName;

    @Column(name = "region_id")
    private int regionId;

    @Column(name = "circle_code")
    private String circleCode;

    @Column(name = "region_code")
    private String regionCode;

}
