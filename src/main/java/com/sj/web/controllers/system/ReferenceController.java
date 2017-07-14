package com.sj.web.controllers.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.web.services.system.OrgService;

/**
 * 
 * @Description: 用于常用参照的管理
 * @author tody
 * @date 2017年5月13日下午2:57:10
 */
@Controller
@RequestMapping("/system/reference")
public class ReferenceController {

	@Autowired
	private OrgService orgService;
	//
	// @Autowired
	// private MenuService menuService;
	//

	/**
	 * 组织机构树的单独页面，用于显示组织机构选择对话框
	 * 
	 * @return
	 */
	@RequestMapping(value = "showorgdialog")
	public String showOrgDialog(
			@RequestParam(value = "multi", required = false, defaultValue = "false") boolean multiSelect,
			ModelMap model) {
		model.addAttribute("multiSelect", multiSelect);
		return "reference/showorgdialog";
	}

	/**
	 * 获取组织机构Tree数据，用于显示一个组织机构树 用于公共的组织机构选择对话框
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadorgtree", method = RequestMethod.POST)
	@ResponseBody
	public List<EzTreeNode> loadOrgTree() {
		List<EzTreeNode> allList = orgService.getAllOrgTree();
		return allList;
	}
	//
	// /**
	// * 获取某个机构的下属机构，递归
	// * @param parentid
	// * @return
	// */
	// private List<EzTreeNode> getOrgaChildTreeNode(List<Sys_Orga> allList,
	// String parentid) {
	// List<Sys_Orga> list = getOrgaListByParentId(allList, parentid);
	//
	// List<EzTreeNode> treeList = new ArrayList<EzTreeNode>();
	// for(Sys_Orga orga:list){
	// EzTreeNode node = new EzTreeNode();
	// node.setId(orga.getId());
	// node.setText(orga.getName());
	//
	// List<EzTreeNode> child = getOrgaChildTreeNode(allList, orga.getId());
	// node.setChildren(child);
	//
	// treeList.add(node);
	// }
	//
	// return treeList;
	// }
	//
	// /**
	// * 在给定的组织机构List中查找某个id的下一层级子节点（只查找一层）
	// * @param allList
	// * @param parentid
	// * @return
	// */
	// private List<Sys_Orga> getOrgaListByParentId(List<Sys_Orga> allList,
	// String parentid){
	// List<Sys_Orga> list = new ArrayList<Sys_Orga>();
	// for(Sys_Orga orga: allList){
	// if(StringUtils.isEmpty(orga.getParentid()) &&
	// StringUtils.isEmpty(parentid)) {
	// list.add(orga);
	// }else if(StringUtils.equals(parentid, orga.getParentid()))
	// list.add(orga);
	// }
	//
	// return list;
	// }

	// /**
	// * 获取菜单Tree数据，用于显示一个菜单树
	// * @return
	// */
	// @RequestMapping(value = "loadmenutree", method = RequestMethod.POST)
	// @ResponseBody
	// public List<EzTreeNode> loadMenuTree() {
	//// List<Menu> allList = menuService.getList();
	// List<Sys_Menu> allList = menuService.getListBySort("displayorder",
	// "asc");
	//
	// return getMenuChildTreeNode(allList, "");
	// }
	//
	// /**
	// * 获取某个菜单的下属菜单，递归
	// * @param parentid
	// * @return
	// */
	// private List<EzTreeNode> getMenuChildTreeNode(List<Sys_Menu> allList,
	// String parentid) {
	// List<Sys_Menu> list = getMenuListByParentId(allList, parentid);
	//
	// List<EzTreeNode> treeList = new ArrayList<EzTreeNode>();
	// for(Sys_Menu menu :list){
	// EzTreeNode node = new EzTreeNode();
	// node.setId(menu.getId());
	// node.setText(menu.getName());
	//
	// List<EzTreeNode> child = getMenuChildTreeNode(allList, menu.getId());
	// node.setChildren(child);
	//
	// treeList.add(node);
	// }
	//
	// return treeList;
	// }
	//
	// /**
	// * 在给定的菜单List中查找某个id的下一层级子节点（只查找一层）
	// * @param allList
	// * @param parentid
	// * @return
	// */
	// private List<Sys_Menu> getMenuListByParentId(List<Sys_Menu> allList,
	// String parentid){
	// List<Sys_Menu> list = new ArrayList<Sys_Menu>();
	// for(Sys_Menu orga: allList){
	// if(StringUtils.equals(parentid, orga.getParentid()))
	// list.add(orga);
	// }
	//
	// return list;
	// }
	//
	// /**
	// * 菜单树的单独页面，用于显示菜单选择对话框
	// * @return
	// */
	// @RequestMapping(value = "showmenudialog")
	// public String showMenuDialog(@RequestParam(value = "multi", required =
	// false, defaultValue = "false") boolean multiSelect, ModelMap model){
	// model.addAttribute("multiSelect", multiSelect);
	// return "system/public/showmenudialog";
	// }
	//
	// /**
	// * 用于显示用户选择对话框
	// * @return
	// */
	// @RequestMapping(value = "showuserdialog")
	// public String showUserDialog(@RequestParam(value = "multi", required =
	// false, defaultValue = "false") boolean multiSelect, ModelMap model){
	// model.addAttribute("multiSelect", multiSelect);
	// return "system/public/showuserdialog";
	// }
	//
	

}
