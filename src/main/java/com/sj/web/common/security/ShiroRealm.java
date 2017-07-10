package com.sj.web.common.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.sj.core.utils.SpringUtils;
import com.sj.web.model.bean.system.SysUser;
import com.sj.web.services.system.UserService;


/**
 * 目前只用于简单的权限管理，菜单管理
 * @Description: 自定义的ShiroRealm，用于进行用户验证及授权
 * @author tody
 * @date 2017年5月9日上午10:40:57
 */
public class ShiroRealm extends AuthorizingRealm {

    //注意：自定义Realm里面不能使用@Autowired来注入Bean，否则会因为Bean加载顺序的问题导致对应Service的EhCahce失效
    private UserService userService;

   

    public UserService getUserService() {
        if(userService == null) {
            userService = SpringUtils.getBean(UserService.class);
        }

        return userService;
    }

  

    /**
     * 授权，即向通过认证的用户赋予指定的权限
     * 每次通过Shiro获取权限/判断权限时均会触发此方法
     * 当配置缓存后，此方法在当次登录后，仅对当前用户仅调用一次(缓存未过期的情况下)
     * 当退出系统时，会自动清除缓存
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
       System.out.println("在什么时候执行");
        //获取当前登录的用户名
//        String userLoginName = shiroUser.username;
//        String userpk = shiroUser.pkSysUser;
//        String usercode = shiroUser.usercode;
//
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        
//        //only for test:
        //赋予权限，此处手工添加一个”admin”的角色，以及创建/编辑/删除用户的权限
        //实际的业务中，应该根据用户标识，从数据库中获取其真正的角色和权限。
        info.addRole("admin");
        info.addStringPermission("emp:view,add,update,delete");
//
//        //********************此处开始获取当前用户的权限角色信息********************************
//        shiroUser.RoleList = new ArrayList<SysRole>();
//        List<SysRole> userrolesList = getUserRolesService().getListByUserId(userId);
//        for (Sys_Userroles userroles : userrolesList) {
//            String roleId = userroles.getRoleid();
//            AppRole appRole = getAppRoleService().getAppRoleByRoleId(roleId);
//            if (appRole != null) {
//                //1.添加自定义的权限角色到shiro中
//                shiroUser.appRoleList.add(appRole);
//
//                //2.添加角色名称到shiro
//                info.addRole(appRole.getName());
//
//                //3.添加操作Actions到shiro
//                for (AppRole.AppPermMenu appPermMenu : appRole.appPermMenuList) {
//                    String actions = appPermMenu.getActions();
//                    if (StringUtils.isEmpty(actions)) {
//                        actions = "view";
//                    }
//
//                    info.addStringPermission(appPermMenu.getMenukey() + ":" + actions);
//                }
//            }
//        }
//
//        Sys_User dbUser = getUserService().get(userId);
//        //4.添加当前用户所在部门的全部用户列表到shiro中
//        shiroUser.currentOrgaUserList = getUserService().getListByOrgaId(dbUser.getOrgaid());
//        //*********************************************************************************
//
////        User user = accountService.findUserByLoginName(shiroUser.loginName);
////        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
////        for (Role role : user.getRoleList()) {
////            // 基于Role的权限信息
////            info.addRole(role.getName());
////            // 基于Permission的权限信息
////            info.addStringPermissions(role.getPermissionList());
////        }

        return info;
    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载。
     * 当异常退出时（如非法关闭浏览器等），缓存不会自动清除，那么就需要手动清除
     *
     * @param principal
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 认证，即用户登录时进行的认证动作 doLogin
     * 一般在用户登录时触发
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户输入的登录名称和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userLoginCode = token.getUsername();  //得到登入名
        String password = new String(token.getPassword()); //得到密码
        //进行加密
//        password = Utils.getMD5(password).toUpperCase();
        token.setPassword(password.toCharArray());

        //从数据库中查询用户用信息
        SysUser dbUser = getUserService().getByLogin(userLoginCode, password);
        if (dbUser == null)
            return null;


        ShiroUser shiroUser = new ShiroUser(dbUser);

        //此处无需对比,对比的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, dbUser.getPwd(), getName());
       
        //如果验证通过，那么清除上次授权的缓存,跟原来的TOKEN比较
        if (StringUtils.equals(userLoginCode, dbUser.getUsercode()) && StringUtils.equals(password,dbUser.getPwd() )) {
            clearCache(info.getPrincipals());
            clearCachedAuthorizationInfo(info.getPrincipals());
        }

        return info;
    }
}
