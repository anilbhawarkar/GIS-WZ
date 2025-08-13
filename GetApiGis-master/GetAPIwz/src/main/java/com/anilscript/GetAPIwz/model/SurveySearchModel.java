package com.anilscript.GetAPIwz.model;

public class SurveySearchModel {
    public Integer search_type;
    public String code_of_region;
    public String code_of_circle;
    public String code_of_division;
    public String code_of_distribution_center;
    public String search_33KV_feeder;
    public String search_11KV_feeder;
    public String search_ltKV_feeder;
    public String end_date;
    public String start_date;
    public Integer page_no;
    public Integer page_size;
    public String feeder_code;

    public String feeder_code_lt;

    public String getFeeder_code_lt() {
        return feeder_code_lt;
    }

    public void setFeeder_code_lt(String feeder_code_lt) {
        this.feeder_code_lt = feeder_code_lt;
    }

    public String getSearch_ltKV_feeder() {
        return search_ltKV_feeder;
    }

    public void setSearch_ltKV_feeder(String search_ltKV_feeder) {
        this.search_ltKV_feeder = search_ltKV_feeder;
    }

    public String getFeeder_code() {
        return feeder_code;
    }

    public void setFeeder_code(String feeder_code) {
        this.feeder_code = feeder_code;
    }

    public Integer getSearch_type() {
        return search_type;
    }

    public void setSearch_type(Integer search_type) {
        this.search_type = search_type;
    }

    public String getCode_of_region() {
        return code_of_region;
    }

    public void setCode_of_region(String code_of_region) {
        this.code_of_region = code_of_region;
    }

    public String getCode_of_circle() {
        return code_of_circle;
    }

    public void setCode_of_circle(String code_of_circle) {
        this.code_of_circle = code_of_circle;
    }

    public String getCode_of_division() {
        return code_of_division;
    }

    public void setCode_of_division(String code_of_division) {
        this.code_of_division = code_of_division;
    }

    public String getCode_of_distribution_center() {
        return code_of_distribution_center;
    }

    public void setCode_of_distribution_center(String code_of_distribution_center) {
        this.code_of_distribution_center = code_of_distribution_center;
    }

    public String getSearch_33KV_feeder() {
        return search_33KV_feeder;
    }

    public void setSearch_33KV_feeder(String search_33KV_feeder) {
        this.search_33KV_feeder = search_33KV_feeder;
    }

    public String getSearch_11KV_feeder() {
        return search_11KV_feeder;
    }

    public void setSearch_11KV_feeder(String search_11KV_feeder) {
        this.search_11KV_feeder = search_11KV_feeder;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public Integer getPage_no() {
        return page_no;
    }

    public void setPage_no(Integer page_no) {
        this.page_no = page_no;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }
}
