package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class HealthBGBean {
    private String baogaoDanhao;
    private String baogaoHZname;
    private String baogaoYSname;
    private String baogaoZuZhi;
    private String baogaoTime;

    public HealthBGBean(String baogaoDanhao, String baogaoHZname, String baogaoYSname, String baogaoZuZhi, String baogaoTime) {
        this.baogaoDanhao = baogaoDanhao;
        this.baogaoHZname = baogaoHZname;
        this.baogaoYSname = baogaoYSname;
        this.baogaoZuZhi = baogaoZuZhi;
        this.baogaoTime = baogaoTime;
    }

    public String getBaogaoDanhao() {
        return baogaoDanhao;
    }

    public void setBaogaoDanhao(String baogaoDanhao) {
        this.baogaoDanhao = baogaoDanhao;
    }

    public String getBaogaoHZname() {
        return baogaoHZname;
    }

    public void setBaogaoHZname(String baogaoHZname) {
        this.baogaoHZname = baogaoHZname;
    }

    public String getBaogaoYSname() {
        return baogaoYSname;
    }

    public void setBaogaoYSname(String baogaoYSname) {
        this.baogaoYSname = baogaoYSname;
    }

    public String getBaogaoZuZhi() {
        return baogaoZuZhi;
    }

    public void setBaogaoZuZhi(String baogaoZuZhi) {
        this.baogaoZuZhi = baogaoZuZhi;
    }

    public String getBaogaoTime() {
        return baogaoTime;
    }

    public void setBaogaoTime(String baogaoTime) {
        this.baogaoTime = baogaoTime;
    }
}
