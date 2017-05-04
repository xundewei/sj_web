package com.sj.web.dao.system;

import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.model.system.SysUser;
import com.sj.web.model.system.SysUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;




public interface SysUserMapper {
    int countByExample(SysUserExample example);
    int countByExample2(Page<SysUser> page);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(String pkSysUser);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);
    

    SysUser selectByPrimaryKey(String pkSysUser);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    SysUser selectByUsercode_Pwd(@Param("usercode") String usercode,@Param("pwd") String pwd);
    
    List<SysUser> selectAll(@Param("page") Page<SysUser> page);
}