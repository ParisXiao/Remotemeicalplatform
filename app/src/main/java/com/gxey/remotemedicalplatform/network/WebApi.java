package com.gxey.remotemedicalplatform.network;

import com.gxey.remotemedicalplatform.common.ApiConstant;
import com.gxey.remotemedicalplatform.javaben.Bannerben;
import com.gxey.remotemedicalplatform.javaben.DepartmentBen;
import com.gxey.remotemedicalplatform.javaben.HomeNewsBen;
import com.gxey.remotemedicalplatform.javaben.LogBen;
import com.gxey.remotemedicalplatform.javaben.MyPersonBen;
import com.gxey.remotemedicalplatform.javaben.StoresBen;
import com.gxey.remotemedicalplatform.javaben.WDoctorEntity;
import com.gxey.remotemedicalplatform.model.ApiModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lanluo on 16/9/14.
 * V2系统接口类
 */
public interface WebApi {

    @POST(ApiConstant.LOGIN)
    Observable<ApiModel<LogBen>> loginUser(@Body RequestBody RequestBody);
 
    //注册
    @POST(ApiConstant.Registered)
    Observable<ApiModel<String>> getRegistered(@Body RequestBody RequestBody);

    //获取验证码
    @POST(ApiConstant.VerificationCode)
    Observable<ApiModel<String>>getVerificationCode(@Body RequestBody RequestBody);
   //首页banner
    @POST(ApiConstant.Banner)
    Observable<ApiModel<List<Bannerben>>> homebanner(@Body RequestBody RequestBody);
    //找回密码
    @POST(ApiConstant.RetrievePassword)
    Observable<ApiModel<String>> bacpassword(@Body RequestBody RequestBody);
    //获取门店id
    @POST(ApiConstant.GetMD)
    Observable<ApiModel<List<StoresBen>>> stores(@Body RequestBody RequestBody);
    //修改个人信息
    @POST(ApiConstant.LoginModify)
    Observable<ApiModel<String>> person(@Body RequestBody RequestBody);
    //发布话题
    @POST(ApiConstant.PushTopic)
    Observable<ApiModel<String>> release(@Body RequestBody RequestBody);
    //上传图片
    @POST(ApiConstant.UpLoad)
    Observable<ApiModel<String>> upLoadImag(@Body MultipartBody body);
    //首页新闻
    @POST(ApiConstant.GetNews)
    Observable<ApiModel<List<HomeNewsBen>>> getNews(@Body RequestBody RequestBody);
    //视屏评价
    @POST(ApiConstant.AddAppraise)
    Observable<ApiModel<String>>AddAppraise(@Body RequestBody RequestBody);
    //获取科室
    @POST(ApiConstant.GetOrganization)
    Observable<ApiModel<List<DepartmentBen>>>GetOrganization(@Body RequestBody RequestBody);
    //获取用户信息
    @POST(ApiConstant.GetMember)
    Observable<ApiModel<MyPersonBen>>GetMember(@Body RequestBody RequestBody);

    //获取医生列表
    @POST(ApiConstant.GetDoctor)
    Observable<ApiModel<List<WDoctorEntity>>>getDoctor(@Body RequestBody RequestBody);


}
