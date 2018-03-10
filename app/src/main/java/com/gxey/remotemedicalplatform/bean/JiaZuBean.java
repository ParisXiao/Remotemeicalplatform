package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-10.
 */

public class JiaZuBean {
    private String Id;
    private String DiseaseName;
    private String WarehouseEntryTime;
    private String Type;
    private String UserId;
    private String AddUserId;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDiseaseName() {
        return DiseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        DiseaseName = diseaseName;
    }

    public String getWarehouseEntryTime() {
        return WarehouseEntryTime;
    }

    public void setWarehouseEntryTime(String warehouseEntryTime) {
        WarehouseEntryTime = warehouseEntryTime;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getAddUserId() {
        return AddUserId;
    }

    public void setAddUserId(String addUserId) {
        AddUserId = addUserId;
    }
}
