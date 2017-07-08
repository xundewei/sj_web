package com.sj.web.model.dao.system;

import com.sj.web.model.bean.system.SysUser;
import java.util.List;

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
    * @Title: updateByPrimaryKey
    * @Description: 根据主键更新用户
    * @param record
    * @return
    * @throws
     */
    int updateByPrimaryKey(SysUser record);
}