package com.sj.web.services.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.sj.core.utils.BeanMapper;
import com.sj.core.utils.web.JsonResult;
import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.model.bean.system.SysOrg;
import com.sj.web.model.bean.system.SysUser;
import com.sj.web.model.dao.system.SysOrgMapper;
import com.sj.web.model.dao.system.SysUserMapper;
import com.sj.web.model.dao.system.SysUserRoleMapper;
import com.sj.web.model.vo.RequestParamVO;
import com.sj.web.model.vo.system.SysUserRoleVO;
import com.sj.web.model.vo.system.SysUserVO;

/**
 * 用户基本信息业务类
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	protected static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysOrgMapper sysOrgMapper;
	
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	

	@Override
	public SysUser getByLogin(String usercode, String pwd) {
		SysUser user = sysUserMapper.selectByUsercodePwd(usercode, pwd);
		return user;
	}

	@Override
	public Map<String, Object> getAllSysUsers(String searchFilter) {
		Page<SysUser> page = new Page<SysUser>();
		List<SysUser> list = sysUserMapper.selectAllByPage(page);
		HashMap hm = new HashMap<>();
		hm.put("total", list.size());
		hm.put("rows", list);
		return hm;
	}

	@Override
	public JsonResult add(SysUser sysuser, ShiroUser shiroUser) {
		try {
			/*
			 * 判断用户编码是否存在
			 */
			SysUser sysuser1 = sysUserMapper.selectByUserCode(sysuser.getUsercode());
			if (sysuser1 != null) {
				return JsonResult.error("用户编号[" + sysuser.getUsercode() + "]已经存在！");
			}
			/*
			 * 设置固定值
			 */
			sysuser.setPkSysUser(UUID.randomUUID().toString());
			sysuser.setDr("0");
			sysuser.setCreateuser(shiroUser.pkSysUser);
			sysuser.setPwd("1234");
			sysUserMapper.insert(sysuser);
			return JsonResult.success();
		} catch (Exception ex) {
			logger.error("新增失败！", ex);
			return JsonResult.error("新增失败！");
		}
	}

	/**
	 * 业务逻辑：1.取到PK对应的用户对象 2.组装对应界面的VO（这里主要处理是机构名称<后续需要从缓存中取到相关数据，目前从数据库中取值>）
	 */
	@Override
	public JsonResult getSysUserByPrimaryKey(String pk_sys_user) {
		JsonResult result;
		try {
			SysUser entity = sysUserMapper.selectByPrimaryKey(pk_sys_user);
			if (entity != null) {
				SysUserVO vo = BeanMapper.map(entity, SysUserVO.class);
				SysOrg sysorg = sysOrgMapper.selectByPrimaryKey(entity.getPkSysOrg());
				if (sysorg != null) {
					vo.setOrgcode(sysorg.getOrgcode());
					vo.setOrgname(sysorg.getOrgname());
				} else {
					logger.debug("此用户：" + pk_sys_user + "，没有配置机构");
				}
				return result = JsonResult.success(vo);
			}
			return JsonResult.error("未获取到相关数据,主键：'" + pk_sys_user + "'");
		} catch (Exception ex) {
			String message = "获取数据异常";
			logger.error(message, ex);
			result = JsonResult.error(message);
		}
		return result;
	}

	/**
	 * 业务逻辑：1.对修改后的编码做校验，是否已经存在 2.增加数据
	 */
	@Override
	public JsonResult modifySysUser(SysUser entity) {
		try {
			SysUser entityFromDB = sysUserMapper.selectByPrimaryKey(entity.getPkSysUser());
			if (entityFromDB == null) {
				return JsonResult.error("ID=" + entity.getUsercode() + "不存在！");
			}
			if (!StringUtils.isEmpty(entity.getUsercode())) {
				if (!StringUtils.equals(entity.getUsercode(), entityFromDB.getUsercode())) {
					if(sysUserMapper.selectByUserCode(entity.getUsercode())!=null){
						return JsonResult.error("用户编号[" + entity.getUsercode() + "]已经存在！");
					}
					
				}
			}
			sysUserMapper.updateByPrimaryKey(entity);
			return JsonResult.success();
		} catch (Exception ex) {
			logger.error("修改失败！", ex);
			return JsonResult.error("修改失败！");
		}
	}

	/**
	 * 业务逻辑：目前无业务关联，能直接删除，后续需要调整为注销操作
	 */
	@Override
	public JsonResult removeSysUser(String pk_sys_user) {
		try {
			SysUser entityFromDB = sysUserMapper.selectByPrimaryKey(pk_sys_user);
			if (entityFromDB == null) {
				return JsonResult.error("ID=" + pk_sys_user + "不存在！");
			}

			/*
			 * 系统内置对象不能删除，如果删除系统将无法正常进行
			 */
			if (entityFromDB.getUsername().toLowerCase().equals("admin")
					|| entityFromDB.getUsername().toLowerCase().equals("superadmin")) {
				return JsonResult.error("管理员不允许删除！");
			}

			sysUserMapper.deleteByPrimaryKey(pk_sys_user);
			return JsonResult.success();
		} catch (Exception ex) {
			logger.error("删除失败！", ex);
			return JsonResult.error("删除失败！");
		}
	}

	/**
	 * 重置用户的密码
	 * 业务逻辑：1.对传入的用户做校验是否存在此用户
	 * 		  2.更新用户密码
	 * entity：其中parma1用户主键  parma2需要更新的密码
	 */
	@Override
	public JsonResult modifySysUserPwd(RequestParamVO entity) {
		try {
			SysUser entityFromDB =  sysUserMapper.selectByPrimaryKey(entity.Param1);
			if (entityFromDB == null) {
				return JsonResult.error("用户不存在！");
			}

			entityFromDB.setPwd(entity.Param2);

			sysUserMapper.updateByPrimaryKey(entityFromDB);

			return JsonResult.success();
		} catch (Exception ex) {
			logger.error("修改失败！", ex);
			return JsonResult.error("修改失败！");
		}
	}

	@Override
	public ModelMap modifyUserRole(String pkSysUser,ModelMap model) {
		
		List list= new ArrayList();
		SysUser entityFromDB = sysUserMapper.selectByPrimaryKey(pkSysUser);
		//已经添加的角色列表
		List<SysUserRoleVO> ListVO = sysUserRoleMapper.selectByPkSysUserAllRoles(pkSysUser);
		model.addAttribute("userid", entityFromDB.getPkSysUser());
		model.addAttribute("username", entityFromDB.getUsername());
		model.addAttribute("list", ListVO);
		return model;
		
	}

}
