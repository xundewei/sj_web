package com.sj.web.services.system;

import java.util.List;

import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.web.model.system.SysOrg;



/**
 * 
 * @Description: 机构业务实现接口
 * @author tody
 * @date 2017年5月4日下午8:56:59
 */
public interface OrgService{
	
	/**
	 * 取到全部机构
	 * @return
	 */
	public List<SysOrg> getAllOrg();
	
	
	/**
	 * 取到全部机构
	 * @return
	 */
	public List<EzTreeNode> getAllOrgTree();
	
	/**
	 * 根据ORGCODE找到机构
	 * @return
	 */
	public List<SysOrg> getByOrgcode(String orgcode);
	
	/**
	 * 根据ORGCODE找到是否末级机构
	 * @return
	 */
	public List<SysOrg> getByLastOrg(String orgcode);
	
	
	/**
	 * 根据ORGCODE找到机构
	 * @return
	 */
	public SysOrg selectByPrimaryKey(String pk_sys_org);
	
	
	/**
	 * 新增机构操作
	 * @return
	 */
	public int addSysOrg(SysOrg sysorg);
	
	
	/**
	 * 更新操作
	 * @return
	 */
	public int updateSysOrg(SysOrg sysorg);
	
	
	/**
	 * 删除机构操作
	 * @return
	 */
	public int deleteSysOrg(String pk_sys_org);
	

}
