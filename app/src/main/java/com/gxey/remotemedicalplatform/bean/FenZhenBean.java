package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018/3/2 0002.
 */

public class FenZhenBean {
    private String yaofang;
    private String time;
    private String yiyuan;
    private String waike;
    private String yisheng;

    public FenZhenBean(String yaofang, String time, String yiyuan, String waike, String yisheng) {
        this.yaofang = yaofang;
        this.time = time;
        this.yiyuan = yiyuan;
        this.waike = waike;
        this.yisheng = yisheng;
    }

    public String getYaofang() {
        return yaofang;
    }

    public void setYaofang(String yaofang) {
        this.yaofang = yaofang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYiyuan() {
        return yiyuan;
    }

    public void setYiyuan(String yiyuan) {
        this.yiyuan = yiyuan;
    }

    public String getWaike() {
        return waike;
    }

    public void setWaike(String waike) {
        this.waike = waike;
    }

    public String getYisheng() {
        return yisheng;
    }

    public void setYisheng(String yisheng) {
        this.yisheng = yisheng;
    }
}
