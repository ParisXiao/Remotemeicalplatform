package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class XinLvBean {
    private String id;
    private String DeviceID;
    private String HealthID;
    private String Addtime;
    private String Remark;
    private String Pulse;

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

    public String getPulse() {
        return Pulse;
    }

    public void setPulse(String pulse) {
        Pulse = pulse;
    }
}
