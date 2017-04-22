package com.sj.web.services.system;

import com.sj.web.model.system.SysUser;



/**
 * 用户基本信息业务类
 */

public interface UserService{
	
    /**
     * 根据用户名和密码找到相关用户
     * @param 
     * 		usercode 用户名  
     * 		pwd 密码
     * @return
     */
    public SysUser getByLogin(String usercode,String pwd);

}
