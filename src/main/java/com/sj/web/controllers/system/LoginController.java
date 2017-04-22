package com.sj.web.controllers.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.web.model.system.SysUser;
import com.sj.web.services.system.UserService;



/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * <p/>
 * 真正登录的POST请求由Filter完成,
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	 @Autowired
	private UserService service;


	/**
	 * GET 登入界面
	 * @param model
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("errorMessage", "");
        return "main/login";
    }

    /**
     * POST 登入请求
     * @param userName
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String index(@RequestParam("username") String userName,
                        @RequestParam("password") String password, Model model) {

        String errorMsg = "";
        if (StringUtils.isEmpty(userName) && StringUtils.isEmpty(password)) {
            errorMsg = "提示信息：登录用户和密码不能为空！";
        } else if (StringUtils.isEmpty(userName)) {
            errorMsg = "提示信息：登录用户不能为空！";
        } else if (StringUtils.isEmpty(password)) {
            errorMsg = "提示信息：登录密码不能为空！";
        }

        if (!StringUtils.isEmpty(errorMsg)) {
            model.addAttribute("errorMessage", errorMsg);
            return "main/login";
        }

//        UsernamePasswordToken token = null;
        try {
            SysUser user = service.getByLogin(userName,password);

            if (user == null) {
                errorMsg = "提示信息：用户名或密码不正确，请重新输入！";
            } else {
                //已禁用\已删除
                if ("1".equals(user.getFlag()) || "1".equals(user.getDr())) {
                    errorMsg = "提示信息：登录用户已被禁用";
                } else {
                    //shiro login check
//                    appRealm.clearCachedAuthorizationInfo(userName);
//                    token = new UsernamePasswordToken(userName, password);
//                    token.setRememberMe(true);
//                    Subject currentUser = SecurityUtils.getSubject();
//                    currentUser.login(token);
                    return "redirect:/";
                }
            }

        } catch (Exception e) {
//            if(token != null)
//                token.clear();
            errorMsg = "提示信息：用户名或密码不正确，请重新输入！";
        }
        model.addAttribute("errorMessage", errorMsg);
        return "main/login";
    }
}
