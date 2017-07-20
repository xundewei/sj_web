package com.sj.web.model.vo.system;

import java.io.Serializable;

public class SysRoleMenuVO implements Serializable {
    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2391822254214015464L;

	private String pkSysRoleMenu;

    private String pkSysRole;
    
    private String rolecode;

	private String rolename;

    private String pkSysMenu;
    
    private String menucode;

    private String menuname;
    
    private String dr;

    private String ts;
    
    
    

    public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode == null ? null :rolecode.trim();
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename == null ? null :rolename.trim();
	}

	public String getMenucode() {
		return menucode;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode == null ? null : menucode.trim();
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname == null ? null :menuname.trim();
	}

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