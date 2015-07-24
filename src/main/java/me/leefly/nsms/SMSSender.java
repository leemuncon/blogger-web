package me.leefly.nsms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Administrator on 2015/7/13.
 */
public class SMSSender {

    private static final Logger LOG = LoggerFactory.getLogger(SMSSender.class);

    private static final SMSSender SENDER = new SMSSender();

    /**
     * 发送sms信息
     *
     * @param sms
     * @return
     */
    public static boolean dispatch(SMSInfo sms) {
        return SENDER.send(sms);
    }

    /**
     * 发送sms信息
     *
     * @param sms
     * @return
     */
    private boolean send(SMSInfo sms) {
        boolean res = false;
        Map<String, Object> param = SMSParamer.creatParam(sms.getPhones(), sms.getContent());
        // TODO
        return res;
    }

}
