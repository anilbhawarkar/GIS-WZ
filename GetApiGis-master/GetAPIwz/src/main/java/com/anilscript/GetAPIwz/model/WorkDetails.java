package com.anilscript.GetAPIwz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "work_details")
public class WorkDetails {

    @Id
    private String feederId; // You can choose a unique identifier based on your data
    private String circleCode;
    private String divisionCode;
    private String dcCode;
    private String fdrName;
    private String ssId;
    private String schemeCode;
    private String workStatus;
    private Date creationDate;

    public String getFeederId() { return feederId;}

    public void setFeederId(String feederId) { this.feederId = feederId;}

    public String getCircleCode() { return circleCode;}

    public void setCircleCode(String circleCode) {this.circleCode = circleCode;}

    public String getDivisionCode() {return divisionCode;}

    public void setDivisionCode(String divisionCode) { this.divisionCode = divisionCode;}

    public String getDcCode() {return dcCode;}

    public void setDcCode(String dcCode) {this.dcCode = dcCode;}

    public String getFdrName() {return fdrName;}

    public void setFdrName(String fdrName) {this.fdrName =fdrName;}

    public String getSsId() {return ssId;}

    public void setSsId(String ssId) {this.ssId = ssId;}

    public String getSchemeCode() {return schemeCode;}

    public void setSchemeCode( String schemeCode) { this.schemeCode = schemeCode;}

    public String getWorkStatus() {return workStatus;}

    public void setWorkStatus(String workStatus) {this.workStatus= workStatus;}

    public Date getCreationDate() {return creationDate;}

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate;}

    @Override
    public String toString() {
        return "WorkDetails{" +
                "feederId='" + feederId + '\'' +
                ", circleCode='" + circleCode + '\'' +
                ", divisionCode='" + divisionCode + '\'' +
                ", dcCode='" + dcCode + '\'' +
                ", fdrName='" + fdrName + '\'' +
                ", ssId='" + ssId + '\'' +
                ", schemeCode='" + schemeCode + '\'' +
                ", workStatus='" + workStatus + '\'' +
                ", creationDate=" + creationDate +
                '}';


    }
}
