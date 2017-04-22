package com.sj.web.controllers.main;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.web.services.system.LoginInfoService;



/**
 * 系统主控制器，负责处理一些系统级别的请求
 */
@Controller
@RequestMapping("/")
public class MainController {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private LoginInfoService loginInfoService;
    private void addLoginInfo(HttpServletRequest request) {
     
        try {
           //tody
        } catch (Exception ex) {
            String message = "保存用户登录信息失败";
            logger.error(message, ex);
        }
    }
}
