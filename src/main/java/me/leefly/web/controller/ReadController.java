package me.leefly.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/24.
 */
@Controller
public class ReadController {

    @ResponseBody
    @RequestMapping(value = "read", method = RequestMethod.GET)
    public Map<String, Object> readData(HttpSession session){
        Map<String, Object> result = new HashMap<>();
        Object test = session.getAttribute("test");
        result.put("data", test);
        return result;
    }

}
