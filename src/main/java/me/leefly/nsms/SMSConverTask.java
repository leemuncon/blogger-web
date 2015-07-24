package me.leefly.nsms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2015/7/14.
 */
public class SMSConverTask implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SMSConverTask.class);

    private BlockingQueue<SMSInfo> queue;

    private BlockingQueue<SMSInfo> cache;

    public SMSConverTask(BlockingQueue<SMSInfo> queue, BlockingQueue<SMSInfo> cache) {
        this.queue = queue;
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {
            try {
                SMSInfo sms = cache.take();
                LOG.info("Reset SMS [" + sms.getContent() + "] phone [" + sms.getPhones() + "] times [" + sms.getCount() + "] into queue");
                sms.resend();
                queue.put(sms);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.info("SMSConverTask was interrupted");
            }
        }
    }
}
