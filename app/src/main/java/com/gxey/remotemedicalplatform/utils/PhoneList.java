package com.gxey.remotemedicalplatform.utils;

/**
 *
 *手机黑名单
 * Created by Administrator on 2018/4/4 0004.
 */

public class PhoneList {
    private static String[] phones =new String[]{"RNE-AL00","BND-AL10","SM-C5000","VTR-AL00","MHA-AL00","MHA-TL00","LON-AL00","CAZ-AL00"};
    public static boolean phoneRtmp(String phonename){
        for (int i = 0; i < phones.length; i++) {
            if (phonename.equals(phones[i])){
                return true;
            }
        }
        return false;
    }
}
