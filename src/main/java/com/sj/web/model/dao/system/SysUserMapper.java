package com.sj.web.model.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.model.bean.system.SysUser;

/**
 * 
* @ClassName: SysUserMapper
* @Description: 用户 DAO
* @author TODY happyming886@126.com
* @date 2017年7月8日 上午11:34:49
*
 */
public interface SysUserMapper {
	/**
	 * 
	* @Title: deleteByPrimaryKey
	* @Description: 根据主键删除用户
	* @param pkSysUser 用户主键
	* @return
	* @throws
	 */
    int deleteByPrimaryKey(String pkSysUser);

    /**
     * 
    * @Title: insert
    * @Description: 新增用户
    * @param record 用户对象
    * @return
    * @throws
     */
    int insert(SysUser record);

    /**
     * 
    * @Title: selectByPrimaryKey
    * @Description: 根据主键查找用户
    * @param pkSysUser 用户主键
    * @return
    * @throws
     */
    SysUser selectByPrimaryKey(String pkSysUser);

    /**
     * 
    * @Title: selectAll
    * @Description: 查询所有的用户
    * @return
    * @throws
     */
    List<SysUser> selectAll();
    
    
    
    /**
     * 
    * @Title: selectByUsercodePwd
    * @Description: 根据用户编码和密码获得用户对象
    * @param usercode 用户编码
    * @param pwd 密码
    * @return
    * @throws
     */
    SysUser selectByUsercodePwd(@Param("usercode") String usercode,@Param("pwd") String pwd);
  
    
    /**
     * 
    * @Title: selectAll
    * @Description: 查找所有用户，翻页
    * @param page
    * @return
    * @throws
     */
    List<SysUser> selectAllByPage(@Param("page") Page<SysUser> page);

    /**
     * 
    * @Title: updateByPrimaryKey
    * @Description: 根据主键更新用户
    * @param record
    * @return
    * @throws
     */
    int updateByPrimaryKey(SysUser record);
}