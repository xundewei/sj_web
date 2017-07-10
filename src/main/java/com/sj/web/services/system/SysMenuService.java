package com.sj.web.services.system;

import java.util.List;

import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.web.model.bean.system.SysMenu;
import com.sj.web.model.vo.system.MenuTreeGridVO;


/**
 * 菜单服务接口
 * @author TODY
 * 2017年4月24日14:52:17
 *
 */
public interface SysMenuService{
	

	/**
	 * 
	* @Title: getMenuAccordion
	* @Description: 菜单-百叶窗接口 lev=1
	* @param pk_sys_user 用户主键
	* @return
	* @throws
	 */
	public List<SysMenu> getMenuAccordion(String pk_sys_user);
	
	/**
	 * 
	* @Title: getMenuLeftTree
	* @Description: 菜单-百叶窗-tree接口 lev>=2
	* @param pk_sys_user
	* @param menucode
	* @return
	* @throws
	 */
	public List<EzTreeNode> getMenuLeftTree(String pk_sys_user,String menucode);
	
	
	/**
	 * 
	* @Title: getAll
	* @Description: 查找所有的菜单
	* @return
	* @throws
	 */
    public  List<MenuTreeGridVO>  getAll();
	
	
    /**
     * 
    * @Title: addSysMenu
    * @Description: 新增菜单
    * @param sysmenu 菜单对象
    * @return
    * @throws
     */
    public String addSysMenu(SysMenu sysmenu);
	

    
    
  
    /**
     * 根据主键找到菜单
     * @param 
     * 		pkSysMenu 主键
     * @return
     */
    public MenuTreeGridVO getByPrimaryKey(String pkSysMenu);
	

	
 
    
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
    

}
