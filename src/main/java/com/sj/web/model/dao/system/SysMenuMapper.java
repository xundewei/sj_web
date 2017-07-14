package com.sj.web.model.dao.system;

import com.sj.web.model.bean.system.SysMenu;
import java.util.List;

/**
 * 
* @ClassName: SysMenuMapper
* @Description: 菜单DAO
* @author TODY happyming886@126.com
* @date 2017年7月8日 上午11:24:08
*
 */
public interface SysMenuMapper {
	/**
	 * 
	* @Title: deleteByPrimaryKey
	* @Description: 根据主键删除菜单
	* @param pkSysMenu 主键
	* @return
	* @throws
	 */
    int deleteByPrimaryKey(String pkSysMenu);

    /**
     * 
    * @Title: insert
    * @Description: 新增菜单
    * @param record 菜单对象
    * @return
    * @throws
     */
    int insert(SysMenu record);
    
    /**
     * 
    * @Title: updateByPrimaryKey
    * @Description: 根据主键更新菜单
    * @param record
    * @return
    * @throws
     */
    int updateByPrimaryKey(SysMenu record);

    /**
     * 
    * @Title: selectByPrimaryKey
    * @Description: 根据主键查找菜单
    * @param pkSysMenu 菜单主键
    * @return
    * @throws
     */
    SysMenu selectByPrimaryKey(String pkSysMenu);

    /**
     * 
    * @Title: selectAll
    * @Description: 查询所有的菜单
    * @return
    * @throws
     */
    List<SysMenu> selectAll();

    /**
     * 
    * @Title: selectMenuAccordion
    * @Description: 查询主界面菜单-百叶窗的数据
    * @param pk_sys_user 用户主键
    * @return
    * @throws
     */
    List<SysMenu> selectMenuAccordion(String pk_sys_user);
    
    
    /**
     * 
    * @Title: selectMenuLeftTree
    * @Description: 查询主界面菜单-百叶窗-tree的数据
    * @param pk_sys_user 用户主键
    * @return
    * @throws
     */
    List<SysMenu> selectMenuLeftTree(String pk_sys_user);
    
    /**
     * 
    * @Title: selectByMenuCode
    * @Description: 根据菜单编码查找菜单
    * @param menucode 菜单编码
    * @return
    * @throws
     */
    SysMenu selectByMenuCode(String menucode);
    
    
    
   
    /**
     * 
    * @Title: selectMenuByPkSysUser
    * @Description: 根据用户主键获取所有的菜单
    * @param pk_sys_user
    * @return
    * @throws
     */
    List<SysMenu> selectMenuByPkSysUser(String pk_sys_user);
    
}