package com.sj.web.dao.system;

import java.util.List;

import com.sj.web.model.system.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String pkSysMenu);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(String pkSysMenu);
    
    SysMenu selectByMenuCode(String menucode);

    List<SysMenu> selectAll();
    
    List<SysMenu> selectBylev1(String pk_sys_user);
    
    List<SysMenu> GetMenuByMoreLev2(String pk_sys_user);

    int updateByPrimaryKey(SysMenu record);
}