package me.leefly.web.controller;

import me.leefly.model.Directory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
public class WriteController {

    @ResponseBody
    @RequestMapping(value = "write", method = RequestMethod.POST)
    public Map<String, Object> writeData(Directory data, HttpSession session){
        Map<String, Object> result = new HashMap<>();
        session.setAttribute("test", data);
        result.put("success", data);
        return result;
    }

}
