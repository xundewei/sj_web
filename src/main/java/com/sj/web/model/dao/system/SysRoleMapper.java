package com.sj.web.model.dao.system;

import java.util.List;

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
    
   
    
}