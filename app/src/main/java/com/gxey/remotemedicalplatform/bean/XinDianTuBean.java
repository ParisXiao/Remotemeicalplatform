package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class XinDianTuBean {
    private String id;
    private String DeviceID;
    private String HealthID;
    private String Addtime;
    private String Ecg;
    private String IsException;
    private String StartTime;
    private String EndTime;
    private String ExceptionStartTime;
    private String ExceptionEndTime;
    private String AlertInfo;

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

    public String getEcg() {
        return Ecg;
    }

    public void setEcg(String ecg) {
        Ecg = ecg;
    }

    public String getIsException() {
        return IsException;
    }

    public void setIsException(String isException) {
        IsException = isException;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getExceptionStartTime() {
        return ExceptionStartTime;
    }

    public void setExceptionStartTime(String exceptionStartTime) {
        ExceptionStartTime = exceptionStartTime;
    }

    public String getExceptionEndTime() {
        return ExceptionEndTime;
    }

    public void setExceptionEndTime(String exceptionEndTime) {
        ExceptionEndTime = exceptionEndTime;
    }

    public String getAlertInfo() {
        return AlertInfo;
    }

    public void setAlertInfo(String alertInfo) {
        AlertInfo = alertInfo;
    }
}
