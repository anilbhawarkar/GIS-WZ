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
@Table(name = "division_master", schema = "auth")
@Getter
@Setter
public class DivisionMaster {

    @Id
    @Column(name = "division_id")
    private int divisionId;

    @Column(name = "division_name")
    private String divisionName;

    @Column(name = "division_code")
    private String divisionCode;

    @Column(name = "circle_code")
    private String circleCode;

    @Column(name = "circle_id")
    private int circleId;
}
