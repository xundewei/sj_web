package com.sj.web.services.system;

import java.util.List;

import com.sj.core.utils.web.easyui.EzTreeNode;
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
     * 		pk_sys_user  用户主键
     * @return
     */
    public List<SysMenu> GetMenuBylev1(String pk_sys_user);
    
    /**
     * 根据角色和父节点编码找到菜单下面所有的菜单
     * @param 
     *      pk_sys_user 用户主键
     * 		menucode  一级菜单CODE(父节点编码)
     * @return
     */
    public List<EzTreeNode> GetMenuByMoreLev2(String pk_sys_user,String menucode);
    

}
