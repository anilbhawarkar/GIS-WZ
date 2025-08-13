package com.anilscript.GetAPIwz.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "sub_station")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class SubStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_no")
    private Long serialNo;

    @Column(name = "circle_name")
    private String circleName;

    @Column(name = "division_name")
    private String divisionName;

    @Column(name = "sub_station_name")
    private String subStationName;

    @Column(name = "lattitude")
    private String lattitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "capacity_injectable")
    private String capacityInjectable;

}

