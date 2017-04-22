package com.sj.web.services.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.web.dao.system.SysUserMapper;
import com.sj.web.model.system.SysUser;



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

   
}
