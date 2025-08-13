package com.anilscript.GetAPIwz.model;

public class CompletionModel {
    public String region;
    public String circle;
    public String division;
    public String distributioncenter;
    public String feedername;
    public String feedercode;
    public String userid;
    public String username;
    public String requestdate;

    public String code_of_lt_kv_feeder;
    public String name_of_lt_kv_feeder;

    public Long row_no;
    public Long total_rows;

    public String getCode_of_lt_kv_feeder() {
        return code_of_lt_kv_feeder;
    }

    public void setCode_of_lt_kv_feeder(String code_of_lt_kv_feeder) {
        this.code_of_lt_kv_feeder = code_of_lt_kv_feeder;
    }

    public String getName_of_lt_kv_feeder() {
        return name_of_lt_kv_feeder;
    }

    public void setName_of_lt_kv_feeder(String name_of_lt_kv_feeder) {
        this.name_of_lt_kv_feeder = name_of_lt_kv_feeder;
    }

    public Long getTotal_rows() {
        return total_rows;
    }
    public void setTotal_rows(Long total_rows) {
        this.total_rows = total_rows;
    }
    public Long getRow_no() {
        return row_no;
    }
    public void setRow_no(Long row_no) {
        this.row_no = row_no;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getDistributioncenter() {
        return distributioncenter;
    }

    public void setDistributioncenter(String distributioncenter) {
        this.distributioncenter = distributioncenter;
    }

    public String getFeedername() {
        return feedername;
    }

    public void setFeedername(String feedername) {
        this.feedername = feedername;
    }

    public String getFeedercode() {
        return feedercode;
    }

    public void setFeedercode(String feedercode) {
        this.feedercode = feedercode;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(String requestdate) {
        this.requestdate = requestdate;
    }
}
