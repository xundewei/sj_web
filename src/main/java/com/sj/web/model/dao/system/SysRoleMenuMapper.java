package com.sj.web.model.dao.system;

import java.util.List;

import com.sj.web.model.bean.system.SysRoleMenu;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(String pkSysRoleMenu);

    int insert(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(String pkSysRoleMenu);

    List<SysRoleMenu> selectAll();

    int updateByPrimaryKey(SysRoleMenu record);
    
//    /**
//     * 
//    * @Title: selectByPkSysRole
//    * @Description: 根据角色主键取到关系列表
//    * @param pkSysRole
//    * @return
//    * @throws
//     */
//    List<SysRoleMenuVO> selectByPkSysRole(String pkSysRole);
    
}