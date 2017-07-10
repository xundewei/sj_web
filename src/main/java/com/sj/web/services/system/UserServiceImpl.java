package com.sj.web.services.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.core.utils.web.JsonResult;
import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.model.bean.system.SysUser;
import com.sj.web.model.dao.system.SysUserMapper;

/**
 * 用户基本信息业务类
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	protected static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;

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
	public JsonResult add(SysUser sysuser,ShiroUser shiroUser) {
		try {
			SysUser sysuser1 = sysUserMapper.selectByUserCode(sysuser.getUsercode());
			if (sysuser1 != null) {
				return JsonResult.error("用户编号[" + sysuser.getUsercode() + "]已经存在！");
			}
			sysuser.setPkSysUser(UUID.randomUUID().toString());
			sysuser.setDr("0");
			sysuser.setCreateuser(shiroUser.pkSysUser);
			sysUserMapper.insert(sysuser);
			return JsonResult.success();
		} catch (Exception ex) {
			logger.error("新增失败！", ex);
			return JsonResult.error("新增失败！");
		}
	}

}
