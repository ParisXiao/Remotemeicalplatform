package com.gxey.remotemedicalplatform.common;

/**
 * @title api的全局常量存储
 * @author lsnluo
 * @data 16/8/8
 */
public class ApiConstant {

//    public static final String H5BASE="http://211.149.217.223:8083";
    public static final String H5BASE="https://h.gxwdyf.com/";
//    public static final String H5BASE="http://api.guanaida.com:8088/";

    public static final String DEBUGBASE = "http://211.149.217.223:8088/";

//    public static final String BASE = "http://211.149.217.223:8091/";
//    public static final String BASE = "http://api.guanaida.com:8088/";
//    public static final String BASE = "https://h.gxwdyf.com:8011/";
    public static final String BASE = "https://h.gxwdyf.com/webapi/";

    public static final String LOGIN="api/Member/LoginMember";

    public static final String Banner="api/UData/GetBanner";

    //注册
    public static final String Registered= "api/Member/LoginRegister";

    public static final String VerificationCode = "api/Member/TestGetCode";
    //找回密码
    public static final String RetrievePassword = "api/Member/RetrievePassword";
    //获取门店Id
    public static final String GetMD = "api/Member/GetMD";
    //修改个人信息
    public static final String LoginModify = "api/Member/LoginModify";
    //发布话题
    public static final String PushTopic = "api/UData/PushTopic";
    //用户信息获取
    public static final String GetMember = "api/Member/GetMember";

    public static final String GetDoctor="api/Member/GetDoctor";

    public static final String UpLoad = "api/Member/UploadFile";
    //首页新闻
    public static final String GetNews = "api/UData/GetNews";
    //获取科室
    public static final String GetOrganization = "api/Member/GetOrganization";
    //视频评价
    public static final String AddAppraise = "api/UData/AddAppraise";
    //web圈子
    public static final String Circle = "/App/AppCircle/index ";
    //咨询单
    public static final String Recordbills="/App/AppDiagnosis/Recordbills?roomid=";
    //医生详情
    public static final String ShowDoctor ="/App/AppMember/ShowDoctor?DoctorId=";

    public static final String CircleId="CircleId=";

    //web医疗资讯（小助手）
    public static final String Information = "/App/AppNews/Index";
    //web健康
    public static final String HealthRecords = "/App/AppDiagnosis/MemberCase";
    //web我的诊疗
    public static final String MyDiagnosis = "/App/AppDiagnosis/ChatList";
    //web意见反馈
    public static final String Opinion = "/App/AppAbout/feedback";
    //web用药咨询单
    public static final String Medication = "/App/AppDiagnosis/RecordList";
    //web使用帮助
    public static final String help = "/App/AppAbout/help";
    //web关于公信E网
    public static final String index = "/App/AppAbout/index";
    //web注册协议
    public static final String agreement = "/App/AppAbout/agreement";

    //web搜索
    public static final String search = "/App/AppDiagnosis/MultiSearch?KeyWord=";

    //web 健康档案
    public static final String HEADINFO= "/App/AppDiagnosis/MemberCase";
    //首页商品图片
    public static final String homegood1= "http://wxapi.gxwdyf.com/statics/attachment/goods/2016/10/12/18//01004636_small.jpg";
    public static final String homegood2= "http://wxapi.gxwdyf.com/statics/attachment/goods/2016/10/12/18//22545761_small.jpg";
    public static final String homegood3= "http://wxapi.gxwdyf.com/statics/attachment/goods/2016/10/12/18//30558785_small.jpg";
    public static final String homegood4= "http://wxapi.gxwdyf.com/statics/attachment/goods/2016/10/12/18//18082924_small.jpg";
    public static final String homegood5= "http://wxapi.gxwdyf.com/statics/attachment/goods/2016/11/1/15//01341467_small.jpg";
    public static final String homegood6= "http://wxapi.gxwdyf.com/statics/attachment/goods/2016/10/12/18//01004636_small.jpg";

    //商品地址
    public static final String homego1= "http://wxapi.gxwdyf.com/home/goodsinfo_router.html?goods_id=22773#/index";
    public static final String homego2= "http://wxapi.gxwdyf.com/home/goodsinfo_router.html?goods_id=23167#/index";
    public static final String homego3= "http://wxapi.gxwdyf.com/home/goodsinfo_router.html?goods_id=19517#/index";
    public static final String homego4= "http://wxapi.gxwdyf.com/home/goodsinfo_router.html?goods_id=16448#/index";
    public static final String homego5= "http://wxapi.gxwdyf.com/home/goodsinfo_router.html?goods_id=23587#/index";
    public static final String homego6= "http://wxapi.gxwdyf.com/home/goodsinfo_router.html?goods_id=18274#/index";


}
