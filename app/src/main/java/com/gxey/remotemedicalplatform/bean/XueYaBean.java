package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class XueYaBean {
    private String id;
    private String DeviceID;
    private String HealthID;
    private String Addtime;
    private String Remark;
    private String Systolic;
    private String Diastolic;
    private String SystolicRemark;
    private String DiastolicRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }

    public String getHealthID() {
        return HealthID;
    }

    public void setHealthID(String healthID) {
        HealthID = healthID;
    }

    public String getAddtime() {
        return Addtime;
    }

    public void setAddtime(String addtime) {
        Addtime = addtime;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getSystolic() {
        return Systolic;
    }

    public void setSystolic(String systolic) {
        Systolic = systolic;
    }

    public String getDiastolic() {
        return Diastolic;
    }

    public void setDiastolic(String diastolic) {
        Diastolic = diastolic;
    }

    public String getSystolicRemark() {
        return SystolicRemark;
    }

    public void setSystolicRemark(String systolicRemark) {
        SystolicRemark = systolicRemark;
    }

    public String getDiastolicRemark() {
        return DiastolicRemark;
    }

    public void setDiastolicRemark(String diastolicRemark) {
        DiastolicRemark = diastolicRemark;
    }
}
