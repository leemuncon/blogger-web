package me.leefly.nsms.util;

import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/14.
 */
public class SMSTemplateParse {

    private static final String FILE_NAME = "sms-template.properties";

    private static final String SIGN = "【小漫WiFi】";

    private static final SMSTemplateParse PARSE = new SMSTemplateParse();

    private SMSTemplateParse() {
        this.templates = PropertiesParse.getArgument(FILE_NAME);
    }

    private final Map<String, String> templates;

    /**
     * 通过模板key及参数args组成短信内容
     *
     * @param key
     * @param args
     * @return
     */
    public static String getSMSContent(String key, Object... args) {
        String template = PARSE.templates.get(key);
        if (template == null)
            return SIGN;
        String content = MessageFormat.format(template, args);
        return content + SIGN;
    }

}
