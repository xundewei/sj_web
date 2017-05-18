package com.sj.web.services.system;

import java.util.List;

import com.sj.web.model.system.SysRole;
import com.sj.web.model.system.SysUser;



/**
 * 角色业务处理
 */

public interface RoleService{
	
	
	/**
	 * 根据主键删除角色数据--物理删除
	 * @param 
	 * 		pkSysRole 主键
	 * @return
	 */
	public int deleteByPrimaryKey(String pkSysRole);

	/**
	 * 新增角色数据接口
	 * @param 
	 * 		record 角色数据
	 * @return
	 */
	public int insert(SysRole record);

	/**
	 * 根据主键查找角色数据
	 * @param 
	 * 		pkSysRole 主键
	 * @return
	 */
	public SysRole selectByPrimaryKey(String pkSysRole);

	/**
	 * 找到所有角色数据
	 * @return
	 */
	public List<SysRole> selectAll();

	/**
	 * 根据主键更新角色数据
	 * @param 
	 * 		record 更新的数据
	 * @return
	 */
	public int updateByPrimaryKey(SysRole record);
	
    /**
     * 根据用户主键找到相关的权限
     * @param 
     * 		pkSysUser 用户主键
     * @return
     */
    public List<SysRole> getRolesByUserPk(String pkSysUser);
    
   

}
