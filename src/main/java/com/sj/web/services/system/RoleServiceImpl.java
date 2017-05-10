package com.sj.web.services.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.web.dao.system.SysRoleMapper;
import com.sj.web.model.system.SysRole;

/**
 * 
 * @Description: 角色先关业务实现
 * @author tody
 * @date 2017年5月9日下午2:49:40
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private SysRoleMapper sysroledao;

	/**
	 * 
	 */
	@Override
	public List<SysRole> getRolesByUserPk(String pkSysUser) {
		return sysroledao.selectByPkSysUser(pkSysUser);
	}


}
