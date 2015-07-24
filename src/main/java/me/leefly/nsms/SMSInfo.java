package me.leefly.nsms;

public class SMSInfo {

    public SMSInfo(String[] phones, String content) {
        this.phones = phones;
        this.content = content;
        this.count = 1;
    }

    /**
     * 要发送的电话号码
     */
    private String[] phones;
    /**
     * 发送的内容
     */
    private String content;

    private volatile int count;

    /**
     * 发送次数
     */
    public synchronized void resend() {
        count++;
    }

    public String getPhones() {
        StringBuilder _phones = new StringBuilder();
        for (int i = 0; i < phones.length; i++) {
            _phones.append(phones[i]);
            if (i < phones.length - 1)
                _phones.append(",");
        }
        return _phones.toString();
    }

    public String getContent() {
        return content;
    }

    public int getCount() {
        return count;
    }
}
