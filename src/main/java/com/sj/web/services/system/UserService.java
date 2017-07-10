package com.sj.web.services.system;

import java.util.Map;

import com.sj.web.model.bean.system.SysUser;



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
    
    /**
     * 根据过滤条件查询所有用户
     * @param 
     * 		searchFilter 查询的条件
     * @return
     * 		MAP 多少记录和具体记录数
     */
    public Map<String,Object> getAllSysUsers(String searchFilter);

}
