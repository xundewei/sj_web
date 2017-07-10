package com.sj.web.services.system;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.core.utils.BeanMapper;
import com.sj.core.utils.web.easyui.EzTreeNode;
import com.sj.core.utils.web.easyui.TreeBuilder;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.model.bean.system.SysOrg;
import com.sj.web.model.dao.system.SysOrgMapper;
import com.sj.web.model.vo.system.OrgTreeGridVO;

/**
 * 
 * @Description: 机构业务处理
 * @author tody
 * @date 2017年5月4日下午8:56:32
 */
@Service("orgService")
public class OrgServiceImpl implements OrgService {
	@Autowired
	private SysOrgMapper sysOrgMapper;

	@Override
	public List<OrgTreeGridVO> getAllOrg() {
		List<SysOrg> list = sysOrgMapper.selectAll();
		// 处理 bean -> vo
		List<OrgTreeGridVO> voList = convertToVOList(list);
		return voList;
	}

	@Override
	public OrgTreeGridVO getByPrimaryKey(String pk_sys_org) {
		SysOrg sysorg = sysOrgMapper.selectByPrimaryKey(pk_sys_org);
		OrgTreeGridVO vo = convertToVO(sysorg);
		return vo;
	}

	@Override
	public String addSysOrg(SysOrg sysorg, ShiroUser shiroUser) {
		if (sysOrgMapper.selectByOrgcode(sysorg.getOrgcode()).size() > 0) {
			return sysorg.getOrgname();
		} else {
			// 以随机数作为ID
			sysorg.setPkSysOrg(UUID.randomUUID().toString());
			sysorg.setCreateuser(shiroUser.pkSysUser);
			try {
				sysOrgMapper.insert(sysorg);
			} catch (Exception ex) {
				return "失败";
			}
			return "成功";
		}
	}
	
	
	@Override
	public int modifySysOrg(SysOrg sysorg) {
		int i = sysOrgMapper.updateByPrimaryKey(sysorg);
		return i;
	}
	
	
	
	@Override
	public String removeSysOrg(String pk_sys_org,String orgcode) {
		SysOrg entityFromDB = sysOrgMapper.selectByPrimaryKey(pk_sys_org);
		List<SysOrg> list = sysOrgMapper.selectEndOrg(orgcode);
		if (entityFromDB == null) {
			return "ID=" + orgcode + "不存在！";
		}else if (list.size()>0) {
			return "此机构不是末级机构，请先删除此机构一下的机构";
		}else{
			try {
				sysOrgMapper.deleteByPrimaryKey(pk_sys_org);
			} catch (Exception e) {
				return "失败";
			}
			return "成功";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public List<SysOrg> getByOrgcode(String orgcode) {
		List<SysOrg> list = sysOrgMapper.selectByOrgcode(orgcode);
		return list;
	}

	@Override
	public List<EzTreeNode> getAllOrgTree() {
		List<EzTreeNode> list = new ArrayList<EzTreeNode>();
		List<SysOrg> sysorglist = sysOrgMapper.selectAll();
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
		List<SysOrg> lsit = sysOrgMapper.selectEndOrg(orgcode);
		return lsit;
	}





	/**
	 * 
	 * @Title: convertToVOList @Description: 处理_parentId字段，转换VO
	 * LIST<BEAN>-->LIST<VO> @param content @return @throws
	 */
	private List<OrgTreeGridVO> convertToVOList(List<SysOrg> content) {
		List<OrgTreeGridVO> list = new ArrayList<OrgTreeGridVO>();
		for (SysOrg org : content) {
			OrgTreeGridVO vo = BeanMapper.map(org, OrgTreeGridVO.class);
			vo._parentId = org.getParentcode();

			list.add(vo);
		}
		return list;
	}

	/**
	 * 
	 * @Title: convertToVO @Description: 处理_parentId字段 BEAN-->VO @param
	 * content @return @throws
	 */
	private OrgTreeGridVO convertToVO(SysOrg content) {
		OrgTreeGridVO vo = BeanMapper.map(content, OrgTreeGridVO.class);
		vo._parentId = content.getParentcode();
		return vo;
	}

}
