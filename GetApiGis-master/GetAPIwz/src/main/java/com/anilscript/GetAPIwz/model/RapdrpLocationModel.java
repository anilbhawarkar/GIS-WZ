package com.anilscript.GetAPIwz.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RapdrpLocationModel {
    public Integer LocationId;
    public String code_of_region;
    public String name_of_region;
    public String name_of_circle;
    public String code_of_circle;
    public String name_of_division;
    public String code_of_division;
    public String name_of_distribution_center;
    public String code_of_distribution_center;
    public String name_of_feeder;
    public String code_of_feeder;
    public String type_of_feeder;
    public String sub_station_code;
    public String code;
    public String lt_feeder_name;
    public String lt_feeder_code;
    public String type;
}
