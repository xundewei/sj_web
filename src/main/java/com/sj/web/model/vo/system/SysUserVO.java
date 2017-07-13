package com.sj.web.model.vo.system;

import java.io.Serializable;

import com.sj.web.model.bean.system.SysUser;

/**
 * 
* @ClassName: SysUserVO
* @Description: 用于界面显示的
* @author TODY happyming886@126.com
* @date 2017年7月12日 上午11:05:13
*
 */
public class SysUserVO extends SysUser implements Serializable {

	private static final long serialVersionUID = 1390077300833998061L;
	
	private String orgcode;
	
	private String orgname;

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

}
