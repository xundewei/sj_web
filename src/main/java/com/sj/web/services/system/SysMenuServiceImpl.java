package com.sj.web.services.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.web.dao.system.SysMenuMapper;
import com.sj.web.model.system.SysMenu;



/**
 * 菜单相关服务实现
 * @author TODY
 * 2017年4月24日14:52:26
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
    private SysMenuMapper sysmenudao;

	/**
	 * 取到用户的一级菜单
	 */
    @Override
    public List<SysMenu> GetMenuBylev1(String pk_sys_user) {
    	List<SysMenu> sysmenu = sysmenudao.selectBylev1(pk_sys_user);
        return sysmenu;
    }

   
}
