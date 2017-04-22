package com.sj.web.controllers.main;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * 系统主控制器，负责处理一些系统级别的请求
 */
@Controller
@RequestMapping("/")
public class MainController {

    /**
     * 日志对象
     */
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);

   

    /**
     * 记录用户登录信息
     */
    private void addLoginInfo(HttpServletRequest request) {
     
        try {
           //tody
        } catch (Exception ex) {
            String message = "保存用户登录信息失败";
            logger.error(message, ex);
        }
    }

//    /**
//     * 左侧导航菜单，为了加快主页显示速度，不再使用<c:import url="/home/leftmenu" />的方法
//     * 单独在主页加载完后，发起一个ajax请求来加载LeftMenu
//     *
//     * @return
//     */
//    @RequestMapping(value = "main/leftmenu", method = RequestMethod.GET)
//    public String leftMenu(Model model) {
//
//        Map<Sys_Menu, Collection<Sys_Menu>> map = null;
//        if (isAdmin()) {
//            map = menuService.getLeftMenu();
//        } else if(isCurrentGroupAdmin()){
//            map = menuService.getLeftMenuWithCurrentGroupAdmin();
//        }else {
//            map = menuService.getLeftMenuWithPermission();
//        }
//
//        model.addAttribute("leftmenu", map);
//
//        return "main/leftmenu";
//    }
//
//
//    /**
//     * 主页
//     * @return
//     */
//    @RequestMapping(value = "main/home", method = RequestMethod.GET)
//    public String home(){
//        return "main/home";
//    }
//
//    /**
//     * 帮助页面
//     * @return
//     */
//    @RequestMapping("main/help")
//    public String helpTab(){
//        return "main/help";
//    }
//
//    @RequestMapping("logout")
//    public String logout(HttpServletRequest request) {
//        Subject currentUser = SecurityUtils.getSubject();
//        currentUser.logout();
//
//        //removeCurrentUser();
//        request.getSession().invalidate();
//
//        return "redirect:/login";
//    }
//
//    /**
//     * 获取当前Shiro用户
//     * @return
//     */
//    protected ShiroRealm.ShiroUser getCurrentUser(){
//        ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
//        return user;
//    }
//
//    /**
//     * 是否是管理员
//     * @return
//     */
//    protected boolean isAdmin(){
//        ShiroRealm.ShiroUser user = getCurrentUser();
//
//        if(user.loginName.toLowerCase().equals("admin") || user.loginName.toLowerCase().equals("superadmin")){
//            return true;
//        }
//
//        return false;
//
//    }
//
//    /**
//     * 是否是当前公司的管理员
//     * @return
//     */
//    protected boolean isCurrentGroupAdmin(){
//        ShiroRealm.ShiroUser user = getCurrentUser();
//        return user.user.getGroupadminflag();
//    }
}
