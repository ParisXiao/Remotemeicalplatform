package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class YiChuanBean {
    private String id;
    private String yi_name;
    private String yi_time;
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

    public String getYi_name() {
        return yi_name;
    }

    public void setYi_name(String yi_name) {
        this.yi_name = yi_name;
    }

    public String getYi_time() {
        return yi_time;
    }

    public void setYi_time(String yi_time) {
        this.yi_time = yi_time;
    }
}
