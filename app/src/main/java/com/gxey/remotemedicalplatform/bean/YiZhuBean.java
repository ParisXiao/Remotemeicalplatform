package com.gxey.remotemedicalplatform.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-03-02.
 */

public class YiZhuBean implements Serializable {

    /**
     * id : c7ca6ad6-31ef-40e4-97e5-89cc2b4f027e
     * number : null
     * termofvalidity : 长期
     * thestarttime : 2018-03-08 00:00:00
     * content : 多喝开水
     * frequency : 1
     * thepatientid : 3091
     * doctorid : 320d2ce1-c56d-4fc2-b14c-2a4821205bc7
     * opentoldtime : 2018-03-13 00:00:00
     * executableunit : fa2be0df-d1db-4816-acbc-a96778360e76
     * doctorname : 陈霞
     * organizename : 杨家坪门店
     * positionname : 副主任医师
     * headimg : /efiles//headimg//1c43a633-f6c9-4002-9804-092bd79b9e91.jpg
     */

    private String id;
    private String number;
    private String termofvalidity;
    private String thestarttime;
    private String content;
    private String frequency;
    private String thepatientid;
    private String doctorid;
    private String opentoldtime;
    private String executableunit;
    private String doctorname;
    private String organizename;
    private String positionname;
    private String headimg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTermofvalidity() {
        return termofvalidity;
    }

    public void setTermofvalidity(String termofvalidity) {
        this.termofvalidity = termofvalidity;
    }

    public String getThestarttime() {
        return thestarttime;
    }

    public void setThestarttime(String thestarttime) {
        this.thestarttime = thestarttime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getThepatientid() {
        return thepatientid;
    }

    public void setThepatientid(String thepatientid) {
        this.thepatientid = thepatientid;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getOpentoldtime() {
        return opentoldtime;
    }

    public void setOpentoldtime(String opentoldtime) {
        this.opentoldtime = opentoldtime;
    }

    public String getExecutableunit() {
        return executableunit;
    }

    public void setExecutableunit(String executableunit) {
        this.executableunit = executableunit;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getOrganizename() {
        return organizename;
    }

    public void setOrganizename(String organizename) {
        this.organizename = organizename;
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }
}
