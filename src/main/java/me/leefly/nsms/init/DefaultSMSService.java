package me.leefly.nsms.init;


import me.leefly.nsms.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2015/7/13.
 */
public class DefaultSMSService implements SMSService {

    private final static Logger LOG = LoggerFactory.getLogger(DefaultSMSService.class);

    private BlockingQueue<SMSInfo> queue;

    private ExecutorService exe;

    private BlockingQueue<SMSInfo> cache;

    private Future<?> future_main;

    private Future<?> future_assist;

    public DefaultSMSService(BlockingQueue queue, ExecutorService exe) {
        this.queue = queue;
        this.exe = exe;
        this.cache = new LinkedBlockingQueue<SMSInfo>(1000);
    }


    @Override
    public void init() {
        if (!mainAlive())
            future_main = exe.submit(new SMSBlockTask(queue, this));
        if (!assistAlive())
            future_assist = exe.submit(new SMSConverTask(queue, cache));
        LOG.info("SMSService was started");
        // 监听线程，监听短信服务是否可用
        Thread listen = new Thread(new ListenerTask(this));
        listen.setName("SMS Guard Thread");
        listen.start();
    }

    @Override
    public boolean mainAlive() {
        if (exe == null || exe.isShutdown())
            return false;
        if (future_main == null)
            return false;
        if (future_main.isDone() || future_main.isCancelled())
            return false;
        return true;
    }

    @Override
    public boolean assistAlive() {
        if (exe == null || exe.isShutdown())
            return false;
        if (future_assist == null)
            return false;
        if (future_assist.isDone() || future_assist.isCancelled())
            return false;
        return true;
    }

    @Override
    public boolean send(SMSInfo sms) {
        boolean ok = queue.offer(sms);
        if (!ok) {
            add(sms);
        }
        LOG.info("Put SMS [" + sms.getContent() + "] phone [" + sms.getPhones() + "] times [" + sms.getCount() + "] into queue was " + ok);
        return ok;
    }

    @Override
    public void add(SMSInfo sms) {
        SMSAddTask add = new SMSAddTask(cache);
        add.setSMS(sms);
        exe.execute(add);
    }

}
