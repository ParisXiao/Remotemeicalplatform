package com.gxey.remotemedicalplatform.newconfig;

/**
 * Created by Administrator on 2018-03-07.
 */

public class UrlConfig {
        public final static String POST="http://183.230.180.239:58081/";

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
    public final static String SelTemperature=POST+"api/HealthManage/SelTemperature";//体温
    public final static String SelBlood=POST+"api/HealthManage/SelBlood";//血压和心率 （其中 Pulse 代表 心率）
    public final static String SelBloodTang=POST+"api/HealthManage/SelBloodTang";//血糖
    public final static String SelCardiogram=POST+"api/HealthManage/SelCardiogram";//心电图
    public final static String SelMedicalExaminationReport=POST+"api/HealthManage/SelMedicalExaminationReport";//心电图
}
