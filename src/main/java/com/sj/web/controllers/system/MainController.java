package com.sj.web.controllers.system;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.web.common.Consts;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.system.SysMenu;
import com.sj.web.model.system.SysUser;
import com.sj.web.services.system.SysMenuService;



/**
 * 系统主控制器，负责处理一些系统级别的请求
 * 主框架的请求
 */
@Controller
@RequestMapping("/")
public class MainController extends BaseController{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);
//    @Autowired
//    private LoginInfoService loginInfoService;
    
    @Autowired
   	private SysMenuService sysmenuservice;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        model.addAttribute("message", "Hello world!");

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -10);
        
        model.addAttribute("groupname", Consts.GROUPNAME);

        //加载个人配置信息
//        Sys_Userconfig userconfig = userConfigService.get(shiroUser.id);
        SysUser sysuer = (SysUser) this.getCurrentSession().getAttribute("usermsg");
        boolean tabmode = Consts.DEFAULT_TAB_MODE;
        String theme = Consts.DEFAULT_THEME;

        if(sysuer != null){
//            tabmode = sysuer.getTabmode();
//            theme = sysuer.getTheme();
        }

        model.addAttribute("tabmode", tabmode);
        model.addAttribute("theme", theme);
        logger.info("添加完成相关配置界面需要的配置信息！！");
        //记录用户登录信息
//        addLoginInfo(request);

        return "main/main";
    }
    
    /**
     * 框架主页面
     * @return
     */
    @RequestMapping(value = "main/home", method = RequestMethod.GET)
    public String home(){
    	 logger.info("主界面加载控制完成！");
        return "main/home";
    }
    
 
    /**
     * 左边菜单导航界面(这边只加载一级菜单[二级以下菜单使用TREE方式展现])
     * 要求：只显示级别为1
     * @param model
     * @return
     */
    @RequestMapping(value = "main/leftmenu", method = RequestMethod.GET)
    public String leftMenu(Model model) {

//        Map<SysMenu, Collection<SysMenu>> map = null;
//        if (isAdmin()) {
//            map = menuService.getLeftMenu();
//        } else if(isCurrentGroupAdmin()){
//            map = menuService.getLeftMenuWithCurrentGroupAdmin();
//        }else {
//            map = menuService.getLeftMenuWithPermission();
//        }
        //这边需要根据TOKEN知道相关用户主键
        List<SysMenu> list = sysmenuservice.GetMenuBylev1("1");
        model.addAttribute("leftmenu", list);
        logger.info("菜单页面加载控制完成！");
        return "main/leftmenu";
    }
   
}
