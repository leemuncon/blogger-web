package me.leefly.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by LiFly on 2015/7/23.
 */
@Controller
public class ForwardController {

    private static Logger logger = LoggerFactory.getLogger(ForwardController.class);

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(){
        logger.info("ForwardController");
        return "home";
    }

}
