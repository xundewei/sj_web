package com.sj.web.services.system;

import java.util.List;
import java.util.Map;

import com.sj.core.utils.web.JsonResult;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.model.bean.system.SysRole;



/**
 * 角色业务处理
 */

public interface RoleService{
	
	/**
	 * 
	* @Title: getAllSysRole
	* @Description: 
	* @return
	* @throws
	 */
	public Map<String, Object> getAllSysRole();
	
	
	/**
	 * 根据主键查找角色数据
	 * @param 
	 * 		pkSysRole 主键
	 * @return
	 */
	public JsonResult getByPrimaryKey(String pkSysRole);
	
	
	
	/**
	 * 新增角色数据接口
	 * @param 
	 * 		record 角色数据
	 * @return
	 */
	public JsonResult addSysRole(SysRole record,ShiroUser shiroUser);
	
	
	
	/**
	 * 根据主键更新角色数据
	 * @param 
	 * 		record 更新的数据
	 * @return
	 */
	public JsonResult modifySysRoleByPrimaryKey(SysRole record);
	
	
	/**
	 * 根据主键删除角色数据--物理删除
	 * @param 
	 * 		pkSysRole 主键
	 * @return
	 */
	public JsonResult removeByPrimaryKey(String pkSysRole);
	
	
	


	

	

	/**
	 * 找到所有角色数据
	 * @return
	 */
	public List<SysRole> selectAll();


	
	

	


    
   

}
