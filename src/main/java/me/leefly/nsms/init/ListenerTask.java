package me.leefly.nsms.init;


import me.leefly.nsms.SMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/7/14.
 */
public class ListenerTask implements Runnable {

    private final Logger LOG = LoggerFactory.getLogger(ListenerTask.class);

    private SMSService service;

    public ListenerTask(SMSService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!service.mainAlive()) {
                    service.init();
                    LOG.info("Restarted SMSService main");
                }
                if (!service.assistAlive()) {
                    service.init();
                    LOG.info("Restarted SMSService assist");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


}
