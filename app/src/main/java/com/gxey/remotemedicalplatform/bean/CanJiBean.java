package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class CanJiBean {
    private String id;
    private String Disability;
    private String PutInStorageTime;
    private String UserId;
    private String AddUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDisability() {
        return Disability;
    }

    public void setDisability(String disability) {
        Disability = disability;
    }

    public String getPutInStorageTime() {
        return PutInStorageTime;
    }

    public void setPutInStorageTime(String putInStorageTime) {
        PutInStorageTime = putInStorageTime;
    }
}
