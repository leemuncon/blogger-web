package me.leefly.nsms.init;


import me.leefly.nsms.SMSService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2015/7/13.
 */
public class SMSServiceFactory {

    /**
     * 创建短信发送服务
     *
     * @param executor
     * @return
     */
    public static SMSService createBlockingQueueSMSService(ExecutorService executor) {
        BlockingQueue queue = new LinkedBlockingQueue(10000);
        SMSService service = new DefaultSMSService(queue, executor);
        return service;
    }

}
