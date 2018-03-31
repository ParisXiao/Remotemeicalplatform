package com.gxey.remotemedicalplatform.bean;

import com.google.android.gms.search.SearchAuthApi;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/11 0011.
 */

public class DianZiBLBean implements Serializable {

    /**
     * ID : 863cfafe-8e3f-4455-8951-be4c67f8e53b
     * CaseNumber : 1002
     * ThePatientId : null
     * DoctorId : null
     * MainSuit : 主诉测试文字
     * HistoryOfPresentIllness : 现病史测试文章
     * HistoryOfPastIllness : 既往史测试
     * PersonalHistory : 个人史测试
     * FHx : 家族史测试
     * PhysicalExamination : 体格检查测试
     * Diagnosis : 诊断测试
     * Special : 特殊病种测试
     * HandlingSuggestion : 处理意见测试
     * Remarks : 备注测试1
     * Period : 1
     * BoardingTime : 2018-03-01 00:00:00
     * Assistant : 辅助检查
     */

    private String ID;
    private String CaseNumber;
    private String ThePatientId;
    private String DoctorId;
    private String MainSuit;
    private String HistoryOfPresentIllness;
    private String HistoryOfPastIllness;
    private String PersonalHistory;
    private String FHx;
    private String PhysicalExamination;
    private String Diagnosis;
    private String Special;
    private String HandlingSuggestion;
    private String Remarks;
    private String Period;
    private String BoardingTime;
    private String Assistant;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCaseNumber() {
        return CaseNumber;
    }

    public void setCaseNumber(String CaseNumber) {
        this.CaseNumber = CaseNumber;
    }

    public String getThePatientId() {
        return ThePatientId;
    }

    public void setThePatientId(String ThePatientId) {
        this.ThePatientId = ThePatientId;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String DoctorId) {
        this.DoctorId = DoctorId;
    }

    public String getMainSuit() {
        return MainSuit;
    }

    public void setMainSuit(String MainSuit) {
        this.MainSuit = MainSuit;
    }

    public String getHistoryOfPresentIllness() {
        return HistoryOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String HistoryOfPresentIllness) {
        this.HistoryOfPresentIllness = HistoryOfPresentIllness;
    }

    public String getHistoryOfPastIllness() {
        return HistoryOfPastIllness;
    }

    public void setHistoryOfPastIllness(String HistoryOfPastIllness) {
        this.HistoryOfPastIllness = HistoryOfPastIllness;
    }

    public String getPersonalHistory() {
        return PersonalHistory;
    }

    public void setPersonalHistory(String PersonalHistory) {
        this.PersonalHistory = PersonalHistory;
    }

    public String getFHx() {
        return FHx;
    }

    public void setFHx(String FHx) {
        this.FHx = FHx;
    }

    public String getPhysicalExamination() {
        return PhysicalExamination;
    }

    public void setPhysicalExamination(String PhysicalExamination) {
        this.PhysicalExamination = PhysicalExamination;
    }

    public String getDiagnosis() {
        return Diagnosis;
    }

    public void setDiagnosis(String Diagnosis) {
        this.Diagnosis = Diagnosis;
    }

    public String getSpecial() {
        return Special;
    }

    public void setSpecial(String Special) {
        this.Special = Special;
    }

    public String getHandlingSuggestion() {
        return HandlingSuggestion;
    }

    public void setHandlingSuggestion(String HandlingSuggestion) {
        this.HandlingSuggestion = HandlingSuggestion;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getBoardingTime() {
        return BoardingTime;
    }

    public void setBoardingTime(String BoardingTime) {
        this.BoardingTime = BoardingTime;
    }

    public String getAssistant() {
        return Assistant;
    }

    public void setAssistant(String Assistant) {
        this.Assistant = Assistant;
    }
}
