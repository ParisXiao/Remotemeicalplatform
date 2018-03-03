package com.gxey.remotemedicalplatform.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by lanluo on 16/9/21.
 */
public class MyGsonConverterFactory extends Converter.Factory
{


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new MyResponseConverter(type);
    }



}
