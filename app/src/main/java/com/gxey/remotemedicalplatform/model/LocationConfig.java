package com.gxey.remotemedicalplatform.model;

import android.text.TextUtils;

import com.gxey.remotemedicalplatform.application.LocalApplication;
import com.zsoft.signala.hubs.HubConnection;

/**
 * Created by lanluo on 16/9/14.
 */
public class LocationConfig {

    private String UserGUID;

    private String ConnectionId;
    private String GUID;
    private String MStoreID;
    private String StoreID;

    public String getStoreID() {
        if(TextUtils.isEmpty(StoreID)){
            this.StoreID = LocalApplication.getInstance().sharereferences.getString("StoreID","");
        }
        return StoreID;
    }

    public void setStoreID(String storeID) {
        LocalApplication.getInstance().sharereferences.edit().putString("StoreID",storeID).commit();
        StoreID = storeID;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getMStoreID() {
        return MStoreID;
    }

    public void setMStoreID(String MStoreID) {
        this.MStoreID = MStoreID;
    }

    private String RoomID;
    private String HeadImg;
    private String userName;
    private String Division;

    public String getDivision() {

        if(TextUtils.isEmpty(Division)){
            this.Division = LocalApplication.getInstance().sharereferences.getString("Division","全科");
        }
        return Division;
    }

    public void setDivision(String division) {
        LocalApplication.getInstance().sharereferences.edit().putString("Division",division).commit();
        Division = division;
    }

    public String getUserName() {
        if(TextUtils.isEmpty(userName)){
            this.userName = LocalApplication.getInstance().sharereferences.getString("userName",null);
        }
        return userName;
    }

    public void setUserName(String userName) {
        LocalApplication.getInstance().sharereferences.edit().putString("userName",userName).commit();
        this.userName = userName;
    }

    public String getHeadImg() {
        return HeadImg;
    }

    public void setHeadImg(String headImg) {
        HeadImg = headImg;
    }

    public String getConnectionId() {
        return ConnectionId;
    }

    public void setConnectionId(String connectionId) {
        ConnectionId = connectionId;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public HubConnection getCon() {
        return con;
    }

    public void setCon(HubConnection con) {
        this.con = con;
    }

    private HubConnection con;
    private String deviceId;
    private static final LocationConfig config = new LocationConfig();

    public static LocationConfig getInstance() {
        return config;
    }
    private LocationConfig() {

    }

    public String getUserGUID() {
        if(TextUtils.isEmpty(UserGUID)){
            this.UserGUID = LocalApplication.getInstance().sharereferences.getString("UserGUID",null);
        }
        return UserGUID;
    }

    public String getDeviceId() {
        if(TextUtils.isEmpty(deviceId)){
            this.deviceId = LocalApplication.getInstance().sharereferences.getString("deviceId",null);
        }

        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        LocalApplication.getInstance().sharereferences.edit().putString("deviceId",deviceId).commit();
        this.deviceId = deviceId;
    }

    public static LocationConfig getConfig() {
        return config;
    }

    public void setUserGUID(String UserGUID) {
        LocalApplication.getInstance().sharereferences.edit().putString("",UserGUID).commit();
        this.UserGUID = UserGUID;
    }
}






























