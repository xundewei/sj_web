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
     * 找到所有菜单
     * @return
     */
    public List<SysMenu> getAll();
    
    
  
    /**
     * 根据主键找到菜单
     * @param 
     * 		pkSysMenu 主键
     * @return
     */
    public SysMenu getByPrimaryKey(String pkSysMenu);
	
    /**
     * 根据编码找到菜单
     * @param 
     * 		menucode 菜单编码
     * @return 
     * 		菜单记录
     */
    public SysMenu SelectByMenuCode(String menucode);
	
    /**
     * 新增菜单数据
     * @param 
     * 		sysmenu 新增的数据
     * @return
     */
    public  int addSysMenu(SysMenu sysmenu);
    
    /**
     * 通过主键修改菜单数据
     * @param 
     * 		record 修改的数据
     * @return
     */
    public int updateByPrimaryKey(SysMenu record);
    
    
  
    /**
     * 通过主键删除记录
     * @param 
     * 		pkSysMenu 菜单主键
     * @return
     */
    public int deleteByPrimaryKey(String pkSysMenu);
    
    
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
