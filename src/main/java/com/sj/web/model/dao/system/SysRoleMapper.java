package com.sj.web.model.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.model.bean.system.SysRole;
import com.sj.web.model.vo.system.SysUserRoleVO;

/**
 * 
* @ClassName: SysRoleMapper
* @Description: 角色 DAO
* @author TODY happyming886@126.com
* @date 2017年7月8日 上午11:34:27
*
 */
public interface SysRoleMapper {
	
	
	
	 /**
     * 
    * @Title: selectAllByPage
    * @Description: 获取翻页数据
    * @param page
    * @return
    * @throws
     */
    List<SysRole> selectAllByPage(Page<SysRole> page);
	
	
	
    /**
     * 
    * @Title: selectByPrimaryKey
    * @Description: 根据主键查找角色
    * @param pkSysRole 角色主键
    * @return
    * @throws
     */
    SysRole selectByPrimaryKey(String pkSysRole);
	
	
	
	
    /**
     * 
    * @Title: insert
    * @Description: 新增角色
    * @param record 角色对象
    * @return
    * @throws
     */
    int insert(SysRole record);
	
	
	
	
    /**
     * 
    * @Title: selectAllByPage
    * @Description: 获取翻页数据
    * @param page
    * @return
    * @throws
     */
    List<SysRole> selectByRoleCode(String rolecode);
	
	
	
	
	
	
	
	
	/**
	 * 
	* @Title: deleteByPrimaryKey
	* @Description: 根据主键删除角色
	* @param pkSysRole 角色主键
	* @return
	* @throws
	 */
    int deleteByPrimaryKey(String pkSysRole);

   
    
    
    /**
     * 
    * @Title: selectByPkSysUser
    * @Description: 根据用户主键查找所有角色
    * @param PkSysUser 用户主键
    * @return
    * @throws
     */
    List<SysRole> selectByPkSysUser(String PkSysUser);
    

   

    /**
     * 
    * @Title: selectAll
    * @Description: 查询所有的角色
    * @return
    * @throws
     */
    List<SysRole> selectAll();

    /**
     * 
    * @Title: updateByPrimaryKey
    * @Description: 根据主键更新角色
    * @param record 角色对象
    * @return
    * @throws
     */
    int updateByPrimaryKey(SysRole record);
    
    
    /**
     * 
    * @Title: selectByRoleCodeAndRoleName
    * @Description: 通过角色编码和角色名称 获取角色对象数组
    * @param rolecode
    * @param rolename
    * @return
    * @throws
     */
    List<SysRole> selectByRoleCodeAndRoleName(@Param("rolecode") String rolecode,@Param("rolename") String rolename);
    
   
    
}