package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-02.
 */

public class YiZhuBean {
    private String yizhu_icon;
    private String yizhu_name;
    private String yizhu_labels;
    private String yizhu_no;
    private String yizhu_content;
    private String yizhu_time;

    public YiZhuBean(String yizhu_icon, String yizhu_name, String yizhu_labels, String yizhu_no, String yizhu_content, String yizhu_time) {
        this.yizhu_icon = yizhu_icon;
        this.yizhu_name = yizhu_name;
        this.yizhu_labels = yizhu_labels;
        this.yizhu_no = yizhu_no;
        this.yizhu_content = yizhu_content;
        this.yizhu_time = yizhu_time;
    }

    public String getYizhu_icon() {
        return yizhu_icon;
    }

    public void setYizhu_icon(String yizhu_icon) {
        this.yizhu_icon = yizhu_icon;
    }

    public String getYizhu_name() {
        return yizhu_name;
    }

    public void setYizhu_name(String yizhu_name) {
        this.yizhu_name = yizhu_name;
    }

    public String getYizhu_labels() {
        return yizhu_labels;
    }

    public void setYizhu_labels(String yizhu_labels) {
        this.yizhu_labels = yizhu_labels;
    }

    public String getYizhu_no() {
        return yizhu_no;
    }

    public void setYizhu_no(String yizhu_no) {
        this.yizhu_no = yizhu_no;
    }

    public String getYizhu_content() {
        return yizhu_content;
    }

    public void setYizhu_content(String yizhu_content) {
        this.yizhu_content = yizhu_content;
    }

    public String getYizhu_time() {
        return yizhu_time;
    }

    public void setYizhu_time(String yizhu_time) {
        this.yizhu_time = yizhu_time;
    }
}
