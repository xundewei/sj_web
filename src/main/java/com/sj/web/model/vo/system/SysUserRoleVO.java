package com.sj.web.model.vo.system;

import java.io.Serializable;

public class SysUserRoleVO implements Serializable {

	private static final long serialVersionUID = -5082765707559724031L;

	private String pkSysUserRole;

	private String pkSysUser;

	private String usercode;

	private String username;

	private String pkSysRole;

	private String rolecode;

	private String rolename;
	
	private boolean isown;
	
	private String ts;

	private String dr;


	public boolean isIsown() {
		return isown;
	}

	public void setIsown(boolean isown) {
		this.isown = isown;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}


	public String getPkSysUserRole() {
		return pkSysUserRole;
	}

	public void setPkSysUserRole(String pkSysUserRole) {
		this.pkSysUserRole = pkSysUserRole == null ? null : pkSysUserRole.trim();
	}

	public String getPkSysUser() {
		return pkSysUser;
	}

	public void setPkSysUser(String pkSysUser) {
		this.pkSysUser = pkSysUser == null ? null : pkSysUser.trim();
	}

	public String getPkSysRole() {
		return pkSysRole;
	}

	public void setPkSysRole(String pkSysRole) {
		this.pkSysRole = pkSysRole == null ? null : pkSysRole.trim();
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts == null ? null : ts.trim();
	}

	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr == null ? null : dr.trim();
	}
}