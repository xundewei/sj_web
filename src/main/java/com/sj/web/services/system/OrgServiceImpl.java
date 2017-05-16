package com.sj.web.services.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.core.utils.web.easyui.TreeBuilder;
import com.sj.web.dao.system.SysOrgMapper;
import com.sj.web.model.system.SysMenu;
import com.sj.web.model.system.SysOrg;


/**
 * 
 * @Description: 机构业务处理
 * @author tody
 * @date 2017年5月4日下午8:56:32
 */
@Service
public class OrgServiceImpl implements OrgService{
	@Autowired
    private SysOrgMapper orgdao;

	@Override
	public List<SysOrg> getAllOrg() {
		List<SysOrg> list = orgdao.selectAll();
		return list;
	}

	@Override
	public List<SysOrg> getByOrgcode(String orgcode) {
		List<SysOrg> list = orgdao.selectByOrgcdoe(orgcode);
		return list;
	}

	@Override
	public int addSysOrg(SysOrg sysorg) {
		int i = orgdao.insert(sysorg);
		return i;
	}

	@Override
	public SysOrg selectByPrimaryKey(String pk_sys_org) {
		SysOrg sysorg = orgdao.selectByPrimaryKey(pk_sys_org);
		return sysorg;
	}

	@Override
	public List<EzTreeNode> getAllOrgTree() {
		List<EzTreeNode> list = new ArrayList<EzTreeNode>();
		List<SysOrg> sysorglist = orgdao.selectAll();
		for (SysOrg sysorg : sysorglist) {
			EzTreeNode ezTreeNode = new EzTreeNode();
			ezTreeNode.setId(sysorg.getOrgcode());
			ezTreeNode.setPid(sysorg.getParentcode());
			ezTreeNode.setText(sysorg.getOrgname());
			list.add(ezTreeNode);
		}
		List<EzTreeNode> lsit2 = TreeBuilder.buildByRecursive(list);  
		return lsit2;
	}

	@Override
	public List<SysOrg> getByLastOrg(String orgcode) {
		List<SysOrg> lsit = orgdao.selectByLastOrg(orgcode);
		return lsit;
	}

	@Override
	public int updateSysOrg(SysOrg sysorg) {
		int i  = orgdao.updateByPrimaryKey(sysorg);
		return i;
	}

	@Override
	public int deleteSysOrg(String pk_sys_org) {
		int i = orgdao.deleteByPrimaryKey(pk_sys_org);
		return i;
	}

   
}
