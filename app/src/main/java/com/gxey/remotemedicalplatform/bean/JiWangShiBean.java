package com.gxey.remotemedicalplatform.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class JiWangShiBean {
    private List<HistoryOfDiseaseBean> HistoryOfDisease;
    private List<SurgeryBean> Surgery;
    private List<TraumaBean> Trauma;
    private List<BloodTransfusionBean> BloodTransfusion;

    public List<HistoryOfDiseaseBean> getHistoryOfDisease() {
        return HistoryOfDisease;
    }

    public void setHistoryOfDisease(List<HistoryOfDiseaseBean> HistoryOfDisease) {
        this.HistoryOfDisease = HistoryOfDisease;
    }

    public List<SurgeryBean> getSurgery() {
        return Surgery;
    }

    public void setSurgery(List<SurgeryBean> Surgery) {
        this.Surgery = Surgery;
    }

    public List<TraumaBean> getTrauma() {
        return Trauma;
    }

    public void setTrauma(List<TraumaBean> Trauma) {
        this.Trauma = Trauma;
    }

    public List<BloodTransfusionBean> getBloodTransfusion() {
        return BloodTransfusion;
    }

    public void setBloodTransfusion(List<BloodTransfusionBean> BloodTransfusion) {
        this.BloodTransfusion = BloodTransfusion;
    }

    public static class HistoryOfDiseaseBean {
        /**
         * Id : 1d38e5e3-bf70-4ecb-865a-86c6b0fa27cb
         * NameofThedisease : 高血压
         * Timeofdiagnosis : 2018-03-05
         * Isitcured : 是
         * StorageTime : 2018/3/8 21:22:30
         * UserId : 39c0cab2-e4ce-4345-b46a-6f8a7356d784
         * AddUserId : 2c1933f1-d720-4c97-94db-d659b18256e9
         */

        private String Id;
        private String NameofThedisease;
        private String Timeofdiagnosis;
        private String Isitcured;
        private String StorageTime;
        private String UserId;
        private String AddUserId;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getNameofThedisease() {
            return NameofThedisease;
        }

        public void setNameofThedisease(String NameofThedisease) {
            this.NameofThedisease = NameofThedisease;
        }

        public String getTimeofdiagnosis() {
            return Timeofdiagnosis;
        }

        public void setTimeofdiagnosis(String Timeofdiagnosis) {
            this.Timeofdiagnosis = Timeofdiagnosis;
        }

        public String getIsitcured() {
            return Isitcured;
        }

        public void setIsitcured(String Isitcured) {
            this.Isitcured = Isitcured;
        }

        public String getStorageTime() {
            return StorageTime;
        }

        public void setStorageTime(String StorageTime) {
            this.StorageTime = StorageTime;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getAddUserId() {
            return AddUserId;
        }

        public void setAddUserId(String AddUserId) {
            this.AddUserId = AddUserId;
        }
    }

    public static class SurgeryBean {
        /**
         * Id : 33e854e5-cf7d-4faf-ac90-59e55264155a
         * OperativeName : 骨折
         * OperationTime : 2018-03-01 00:00:00
         * Diagnosticresults : 已治愈
         * StorageTime : 2018-03-08 21:23:01
         * UserId : 39c0cab2-e4ce-4345-b46a-6f8a7356d784
         * AddUserId : 2c1933f1-d720-4c97-94db-d659b18256e9
         */

        private String Id;
        private String OperativeName;
        private String OperationTime;
        private String Diagnosticresults;
        private String StorageTime;
        private String UserId;
        private String AddUserId;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getOperativeName() {
            return OperativeName;
        }

        public void setOperativeName(String OperativeName) {
            this.OperativeName = OperativeName;
        }

        public String getOperationTime() {
            return OperationTime;
        }

        public void setOperationTime(String OperationTime) {
            this.OperationTime = OperationTime;
        }

        public String getDiagnosticresults() {
            return Diagnosticresults;
        }

        public void setDiagnosticresults(String Diagnosticresults) {
            this.Diagnosticresults = Diagnosticresults;
        }

        public String getStorageTime() {
            return StorageTime;
        }

        public void setStorageTime(String StorageTime) {
            this.StorageTime = StorageTime;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getAddUserId() {
            return AddUserId;
        }

        public void setAddUserId(String AddUserId) {
            this.AddUserId = AddUserId;
        }
    }

    public static class TraumaBean {
        /**
         * Id : 28e98c05-01af-4756-881d-16ca31fb1abe
         * Nameoftrauma : 阿萨德d
         * TraumaTime : 2018-03-19 00:00:00
         * Des : 111
         * StorageTime : 2018-03-03 10:44:32
         * UserId : 3040
         * AddUserId : System
         */

        private String Id;
        private String Nameoftrauma;
        private String TraumaTime;
        private String Des;
        private String StorageTime;
        private String UserId;
        private String AddUserId;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getNameoftrauma() {
            return Nameoftrauma;
        }

        public void setNameoftrauma(String Nameoftrauma) {
            this.Nameoftrauma = Nameoftrauma;
        }

        public String getTraumaTime() {
            return TraumaTime;
        }

        public void setTraumaTime(String TraumaTime) {
            this.TraumaTime = TraumaTime;
        }

        public String getDes() {
            return Des;
        }

        public void setDes(String Des) {
            this.Des = Des;
        }

        public String getStorageTime() {
            return StorageTime;
        }

        public void setStorageTime(String StorageTime) {
            this.StorageTime = StorageTime;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getAddUserId() {
            return AddUserId;
        }

        public void setAddUserId(String AddUserId) {
            this.AddUserId = AddUserId;
        }
    }

    public static class BloodTransfusionBean {
        /**
         * Id : 14ffad71-519c-4a38-88dc-7ed37648328e
         * Timeofbloodtransfusion : 2018-03-01
         * BloodtransFusion : 200
         * Reason : 骨折手术
         * StorageTime : 2018/3/8 21:24:03
         * PasthistoryID : null
         * UserId : 39c0cab2-e4ce-4345-b46a-6f8a7356d784
         * AddUserId : 2c1933f1-d720-4c97-94db-d659b18256e9
         */

        private String Id;
        private String Timeofbloodtransfusion;
        private String BloodtransFusion;
        private String Reason;
        private String StorageTime;
        private Object PasthistoryID;
        private String UserId;
        private String AddUserId;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getTimeofbloodtransfusion() {
            return Timeofbloodtransfusion;
        }

        public void setTimeofbloodtransfusion(String Timeofbloodtransfusion) {
            this.Timeofbloodtransfusion = Timeofbloodtransfusion;
        }

        public String getBloodtransFusion() {
            return BloodtransFusion;
        }

        public void setBloodtransFusion(String BloodtransFusion) {
            this.BloodtransFusion = BloodtransFusion;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getStorageTime() {
            return StorageTime;
        }

        public void setStorageTime(String StorageTime) {
            this.StorageTime = StorageTime;
        }

        public Object getPasthistoryID() {
            return PasthistoryID;
        }

        public void setPasthistoryID(Object PasthistoryID) {
            this.PasthistoryID = PasthistoryID;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getAddUserId() {
            return AddUserId;
        }

        public void setAddUserId(String AddUserId) {
            this.AddUserId = AddUserId;
        }
    }
}
