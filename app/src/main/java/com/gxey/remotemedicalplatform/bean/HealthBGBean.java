package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class HealthBGBean {
    private String id;
    private String organizationname;
    private String measurementtime;
    private String measuringdoctorname;
    private String physicalexaminationid;
    private String userid;
    private String url;
    private String uname;
    private String fullname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeasurementtime() {
        return measurementtime;
    }

    public void setMeasurementtime(String measurementtime) {
        this.measurementtime = measurementtime;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getMeasuringdoctorname() {
        return measuringdoctorname;
    }

    public void setMeasuringdoctorname(String measuringdoctorname) {
        this.measuringdoctorname = measuringdoctorname;
    }

    public String getPhysicalexaminationid() {
        return physicalexaminationid;
    }

    public void setPhysicalexaminationid(String physicalexaminationid) {
        this.physicalexaminationid = physicalexaminationid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
