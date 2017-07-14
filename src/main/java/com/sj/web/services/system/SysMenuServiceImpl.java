package com.sj.web.services.system;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj.core.utils.BeanMapper;
import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.core.utils.web.easyui.TreeBuilder;
import com.sj.web.model.bean.system.SysMenu;
import com.sj.web.model.dao.system.SysMenuMapper;
import com.sj.web.model.vo.system.MenuTreeGridVO;

/**
 * 菜单相关服务实现
 * 
 * @author TODY 2017年4月24日14:52:26
 */
@Service("sysMenuService")
@Transactional
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;


	@Override
	public List<MenuTreeGridVO> getAll() {
		List<SysMenu> sysmenu = sysMenuMapper.selectAll();
		List<MenuTreeGridVO> voList = convertToVOList(sysmenu);
		return voList;
	}

	
	@Override
	public String addSysMenu(SysMenu sysmenu) {
		//判断编码是否已经存在
		SysMenu sysmenu1 = sysMenuMapper.selectByMenuCode(sysmenu.getMenucode());
		if(!(sysmenu1==null)){
			return sysmenu.getMenucode();
		}else{
			// 以随机数作为ID
			sysmenu.setPkSysMenu(UUID.randomUUID().toString());
			try {
				sysMenuMapper.insert(sysmenu);
			} catch (Exception ex) {
				return "失败";
			}
			return "成功";
		}
	}


	@Override
	public List<SysMenu> getMenuAccordion(String pk_sys_user) {
		List<SysMenu> sysmenu = sysMenuMapper.selectMenuAccordion(pk_sys_user);
		return sysmenu;
	}


	@Override
	public List<EzTreeNode> getMenuLeftTree(String pk_sys_user, String menucode) {
		List<EzTreeNode> list = new ArrayList<EzTreeNode>();
		List<SysMenu> sysmenulist = sysMenuMapper.selectMenuLeftTree(pk_sys_user);
		//处理sysMenu 把sysMenu 变成 easyui 对应 的EzTreeNode 对象
		for (SysMenu menu : sysmenulist) {
			EzTreeNode ezTreeNode = new EzTreeNode();
			ezTreeNode.setId(menu.getMenucode());
			ezTreeNode.setPid(menu.getParentcode());
			ezTreeNode.setIconCls(menu.getIconcls());
			ezTreeNode.setText(menu.getDisplayname());
			ezTreeNode.setAttributes("{\"url\":\"" + menu.getUrl() + "\", \"key\":\"" + menu.getPkSysMenu() + "\"}");
			list.add(ezTreeNode);
		}
		List<EzTreeNode> lsit2 = TreeBuilder.buildByRecursive(list, menucode);
		return lsit2;
	}

	@Override
	public MenuTreeGridVO getByPrimaryKey(String pkSysMenu) {
		SysMenu sysmenu = sysMenuMapper.selectByPrimaryKey(pkSysMenu);
		MenuTreeGridVO menutreegridvo = convertToVO(sysmenu);
		return menutreegridvo;
	}

	@Override
	public int updateByPrimaryKey(SysMenu record) {
		int i = sysMenuMapper.updateByPrimaryKey(record);
		return i;
	}

	@Override
	public int deleteByPrimaryKey(String pkSysMenu) {
		int i = sysMenuMapper.deleteByPrimaryKey(pkSysMenu);
		return i;
	}

	
	
	
	/**
	 * 
	* @Title: convertToVOList
	* @Description: 处理_parentId字段  LIST<BEAN>-->LIST<VO>
	* 				EasyUI的TreeGrid需要一个_parentId的隐藏字段，来确认每个项的父ID
	* @param content
	* @return
	* @throws
	 */
	private List<MenuTreeGridVO> convertToVOList(List<SysMenu> content) {
		List<MenuTreeGridVO> list = new ArrayList<MenuTreeGridVO>();
		for (SysMenu org : content) {
			MenuTreeGridVO vo = BeanMapper.map(org, MenuTreeGridVO.class);
			vo._parentId = org.getParentcode();
			list.add(vo);
		}
		return list;
	}
	
	
	
	/**
	 * 
	* @Title: convertToVO
	* @Description: 处理_parentId字段  ,BEAN-->VO,
	* 				EasyUI的TreeGrid需要一个_parentId的隐藏字段，来确认每个项的父ID
	* @param content
	* @return
	* @throws
	 */
	private MenuTreeGridVO convertToVO(SysMenu content) {
		MenuTreeGridVO vo = BeanMapper.map(content, MenuTreeGridVO.class);
		vo._parentId = content.getParentcode();
		return vo;
	}


	/**
	 * 登入--权限使用
	 * 
	 */
	@Override
	public List<SysMenu> getMenuByPkSysUser(String PkSysUser) {
		return sysMenuMapper.selectMenuByPkSysUser(PkSysUser);
	}



}
