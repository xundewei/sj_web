package com.sj.web.controllers;


import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sj.web.common.security.ShiroUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 控制器的父类，主要是把相关需要用的，特别是数据都加载到父类中，可以方便相关控制器使用
 * @author tody
 * 2017年4月22日20:11:12
 *
 */
public class BaseController {

    /**
     * 日志对象
     */
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 获取当前Request
     *
     * @return HttpServletRequest
     */
    protected HttpServletRequest getCurrentRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取当前Session
     *
     * @return HttpSession
     */
    protected HttpSession getCurrentSession() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null)
            return null;

        HttpSession session = request.getSession();
        return session;
    }
    
    /**
     * 获取当前Shiro用户
     * @return
     */
    protected ShiroUser getCurrentShiroUser(){
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        SecurityUtils.getSubject().isPermitted("admin");
        return user;
    }
    
}
