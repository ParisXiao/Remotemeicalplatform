package com.gxey.remotemedicalplatform.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.gxey.remotemedicalplatform.common.ApiConstant;
import com.gxey.remotemedicalplatform.common.AppConstant;
import com.gxey.remotemedicalplatform.javaben.Bannerben;
import com.gxey.remotemedicalplatform.javaben.DepartmentBen;
import com.gxey.remotemedicalplatform.javaben.HomeNewsBen;
import com.gxey.remotemedicalplatform.javaben.LogBen;
import com.gxey.remotemedicalplatform.javaben.MyPersonBen;
import com.gxey.remotemedicalplatform.javaben.StoresBen;
import com.gxey.remotemedicalplatform.javaben.WDoctorEntity;
import com.gxey.remotemedicalplatform.model.LocationConfig;
import com.gxey.remotemedicalplatform.utils.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @Title
 * @Author lanluo
 * @Date 2016-05-23 17:14
 * v2接口帮助类
 */
public class HttpClientHelper {


    private static final int DEFAULT_TIMEOUT = 10;
    private LocationConfig config;
    private Retrofit retrofit;
    private WebApi webApi;
    private OkHttpClient.Builder builder;
    private Context context;

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpClientHelper INSTANCE = new HttpClientHelper();
    }

    //获取单例
    public static HttpClientHelper getInstance() {


        return SingletonHolder.INSTANCE;
    }


    //构造方法私有
    private HttpClientHelper() {
        //手动创建一个OkHttpClient并设置超时时间
        builder = new OkHttpClient.Builder();
//        if (AppConstant.isDebug) {
            HttpLoggingInterceptor loggingInterceptor =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.i("RxJava", message);
                        }
                    });

            HttpLoggingInterceptor loggingHead =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.i("head", message);
                        }
                    });

            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
            builder.addInterceptor(loggingHead);
//        }

        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        String url;
        if(AppConstant.isDebug){
            url = ApiConstant.DEBUGBASE;
        }else{
            url  =ApiConstant.BASE;
        }


        builder.addNetworkInterceptor(mTokenInterceptor);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(new MyGsonConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();


        config = LocationConfig.getInstance();
        webApi = retrofit.create(WebApi.class);
    }


    Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            String UserGUID="";
            Request originalRequest = chain.request();
            if (TextUtils.isEmpty(LocationConfig.getInstance().getUserGUID())) {
               UserGUID="0";
            }else {
                UserGUID=LocationConfig.getInstance().getUserGUID();
            }
            String date = new Date().getTime()+"";
            Request authorised = originalRequest.newBuilder()
                    .addHeader("UserGUID",UserGUID)
                    .addHeader("Platform", "1")
                    .addHeader("Versions","1.0")
                    .addHeader("DeviceID",LocationConfig.getInstance().getDeviceId())
                    .build();
            return chain.proceed(authorised);
        }
    };

    static <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    private RequestBody getRequestBody(HashMap<String,Object> map){
        Gson gson= new Gson();
        String json = gson.toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);

        return body;
    }


     /*body列子
    public void getVisitOrPlan(AddVisitEntity entity, HttpSubseiber.ResponseHandler<String>response){
        Gson gson= new Gson();
        String json =  gson.toJson(entity);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Observable observable = webApi.getVisitOrPlan(body);
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));
     */



    /**
     * 用户登录
     * @param user
     * @param response
     */
    public void loginUser(String user,String pwd,HttpSubseiber.ResponseHandler<LogBen> response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("LoginName",user);
        hashMap.put("PassWord",pwd);
       Observable observable = webApi.loginUser(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<LogBen>().getSubseiber(response));
    }
/**
 * Banner图
 */

    /**
     * 用户注册
     */
    public void getRegistered(String LoginName,String PassWord,String Sex,String UName ,String UNum,String Captcha, HttpSubseiber.ResponseHandler<String> response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("LoginName",LoginName);
        hashMap.put("PassWord",PassWord);
        hashMap.put("Sex",Sex);
        hashMap.put("UName",UName);
        hashMap.put("UNum",UNum);
        hashMap.put("Captcha",Captcha);
        Observable observable = webApi.getRegistered(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));
    }

    /**
     * 验证码
     */
    public void getVerificationCode(String TelPhone, HttpSubseiber.ResponseHandler<String>response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("TelPhone",TelPhone);
        Observable observable = webApi.getVerificationCode(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));
    }
