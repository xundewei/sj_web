package com.sj.web.model.bean.system;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sj.core.utils.DateUtil;

public class SysOrg implements Serializable{
	private static final long serialVersionUID = 1L;

	private String pkSysOrg;

    private String orgcode;

    private String orgname;

    private String parentcode;

    private BigDecimal displayorder;

    private String duty;

    private boolean enableflag;

    private String remark;

    private String createuser;

    private String ts;

    private String dr;

    public String getPkSysOrg() {
        return pkSysOrg;
    }

    public void setPkSysOrg(String pkSysOrg) {
        this.pkSysOrg = pkSysOrg == null ? null : pkSysOrg.trim();
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode == null ? null : orgcode.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode == null ? null : parentcode.trim();
    }

    public BigDecimal getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(BigDecimal displayorder) {
        this.displayorder = displayorder;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public boolean getEnableflag() {
        return enableflag;
    }

    public void setEnableflag(boolean enableflag) {
        this.enableflag = enableflag ;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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