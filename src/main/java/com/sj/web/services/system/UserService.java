package com.sj.web.services.system;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.sj.core.utils.web.JsonResult;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.model.bean.system.SysUser;
import com.sj.web.model.vo.RequestParamVO;


/**
 * 
* @ClassName: UserService
* @Description: 用户服务接口
* @author TODY happyming886@126.com
* @date 2017年7月11日 上午10:08:35
*
 */
public interface UserService{
	
    /**
     * 根据用户名和密码找到相关用户
     * @param 
     * 		usercode 用户名  
     * 		pwd 密码
     * @return
     */
    public SysUser getByLogin(String usercode,String pwd);
    
    /**
     * 根据过滤条件查询所有用户
     * @param 
     * 		searchFilter 查询的条件
     * @return
     * 		MAP 多少记录和具体记录数
     */
    public Map<String,Object> getAllSysUsers(String searchFilter);
    
    
    /**
     * 
    * @Title: add
    * @Description: 用户新增
    * @param sysuser 用户对象
    * @return
    * @throws
     */
    public JsonResult add(SysUser sysuser,ShiroUser shiroUser);
    
    
    
    /**
     * 
    * @Title: getSysUserByPrimaryKey
    * @Description: 根据主键获取用户对象
    * @param pk_sys_user
    * @return
    * @throws
     */
    public JsonResult getSysUserByPrimaryKey(String pk_sys_user);
    
    
    /**
     * 
    * @Title: modifySysUser
    * @Description: 更新用户操作
    * @param entity
    * @return
    * @throws
     */
    public JsonResult modifySysUser(SysUser entity);
    
    
    /**
     * 
    * @Title: removeSysUser
    * @Description: 删除用户操作
    * @param pk_sys_user
    * @return
    * @throws
     */
    public JsonResult removeSysUser(String pk_sys_user);
    
    
    /**
     * 
    * @Title: modifySysUserPwd
    * @Description: 更新用户操作
    * @param entity{parma1 用户主键 parma2 需要设置的密码}
    * @return
    * @throws
     */
    public JsonResult modifySysUserPwd(RequestParamVO entity);
    
    
    
    public ModelMap  modifyUserRole(String pk_sys_user,ModelMap model);
    

}
