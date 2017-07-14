package com.sj.web.controllers.system;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.web.common.Consts;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.bean.system.SysUser;
import com.sj.web.services.system.SysMenuService;



/**
 * 系统主控制器，负责处理一些系统级别的请求
 * 主框架的请求
 */
@Controller
@RequestMapping("/")
public class MainController extends BaseController{

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);
    
    @Autowired
   	private SysMenuService sysMenuService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        
       
        //页面加载用户信息
        ShiroUser shiroUser = getCurrentShiroUser();
        
        //设置皮肤和展现的模式
        boolean tabmode = Consts.DEFAULT_TAB_MODE;
        String theme =null;
        
        
        if(shiroUser != null){
            tabmode = shiroUser.user.getTabmode();
            
            if(!StringUtils.isEmpty(shiroUser.user.getTheme())){
            	theme =  shiroUser.user.getTheme();
            }else{
            	theme =Consts.DEFAULT_THEME;
            }
            
        }
        model.addAttribute("tabmode", tabmode);
        model.addAttribute("theme", theme);
        model.addAttribute("shirouser", shiroUser.user);
        model.addAttribute("message", "Hello world!");
        model.addAttribute("groupname", Consts.GROUPNAME);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -10);
        logger.info("添加完成相关配置界面需要的配置信息！！");
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
    
    
    @RequestMapping(value = "main/help", method = RequestMethod.GET)
    public String help(){
    	 logger.info("主界面加载控制完成！");
        return "main/help";
    }
    
    /**
     * 左边菜单导航界面(这边只加载一级菜单[二级以下菜单使用TREE方式展现])
     * 要求：只显示级别为1
     * @param model
     * @return
     */
    @RequestMapping(value = "main/leftmenu", method = RequestMethod.GET)
    public String leftMenu(Model model) {
        //这边需要根据TOKEN知道相关用户主键 后续添加
        model.addAttribute("leftmenu", sysMenuService.getMenuAccordion("1"));
        logger.info("菜单页面加载控制完成！");
        return "main/leftmenu";
    }
    
    /**
     * 构造树的功能
     * @param 
     * 		menucode 一级菜单编码
     * @return
     */
    @RequestMapping(value = "main/menutree/{menucode}", method = RequestMethod.GET)
    @ResponseBody
    public List<EzTreeNode> loadMenuTree(@PathVariable String menucode) {
        return sysMenuService.getMenuLeftTree("1",menucode);
    }
   
}
