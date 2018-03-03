package com.gxey.remotemedicalplatform.bean;

/**
 * Created by Administrator on 2018-03-02.
 */

public class DoctorBean {
    private String doctor_iocn;
    private String doctor_name;
    private String doctor_labels;
    private String doctor_keshi;
    private String doctor_content;
    private String doctor_renshu;

    public DoctorBean(String doctor_iocn, String doctor_name, String doctor_labels, String doctor_keshi, String doctor_content, String doctor_renshu) {
        this.doctor_iocn = doctor_iocn;
        this.doctor_name = doctor_name;
        this.doctor_labels = doctor_labels;
        this.doctor_keshi = doctor_keshi;
        this.doctor_content = doctor_content;
        this.doctor_renshu = doctor_renshu;
    }

    public String getDoctor_iocn() {
        return doctor_iocn;
    }

    public void setDoctor_iocn(String doctor_iocn) {
        this.doctor_iocn = doctor_iocn;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_labels() {
        return doctor_labels;
    }

    public void setDoctor_labels(String doctor_labels) {
        this.doctor_labels = doctor_labels;
    }

    public String getDoctor_keshi() {
        return doctor_keshi;
    }

    public void setDoctor_keshi(String doctor_keshi) {
        this.doctor_keshi = doctor_keshi;
    }

    public String getDoctor_content() {
        return doctor_content;
    }

    public void setDoctor_content(String doctor_content) {
        this.doctor_content = doctor_content;
    }

    public String getDoctor_renshu() {
        return doctor_renshu;
    }

    public void setDoctor_renshu(String doctor_renshu) {
        this.doctor_renshu = doctor_renshu;
    }
}
