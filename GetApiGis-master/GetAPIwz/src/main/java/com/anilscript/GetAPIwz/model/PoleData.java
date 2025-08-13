package com.anilscript.GetAPIwz.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pole_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PoleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feeder_id")
    private String feederId;

    @Column(name = "dev_pole_id")
    private String devPoleId;

    @Column(name = "fdr_name")
    private String fdrName;

    @Column(name = "pole_lat")
    private String poleLat;

    @Column(name = "pole_long")
    private String poleLong;

    @Column(name = "sch_no")
    private String schNo;

    @Column(name = "dc_code")
    private String dcCode;
}


