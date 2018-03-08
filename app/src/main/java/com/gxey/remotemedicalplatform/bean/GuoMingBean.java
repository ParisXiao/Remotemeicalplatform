package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class GuoMingBean {
    private String id;
    private String AllergicDrug;
    private String WarehouseEntryTime;
    private String UserId;
    private String AddUserId;
    private String IsOther;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAllergicDrug() {
        return AllergicDrug;
    }

    public void setAllergicDrug(String allergicDrug) {
        AllergicDrug = allergicDrug;
    }

    public String getWarehouseEntryTime() {
        return WarehouseEntryTime;
    }

    public void setWarehouseEntryTime(String warehouseEntryTime) {
        WarehouseEntryTime = warehouseEntryTime;
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

    public String getIsOther() {
        return IsOther;
    }

    public void setIsOther(String isOther) {
        IsOther = isOther;
    }
}