/*
* 首页banner
* */
public void homebanner(String Type,HttpSubseiber.ResponseHandler<List<Bannerben>> response){
    HashMap<String,Object> hashMap = new HashMap<>();
    hashMap.put("Type",Type);
    Observable observable = webApi.homebanner(getRequestBody(hashMap));
    toSubscribe(observable,new HttpSubseiber<List<Bannerben>>().getSubseiber(response));


}
    /*
    * 首页新闻
     * */
    public void GetNews(String Type,String PageIndex,String PageSize,HttpSubseiber.ResponseHandler<List<HomeNewsBen>> response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("Type",Type);
        hashMap.put("PageIndex",PageIndex);
        hashMap.put("PageSize",PageSize);
        Observable observable = webApi.getNews(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<List<HomeNewsBen>>().getSubseiber(response));


    }


    /**
     * 找回密码
     */
    public void bacpassword(String name, String TelPhone,String pass,String Captcha, HttpSubseiber.ResponseHandler<String>response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("Unum",name);
        hashMap.put("LoginName",TelPhone);
        hashMap.put("PassWord",pass);
        hashMap.put("Captcha",Captcha);
        Observable observable = webApi.bacpassword(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));
    }

    /*
     * 获取门店ID
    * */
    public void stores(HttpSubseiber.ResponseHandler<List<StoresBen>> response){
        HashMap<String,Object> hashMap = new HashMap<>();
        Observable observable = webApi.stores(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<List<StoresBen>>().getSubseiber(response));


    }
    /*
   * 修改个人信息
  * */
    public void person(String ID,String LoginName,String PassWord,String Sex,String UName,String UNum,String Introduce,String Images,String StoreID,String sfzh,  HttpSubseiber.ResponseHandler<String> response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("LoginName",LoginName);
        hashMap.put("PassWord",PassWord);
        hashMap.put("Sex",Sex);
        hashMap.put("ID",ID);
        hashMap.put("UName",UName);
        hashMap.put("UNum",UNum);
        hashMap.put("Introduce",Introduce);
        hashMap.put("Images",Images);
        hashMap.put("StoreID",StoreID);
        hashMap.put("SFZH",sfzh);
        Observable observable = webApi.person(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));


    }
    /*
   * 获取门店ID
  * */
    public void release(String CircleID,String Imgs,String Name,String PublisherName,String PublisherID,String Summary,HttpSubseiber.ResponseHandler<String> response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("CircleID",CircleID);
        hashMap.put("Imgs",Imgs);
        hashMap.put("Name",Name);
        hashMap.put("PublisherName",PublisherName);
        hashMap.put("PublisherID",PublisherID);
        hashMap.put("Summary",Summary);
        Observable observable = webApi.release(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));


    }

    /**
     * 视频评价
     */
    public void AddAppraise(String RoomID,String MUserID,String AppraiseContent,String AppraiseLevell, HttpSubseiber.ResponseHandler<String>response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("RoomID",RoomID);
        hashMap.put("AppraiseContent",AppraiseContent);
        hashMap.put("AppraiseLevell",AppraiseLevell);
        hashMap.put("MStoreID",LocationConfig.getInstance().getMStoreID());
        hashMap.put("DUserID",LocationConfig.getInstance().getConnectionId());
        hashMap.put("GUID",LocationConfig.getInstance().getGUID());
        hashMap.put("MUserID",MUserID);


        Observable observable = webApi.AddAppraise(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));
    }

    /**
     * 获取科室
     */
    public void GetOrganization(HttpSubseiber.ResponseHandler<List<DepartmentBen>>response){
        HashMap<String,Object> hashMap = new HashMap<>();

        Observable observable = webApi.GetOrganization(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<List<DepartmentBen>>().getSubseiber(response));
    }

    /**
     * 用户信息
     */
    public void GetMember(String Id,HttpSubseiber.ResponseHandler<MyPersonBen>response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("ID",Id);
        Observable observable = webApi.GetMember(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<MyPersonBen>().getSubseiber(response));
    }

    /**
     * 获取医生列表
     * @param UName
     * @param Type
     * @param response
     */
    public void getDoctor(String UName, String Type, HttpSubseiber.ResponseHandler<List<WDoctorEntity>> response){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("UName",UName);
        hashMap.put("Type",Type);
        Observable observable = webApi.getDoctor(getRequestBody(hashMap));
        toSubscribe(observable,new HttpSubseiber<List<WDoctorEntity>>().getSubseiber(response));
    }


    /**
     * 上传图片
     * @param path
     * @param response
     */
    public void upLoadImg(String path,HttpSubseiber.ResponseHandler<String> response){
        // create RequestBody instance from file
        String [] strList ={path};
        MultipartBody Muli = filesToMultipartBody("file",strList,MediaType.parse("image/JPEG"));
        Observable observable  =   webApi.upLoadImag(Muli);
        toSubscribe(observable,new HttpSubseiber<String>().getSubseiber(response));

    }

    /**
     * 其实也是将File封装成RequestBody，然后再封装成Part，<br>
     * 不同的是使用MultipartBody.Builder来构建MultipartBody
     * @param key 同上
     * @param filePaths 同上
     * @param imageType 同上
     */
    private   MultipartBody filesToMultipartBody(String key,
                                               String[] filePaths,
                                               MediaType imageType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (String filePath : filePaths) {
            File file = new File(ImageUtils.compressImageByPixel(filePath));
            RequestBody requestBody = RequestBody.create(imageType, file);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }

}
