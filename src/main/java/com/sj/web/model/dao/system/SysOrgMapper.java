package com.sj.web.model.dao.system;

import com.sj.web.model.bean.system.SysOrg;
import java.util.List;

/**
 * 
* @ClassName: SysOrgMapper
* @Description: 机构DAO
* @author TODY happyming886@126.com
* @date 2017年7月8日 上午11:34:03
*
 */
public interface SysOrgMapper {
	
	/**
	 * 
	* @Title: deleteByPrimaryKey
	* @Description: 根据主键删除机构
	* @param pkSysOrg
	* @return
	* @throws
	 */
    int deleteByPrimaryKey(String pkSysOrg);

    /**
     * 
    * @Title: insert
    * @Description: 新增机构
    * @param record 机构对象
    * @return
    * @throws
     */
    int insert(SysOrg record);

    /**
     * 
    * @Title: selectByPrimaryKey
    * @Description:  根据主键查找机构
    * @param pkSysOrg 机构主键
    * @return
    * @throws
     */
    SysOrg selectByPrimaryKey(String pkSysOrg);

    /**
     * 
    * @Title: selectAll
    * @Description: 查询所有的机构
    * @return
    * @throws
     */
    List<SysOrg> selectAll();

    /**
     * 
    * @Title: updateByPrimaryKey
    * @Description: 根据主键更新机构
    * @param record
    * @return
    * @throws
     */
    int updateByPrimaryKey(SysOrg record);
}