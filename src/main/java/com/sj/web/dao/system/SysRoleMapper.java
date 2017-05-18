package com.sj.web.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sj.web.model.system.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String pkSysRole);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(String pkSysRole);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> selectByPkSysUser(@Param("pkSysUser") String pkSysUser);
}