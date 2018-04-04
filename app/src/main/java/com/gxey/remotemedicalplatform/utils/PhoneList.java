package com.gxey.remotemedicalplatform.utils;

/**
 *
 *手机黑名单
 * Created by Administrator on 2018/4/4 0004.
 */

public class PhoneList {
    private static String[] phones =new String[]{"RNE-AL00"};
    public static boolean phoneRtmp(String phonename){
        for (int i = 0; i < phones.length; i++) {
            if (phonename.equals(phones[i])){
                return true;
            }
        }
        return false;
    }
}
