package com.sj.web.services.system;

import java.util.List;

import com.sj.web.model.system.SysRole;
import com.sj.web.model.system.SysUser;



/**
 * 用户基本信息业务类
 */

public interface RoleService{
	
    /**
     * 根据用户主键找到相关的权限
     * @param 
     * 		usercode 用户名  
     * 		pwd 密码
     * @return
     */
    public List<SysRole> getRolesByUserPk(String pkSysUser);
    
   

}
