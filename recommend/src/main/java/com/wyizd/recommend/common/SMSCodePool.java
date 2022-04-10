package com.wyizd.recommend.common;

import com.wyizd.recommend.utils.RandomUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SMSCodePool {
    private static Map<String,String> map = new HashMap<>();

    private SMSCodePool() {}

    public static String getCode(String phoneNumber){
       return map.get(phoneNumber);
    }

    public static void setCode(String phoneNumber,String code){
        map.put(phoneNumber, code);
    }
}
