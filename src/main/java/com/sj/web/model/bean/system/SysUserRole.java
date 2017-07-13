package com.sj.web.model.bean.system;

public class SysUserRole {
    private String pkSysUserRole;

    private String pkSysUser;

    private String pkSysRole;

    private String ts;

    private String dr;

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