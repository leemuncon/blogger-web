package me.leefly.nsms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2015/7/13.
 */
public class SMSBlockTask implements Runnable {

    private static Logger LOG = LoggerFactory.getLogger(SMSBlockTask.class);

    private SMSService service;

    private BlockingQueue<SMSInfo> queue;

    public SMSBlockTask(BlockingQueue<SMSInfo> queue, SMSService service) {
        this.queue = queue;
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            try {
                SMSInfo sms = queue.take();
                boolean ok = SMSSender.dispatch(sms);
                if (!ok)
                    service.add(sms);
                LOG.info("Send sms [" + sms.getContent() + "] phone [" + sms.getPhones() + "] times [" + sms.getCount() + "] was " + ok);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.error("SMSBlockTask was interrupted");
            }
        }
    }
}
