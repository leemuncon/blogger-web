package me.leefly.nsms;


import me.leefly.nsms.init.SMSServiceFactory;
import me.leefly.nsms.util.SMSTemplateParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/7/14.
 */
public class SMS {

    private static final Logger LOG = LoggerFactory.getLogger(SMS.class);

    private static final int NUMBER = Runtime.getRuntime().availableProcessors();

    private static final SMS _SMS = new SMS();

    private SMS() {
        this.executor = Executors.newFixedThreadPool(NUMBER * 2);
        this.service = SMSServiceFactory.createBlockingQueueSMSService(this.executor);
        this.service.init();
    }

    private final SMSService service;

    private final ExecutorService executor;

    /**
     * 添加短信到队列
     *
     * @param phones
     * @param templateKEY
     * @param args
     */
    public static void send(List<String> phones, String templateKEY, String... args) {
        String content = SMSTemplateParse.getSMSContent(templateKEY, args);
        _SMS._send(phones.toArray(new String[0]), content);
    }

    private void _send(String[] phones, String content) {
        SMSInfo sms = new SMSInfo(phones, content);
        service.send(sms);
    }

}
