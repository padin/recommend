package com.wyizd.recommend.utils;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.wyizd.recommend.common.SMSCodePool;
import com.wyizd.recommend.dto.condition.SendInfo;

import java.util.HashMap;
import java.util.Map;

public class SMSUtil {
    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static SendSmsResponse send(SendInfo sendInfo) throws Exception {

        com.aliyun.dysmsapi20170525.Client client = SMSUtil.createClient("LTAIUwwbJS5YZb7V", "MTpVy98K3mo8eUP2iiU1l68Xl4kFFZ");
//        SendSmsRequest sendSmsRequest = new SendSmsRequest()
//                .setSignName("苍穹之上")
//                .setTemplateCode("SMS_230960412")
//                .setPhoneNumbers("16684903010")
//                .setTemplateParam("{\"code\":\"1234\"}");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(sendInfo.getSignName())
                .setTemplateCode(sendInfo.getTemplateCode())
                .setPhoneNumbers(sendInfo.getPhoneNumber())
                .setTemplateParam(sendInfo.getTemplateParam());
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        return sendSmsResponse;
    }

    public static SendSmsResponse send(String phoneNumber, Map templateParam ) throws Exception {

        com.aliyun.dysmsapi20170525.Client client = SMSUtil.createClient("LTAIUwwbJS5YZb7V", "MTpVy98K3mo8eUP2iiU1l68Xl4kFFZ");
//        SendSmsRequest sendSmsRequest = new SendSmsRequest()
//                .setSignName("苍穹之上")
//                .setTemplateCode("SMS_230960412")
//                .setPhoneNumbers("16684903010")
//                .setTemplateParam("{\"code\":\"1234\"}");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("苍穹之上")
                .setTemplateCode("SMS_230960412")
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam(JSON.toJSONString(templateParam));
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        return sendSmsResponse;
    }

    public static SendSmsResponse send(String phoneNumber) throws Exception {
        String code = SMSCodePool.getCode(phoneNumber);
        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        SendSmsResponse send = send(phoneNumber, map);
        return send;

    }



}
