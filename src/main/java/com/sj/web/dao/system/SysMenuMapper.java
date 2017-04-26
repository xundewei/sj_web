package com.sj.web.dao.system;

import com.sj.web.model.system.SysMenu;
import com.sj.web.model.system.SysMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(String pkSysMenu);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(String pkSysMenu);
    
    List<SysMenu> selectBylev1(String pk_sys_user);
    
    List<SysMenu> GetMenuByMoreLev2(String pk_sys_user);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    
    
}