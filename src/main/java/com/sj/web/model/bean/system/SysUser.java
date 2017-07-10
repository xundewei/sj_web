package com.sj.web.model.bean.system;

import java.io.Serializable;

public class SysUser implements Serializable{
    /**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 1L;

	private String pkSysUser;

    private String usercode;

    private String username;

    private String pwd;

    private boolean tabmode;

    private String theme;

    private boolean flag;

    private String createuser;

    private String dr;

    private String ts;

    private String remark;

    private String sex;

    private String mobile;

    private String pkSysOrg;

    public String getPkSysUser() {
        return pkSysUser;
    }

    public void setPkSysUser(String pkSysUser) {
        this.pkSysUser = pkSysUser == null ? null : pkSysUser.trim();
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode == null ? null : usercode.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public boolean getTabmode() {
        return tabmode;
    }

    public void setTabmode(boolean tabmode) {
        this.tabmode = tabmode ;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPkSysOrg() {
        return pkSysOrg;
    }

    public void setPkSysOrg(String pkSysOrg) {
        this.pkSysOrg = pkSysOrg == null ? null : pkSysOrg.trim();
    }
}