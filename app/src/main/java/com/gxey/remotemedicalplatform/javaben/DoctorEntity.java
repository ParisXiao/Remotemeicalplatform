package com.gxey.remotemedicalplatform.javaben;

import java.io.Serializable;

/**
 * Created by lanluo on 2017/1/8.
 */

public class DoctorEntity implements Serializable{
    private String RoomID;
    private boolean Logout;

    public boolean isLogout() {
        return Logout;
    }

    public void setLogout(boolean logout) {
        Logout = logout;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    private String userName;
    private String SN;
    private boolean isTrue=false;

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public String getSN() {
        return SN;
    }

    public void setSN(String SN) {
        this.SN = SN;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDivision() {
        return Division;
    }

    public void setDivision(String division) {
        Division = division;
    }

    public String getConnectionId() {
        return ConnectionId;
    }

    public void setConnectionId(String connectionId) {
        ConnectionId = connectionId;
    }

    private String headImg;
    private String position;
    private String Division;
    private String ConnectionId;



}
