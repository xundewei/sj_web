package com.sj.web.services.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.model.bean.system.SysUser;
import com.sj.web.model.dao.system.SysUserMapper;



/**
 * 用户基本信息业务类
 */
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByLogin(String usercode,String pwd) {
        SysUser user = sysUserMapper.selectByUsercodePwd(usercode, pwd);
        return user;
    }

	@Override
	public Map<String, Object> getAllSysUsers(String searchFilter) {
		Page<SysUser> page = new Page<SysUser>();
		List<SysUser> list=sysUserMapper.selectAllByPage(page);
		HashMap hm=new HashMap<>();
		hm.put("total", list.size());
		hm.put("rows", list);
		return hm;
	}

   
}
