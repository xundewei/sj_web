package com.sj.web.common.security;

import java.io.Serializable;
import java.util.List;

import com.sj.web.model.bean.system.SysMenu;
import com.sj.web.model.bean.system.SysRole;
import com.sj.web.model.bean.system.SysUser;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 * 除了用户基本信息外，还包含当前用户的权限信息：
 * 1.权限角色列表（每一个权限角色对应数据库里的角色，同时把此角色对应的各种权限都放入其中，方便使用）
 * 2.当前用户所在部门的全部用户列表，用于数据范围权限过滤
 */
public class ShiroUser  implements Serializable{

    private static final long serialVersionUID = -1373760761780840081L;
    public String pkSysUser;
    public String usercode;
    public String username;

    public SysUser user; //用户全部信息
    
    //当前用户的角色权限列表
    public List<SysRole> RoleList; 
    //当前用户的所有的菜单
    public List<SysMenu> MenuList; 
    
   

    public ShiroUser(SysUser dbUser) {
        this.pkSysUser = dbUser.getPkSysUser();
        this.usercode = dbUser.getUsercode();
        this.username = dbUser.getUsername();
        
        this.user = dbUser;
    }


    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return usercode;
    }

   
}
