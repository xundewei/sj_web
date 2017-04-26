package com.sj.web.services.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.core.utils.web.easyui.TreeBuilder;
import com.sj.web.dao.system.SysMenuMapper;
import com.sj.web.model.system.SysMenu;

/**
 * 菜单相关服务实现
 * 
 * @author TODY 2017年4月24日14:52:26
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
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

	/**
	 * 根据角色和父节点编码找到菜单下面所有的菜单
	 */
	@Override
	public List<EzTreeNode> GetMenuByMoreLev2(String pk_sys_user,String menucode) {
		List<EzTreeNode> list = new ArrayList<EzTreeNode>();
		List<SysMenu> sysmenulist = sysmenudao.GetMenuByMoreLev2(pk_sys_user);
		for (SysMenu menu : sysmenulist) {
			EzTreeNode ezTreeNode = new EzTreeNode();
			ezTreeNode.setId(menu.getMenucode());
			ezTreeNode.setPid(menu.getParentcode());
			ezTreeNode.setText(menu.getDisplayname());
			ezTreeNode.setAttributes("{\"url\":\"" + menu.getUrl() + "\", \"key\":\"" + menu.getPkSysMenu() + "\"}");
			list.add(ezTreeNode);
		}
		List<EzTreeNode> lsit2 = TreeBuilder.buildByRecursive(list,menucode);  
		return lsit2;
	}

}
