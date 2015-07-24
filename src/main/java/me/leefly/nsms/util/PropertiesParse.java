package me.leefly.nsms.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2015/7/14.
 */
public class PropertiesParse {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesParse.class);

    private static final Map<String, Properties> CACHED = new HashMap<>();

    /**
     * 通过配置文件获取参数
     *
     * @param fileName
     * @return
     */
    public static Map<String, String> getArgument(String fileName) {
        Properties prop = CACHED.get(fileName);
        while (prop == null) {
            load(fileName);
            prop = CACHED.get(fileName);
        }
        return parseProperties(prop);
    }

    private static void load(String fileName) {
        InputStream in = null;
        try {
            in = PropertiesParse.class.getClassLoader().getResourceAsStream(fileName);
            Properties prop = new Properties();
            prop.load(new InputStreamReader(in));
            CACHED.put(fileName, prop);
        } catch (IOException e) {
            LOG.error("load properties has an exception with " + e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOG.error("close properties stream has an exception with " + e.getMessage(), e);
                }
            }
        }
    }

    private static Map<String, String> parseProperties(Properties prop) {
        Map<String, String> maps = new HashMap<>();
        Set<String> keys = prop.stringPropertyNames();
        for (String key : keys) {
            maps.put(key, prop.getProperty(key));
        }
        return maps;
    }

}
