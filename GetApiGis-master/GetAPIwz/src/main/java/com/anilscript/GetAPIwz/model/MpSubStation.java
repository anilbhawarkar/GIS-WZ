package com.anilscript.GetAPIwz.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "mpsubstation" , schema = "auth")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class MpSubStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_no")
    private Long serialNo;

    @Column(name = "region_name")
    private String regionName;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "circle_name")
    private String circleName;

    @Column(name = "circle_code")
    private String circleCode;

    @Column(name = "division_name")
    private String divisionName;

    @Column(name = "division_code")
    private String divisionCode;

    @Column(name = "sub_station_code")
    private String subStationCode;

    @Column(name = "sub_station_name")
    private String subStationName;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

}
