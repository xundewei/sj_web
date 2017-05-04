package com.sj.web.services.system;

import java.util.List;

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
	

}
