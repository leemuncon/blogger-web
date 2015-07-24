package me.leefly.nsms;


/**
 * Created by Administrator on 2015/7/13.
 */
public interface SMSService {

    /**
     * 初始化服务
     *
     * @return
     */
    void init();

    /**
     * 主服务是否正常
     *
     * @return
     */
    boolean mainAlive();

    /**
     * 辅助服务是否正常
     *
     * @return
     */
    boolean assistAlive();

    /**
     * 发送信息
     *
     * @param sms
     * @return
     */
    boolean send(SMSInfo sms);

    /**
     * 插入缓存短信队列
     *
     * @param sms
     */
    void add(SMSInfo sms);

}
