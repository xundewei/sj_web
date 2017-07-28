package com.sj.web.services.system;

import java.util.List;

import com.sj.core.utils.web.JsonResult;
import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.model.bean.system.SysOrg;
import com.sj.web.model.vo.system.OrgTreeGridVO;

/**
 * 
* @ClassName: OrgService
* @Description: 机构的业务实现接口
* @author TODY happyming886@126.com
* @date 2017年7月8日 上午11:56:24
*
 */
public interface OrgService{
	
	
	/**
	 * 
	* @Title: getAllOrg
	* @Description: 查找所有的机构VO
	* @return
	* @throws
	 */
	public List<OrgTreeGridVO> getAllOrg();
	
	
	
	/**
	 * 
	* @Title: getByPrimaryKey
	* @Description: 根据主键找到机构VO
	* @param pk_sys_org
	* @return
	* @throws
	 */
	public OrgTreeGridVO getByPrimaryKey(String pk_sys_org);
	
	
	
	
	/**
	 * 
	* @Title: addSysOrg
	* @Description: 新增一个机构
	* @param sysorg 机构对象
	* @param shiroUser 登入用户对象
	* @return
	* @throws
	 */
	public String addSysOrg(SysOrg sysorg,ShiroUser shiroUser);
	
	
	

	/**
	 * 
	* @Title: modifySysOrg
	* @Description: 更新机构
	* @param sysorg
	* @return
	* @throws
	 */
	public JsonResult modifySysOrg(SysOrg sysorg,ShiroUser shiroUser);
	
	
	
	/**
	 * 
	* @Title: removeSysOrg
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param pk_sys_org
	* @return
	* @throws
	 */
	public JsonResult removeSysOrg(String pk_sys_org,String orgcode);
	
	
	
	
	
	
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
	
}
