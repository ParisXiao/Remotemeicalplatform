package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-03.
 */

public class YiChuanBean {
    private String yi_name;
    private String yi_time;

    public YiChuanBean(String yi_name, String yi_time) {
        this.yi_name = yi_name;
        this.yi_time = yi_time;
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
