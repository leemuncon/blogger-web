package me.leefly.nsms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2015/7/13.
 */
public class SMSAddTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SMSAddTask.class);

    private BlockingQueue<SMSInfo> cache;

    private SMSInfo sms;

    public SMSAddTask(BlockingQueue<SMSInfo> cache) {
        this.cache = cache;
    }

    public void setSMS(SMSInfo sms) {
        this.sms = sms;
    }

    @Override
    public void run() {
        try {
            if (sms.getCount() >= 3) {
                LOG.info("Remove SMS [" + sms.getContent() + "] phone [" + sms.getPhones() + "] because times over " + sms.getCount());
                return;
            }
            cache.put(sms);
            LOG.info("Put SMS [" + sms.getContent() + "] phone [" + sms.getPhones() + "] times [" + sms.getCount() + "] into cache");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.info("SMSAddTask was interrupted");
        }
    }

}
