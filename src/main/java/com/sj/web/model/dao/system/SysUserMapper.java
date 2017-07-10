package com.sj.web.model.dao.system;

import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.model.bean.system.SysUser;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int deleteByPrimaryKey(String pkSysUser);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(String pkSysUser);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);
    
    /**
     * 
    * @Title: selectByUsercodePwd
    * @Description: 根据用户编码和密码找到用户对象
    * @param usercode 用户编码
    * @param pwd 密码
    * @return
    * @throws
     */
    SysUser selectByUsercodePwd(@Param("usercode") String usercode,@Param("pwd") String pwd);
    
    /**
     * 
    * @Title: selectAllByPage
    * @Description: 查找所有用户对象--翻页
    * @param page
    * @return
    * @throws
     */
    List<SysUser> selectAllByPage(Page<SysUser> page);
    
    
    /**
     * 
    * @Title: selectByUserCode
    * @Description: 通过用户编码查询用户
    * @param usercode 用户编码（包含注销的用户）
    * @return
    * @throws
     */
    SysUser selectByUserCode(String usercode);
    
    
}