package com.anilscript.GetAPIwz.model;

import lombok.Data;


@Data
public class Dashboard {
    public String feeder_11kv_count;
    public String feeder_33kv_count;
    public String ss_33_11kv_count;
    public String length_33kv_existing;
    public String length_33kv_proposed;
    public String length_11kv_existing;
    public String length_11kv_proposed;
    public String length_lt_existing;
    public String length_lt_proposed;
    public String length_ul_11kv_existing;
    public String length_ul_11kv_proposed;
    public String length_ul_33kv_existing;
    public String length_ul_33kv_proposed;
    public String length_ul_ltkv_existing;
    public String length_ul_ltkv_proposed;
    public String total_dtr;
    public String job_pending_for_approval;
    public String job_assigned;
    public String job_completed;
    public String job_created;
    public String job_started_execution;
}
