package com.sj.web.services.system;

import java.util.List;

import com.sj.web.model.system.SysMenu;


/**
 * 菜单服务接口
 * @author TODY
 * 2017年4月24日14:52:17
 *
 */
public interface SysMenuService{
	
    /**
     * 根据角色找到此角色下面的所有的一级菜单
     * @param 
     * 		usercode 用户名  
     * 		pwd 密码
     * @return
     */
    public List<SysMenu> GetMenuBylev1(String pk_sys_user);

}
