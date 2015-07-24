package me.leefly.nsms;

import me.leefly.nsms.config.SMSConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/13.
 */
public class SMSParamer {

    public static Map<String, Object> creatParam(String phones, String content) {
        Map<String, Object> param = new HashMap<String, Object>();
        //param.put("userid", SMSConfig.SMS_ID);
        param.put("account", SMSConfig.SMS_ACCOUNT);
        param.put("password", SMSConfig.SMS_PASSWORD);
        //param.put("sendTime", SMSConfig.SMS_SEND_TIME);
        param.put("action", SMSConfig.SMS_ACTION);
        //param.put("extno", SMSConfig.SMS_EXTNO);
        param.put("mobile", phones);
        param.put("content", content);
        return param;
    }

}
