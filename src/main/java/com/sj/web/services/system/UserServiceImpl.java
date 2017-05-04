package com.sj.web.services.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.dao.system.SysUserMapper;
import com.sj.web.model.system.SysUser;
import com.sj.web.model.system.SysUserExample;
import com.sj.web.model.system.SysUserExample.Criteria;



/**
 * 用户基本信息业务类
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private SysUserMapper userdao;

    @Override
    public SysUser getByLogin(String usercode,String pwd) {
        SysUser user = userdao.selectByUsercode_Pwd(usercode, pwd);
        return user;
    }

	@Override
	public Map<String, Object> getAllSysUsers(String searchFilter) {
		SysUserExample sysuserexample=new SysUserExample();
		Criteria criteria=sysuserexample.createCriteria();
		Page<SysUser> page = new Page<SysUser>();
		int total=userdao.countByExample(sysuserexample);
		List<SysUser> list=userdao.selectAll(page);
		HashMap hm=new HashMap<>();
		hm.put("total", total);
		hm.put("rows", list);
		return hm;
	}

   
}
