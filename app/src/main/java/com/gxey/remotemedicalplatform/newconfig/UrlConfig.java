package com.gxey.remotemedicalplatform.newconfig;

/**
 * Created by Administrator on 2018-03-07.
 */

public class UrlConfig {
//        public final static String POST="http://183.230.180.239:58081/";
        public final static String POST="http://healthapi.gxwdyf.com/";
    /**
     * RTMP推流地址
     */
    public final static String RTMPURL="rtmp://shop.guanaida.com/5showcam/";
    /**
     * 家族史
     */
    public final static String SelFamilyHistory=POST+"api/HealthManage/SelFamilyHistory";//家族史
    public final static String SelHistoryOfHeredity=POST+"api/HealthManage/SelHistoryOfHeredity";//遗传史
    public final static String SelHistoryOfJW=POST+"api/HealthManage/SelHistoryOfJW";//既往史
    public final static String SelAnaphylaxis=POST+"api/HealthManage/SelAnaphylaxis";//过敏史
    public final static String SelEnvironment=POST+"api/HealthManage/SelEnvironment";//生活环境
    public final static String SelFamilyPermanentMedicine=POST+"api/HealthManage/SelFamilyPermanentMedicine";//家庭常备药
    public final static String SelOxygen=POST+"api/HealthManage/SelOxygen";//体检数据血氧
    public final static String SelDisability=POST+"api/HealthManage/SelDisability";//残疾情况
    public final static String SelTemperature=POST+"api/HealthManage/SelTemperature";//体温
    public final static String SelBlood=POST+"api/HealthManage/SelBlood";//血压和心率 （其中 Pulse 代表 心率）
    public final static String SelBloodTang=POST+"api/HealthManage/SelBloodTang";//血糖
    public final static String SelCardiogram=POST+"api/HealthManage/SelCardiogram";//心电图
    public final static String SelMedicalExaminationReport=POST+"api/HealthManage/SelMedicalExaminationReport";//健康报告

    public final static String SelApply=POST+"api/ReferralManage/SelApply";//分诊转诊

    public final static String SelExhort=POST+"api/ElectronicArchivesManage/SelExhort";//医嘱
    public final static String SelElectronicMedicalRecord=POST+"api/ElectronicArchivesManage/SelElectronicMedicalRecord";//电子病历
    public final static String SelPatientChart=POST+"api/ElectronicArchivesManage/SelPatientChart";//门诊病历
    public final static String SelCorrelationHospitalExhort=POST+"api/ElectronicArchivesManage/SelCorrelationHospitalExhort";//相关病历或医嘱
    public final static String SelCorrelationHospitalExhort_Img=POST+"api/ElectronicArchivesManage/SelCorrelationHospitalExhort_Img";//相关病历或医嘱详情

    /**
     * 老接口
     */
    public final static String OLDPOST="http://183.230.180.239:58081/";

    public final static String GetBanner=OLDPOST+"api/UData/GetBanner";//广告图片
    public final static String GetNews=OLDPOST+"api/UData/GetNews";//新闻列表


    /**
     * 更新接口
     */
    public final static String Updata=POST+"api/Other/AppUpdate";


}
