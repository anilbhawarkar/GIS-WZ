package com.anilscript.GetAPIwz.model;

import lombok.Data;

@Data
public class feeder_Dtr_Capacity {
    public String name;
    public String value;
    public String voltage;
    public String total_dtr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getTotal_dtr() {
        return total_dtr;
    }

    public void setTotal_dtr(String total_dtr) {
        this.total_dtr = total_dtr;
    }
}
