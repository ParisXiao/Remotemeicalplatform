package com.gxey.remotemedicalplatform.javaben;

/**
 * Created by xusongsong on 2016/12/31.
 */

public class LogBen {

    private  String ID;
    private  String PositionID;
    private  String UName;
    private  String UNum;
    private  String Sex;
    private  String MobileNum;
    private  String Addr;
    private  String password;
    private  String StoreID;

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPositionID() {
        return PositionID;
    }

    public void setPositionID(String positionID) {
        PositionID = positionID;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getUNum() {
        return UNum;
    }

    public void setUNum(String UNum) {
        this.UNum = UNum;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getMobileNum() {
        return MobileNum;
    }

    public void setMobileNum(String mobileNum) {
        MobileNum = mobileNum;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
