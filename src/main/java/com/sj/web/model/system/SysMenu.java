package com.sj.web.model.system;

import java.math.BigDecimal;

public class SysMenu {
    private String pkSysMenu;

    private String menucode;

    private String menuname;

    private String displayname;

    private BigDecimal displayorder;

    private BigDecimal lev;

    private String parentcode;

    private String icon;

    private String url;

    private String enableflag;

    private String adminflag;

    private String menutype;

    private String remakr;

    private String createuser;

    private String ts;

    private String dr;

    public String getPkSysMenu() {
        return pkSysMenu;
    }

    public void setPkSysMenu(String pkSysMenu) {
        this.pkSysMenu = pkSysMenu == null ? null : pkSysMenu.trim();
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
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname == null ? null : displayname.trim();
    }

    public BigDecimal getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(BigDecimal displayorder) {
        this.displayorder = displayorder;
    }

    public BigDecimal getLev() {
        return lev;
    }

    public void setLev(BigDecimal lev) {
        this.lev = lev;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode == null ? null : parentcode.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getEnableflag() {
        return enableflag;
    }

    public void setEnableflag(String enableflag) {
        this.enableflag = enableflag == null ? null : enableflag.trim();
    }

    public String getAdminflag() {
        return adminflag;
    }

    public void setAdminflag(String adminflag) {
        this.adminflag = adminflag == null ? null : adminflag.trim();
    }

    public String getMenutype() {
        return menutype;
    }

    public void setMenutype(String menutype) {
        this.menutype = menutype == null ? null : menutype.trim();
    }

    public String getRemakr() {
        return remakr;
    }

    public void setRemakr(String remakr) {
        this.remakr = remakr == null ? null : remakr.trim();
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
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