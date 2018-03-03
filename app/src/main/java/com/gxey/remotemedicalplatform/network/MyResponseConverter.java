package com.gxey.remotemedicalplatform.network;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lanluo on 16/9/21.
 */
public class MyResponseConverter<T> implements Converter<ResponseBody, T> {


    private Type type;

    public MyResponseConverter(Type type)
    {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();
       System.out.println("MyResponseConverter解析前的数据============="+result);
        //result = trim(result);
        if(result.startsWith("\""))
            result = result.substring(1,result.length()-1).replaceAll("\\\\", "");

        System.out.println("MyResponseConverter解析后的数据============="+result);



        Gson gson = new Gson();
        T t =  gson.fromJson(result,type);
        return  t;
    }


    private static final Pattern P = Pattern.compile("<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">(\\s*.*)<\\/string>");

    public static String trim(String xml){
        Matcher m = P.matcher(xml);
        if(m.find()){
            m.group(0);
            return m.group(1);
        }
        return "";
    }
}
