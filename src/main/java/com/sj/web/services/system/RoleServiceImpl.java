package com.sj.web.services.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.web.model.bean.system.SysRole;
import com.sj.web.model.dao.system.SysRoleMapper;

/**
 * 
 * @Description: 角色先关业务实现
 * @author tody
 * @date 2017年5月9日下午2:49:40
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private SysRoleMapper sysroledao;


//	@Override
//	public List<SysRole> getRolesByUserPk(String pkSysUser) {
//		return sysroledao.selectByPkSysUser(pkSysUser);
//	}

	@Override
	public int deleteByPrimaryKey(String pkSysRole) {
		int i = sysroledao.deleteByPrimaryKey(pkSysRole);
		return i;
	}

	@Override
	public int insert(SysRole record) {
		int i = sysroledao.insert(record);
		return i;
	}

	@Override
	public SysRole selectByPrimaryKey(String pkSysRole) {
		SysRole sysrole = sysroledao.selectByPrimaryKey(pkSysRole);
		return sysrole;
	}

	@Override
	public List<SysRole> selectAll() {
		List<SysRole> list = sysroledao.selectAll();
		return list;
	}

	@Override
	public int updateByPrimaryKey(SysRole record) {
		int i = sysroledao.updateByPrimaryKey(record);
		return i;
	}


}
