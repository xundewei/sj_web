package com.sj.web.services.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.web.dao.system.SysOrgMapper;
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

   
}
