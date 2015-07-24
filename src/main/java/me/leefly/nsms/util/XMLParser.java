package me.leefly.nsms.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/13.
 */
public class XMLParser {

    private static final Logger LOG = LoggerFactory.getLogger(XMLParser.class);

    /**
     * 解析XML
     *
     * @param xml
     */
    public static Map<String, Object> parse(String xml) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            parseNode(map, root);
            System.out.println(map);
        } catch (DocumentException e) {
            LOG.error("parsing has an exception", e);
        }
        return map;
    }

    private static void parseNode(Map<String, Object> map, Element ele) {
        List<Element> list = ele.elements();
        for (Element e : list) {
            List<Element> _list = e.elements();
            String name = e.getName();
            if (_list.size() > 0) {
                Map<String, Object> _map = new HashMap<String, Object>();
                map.put(name, _map);
                parseNode(_map, e);
            } else {
                String text = e.getText();
                map.put(name, text);
            }
        }
    }


}
