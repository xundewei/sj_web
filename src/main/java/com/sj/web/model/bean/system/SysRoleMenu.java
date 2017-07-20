package com.sj.web.model.bean.system;

import java.io.Serializable;

public class SysRoleMenu implements Serializable {
    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -6558094255823323415L;

	private String pkSysRoleMenu;

    private String pkSysRole;

    private String pkSysMenu;

    private String dr;

    private String ts;

    public String getPkSysRoleMenu() {
        return pkSysRoleMenu;
    }

    public void setPkSysRoleMenu(String pkSysRoleMenu) {
        this.pkSysRoleMenu = pkSysRoleMenu == null ? null : pkSysRoleMenu.trim();
    }

    public String getPkSysRole() {
        return pkSysRole;
    }

    public void setPkSysRole(String pkSysRole) {
        this.pkSysRole = pkSysRole == null ? null : pkSysRole.trim();
    }

    public String getPkSysMenu() {
        return pkSysMenu;
    }

    public void setPkSysMenu(String pkSysMenu) {
        this.pkSysMenu = pkSysMenu == null ? null : pkSysMenu.trim();
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr == null ? null : dr.trim();
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts == null ? null : ts.trim();
    }
}