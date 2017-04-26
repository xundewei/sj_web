package com.sj.core.utils.web.easyui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI TreeNode 包装类
 */
public class EzTreeNode implements Serializable {
    private static final long serialVersionUID = 4390613291619643206L;
    private String id ;
    private String pid ;
    private String text ;
    private String iconCls ;
    private String state ;
    private boolean checked ;
    private String attributes;
    private List<EzTreeNode> children = new ArrayList<EzTreeNode>() ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public List<EzTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<EzTreeNode> children) {
        this.children = children;
    }
}
