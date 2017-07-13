package com.sj.web.model.dao.system;

import com.sj.web.model.bean.system.SysUserRole;
import com.sj.web.model.vo.system.SysUserRoleVO;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String pkSysUserRole);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(String pkSysUserRole);

    List<SysUserRole> selectAll();
    
    

    int updateByPrimaryKey(SysUserRole record);
    
    
    
    /**
     * 
    * @Title: selectByPkSysUserAllRoles
    * @Description: 根据用户主键获取所有的角色
    * @param pkSysUser
    * @return
    * @throws
     */
    List<SysUserRoleVO> selectByPkSysUserAllRoles(String pkSysUser);
}