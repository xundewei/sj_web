package com.sj.web.services.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.core.utils.web.JsonResult;
import com.sj.web.common.mybatis.plugin.Page;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.model.bean.system.SysRole;
import com.sj.web.model.dao.system.SysRoleMapper;

/**
 * 
 * @Description: 角色先关业务实现
 * @author tody
 * @date 2017年5月9日下午2:49:40
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	private static Logger logger =  LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	
	@Override
	public Map<String, Object> getAllSysRole() {
		Page<SysRole> page = new Page<SysRole>();
		List<SysRole> list = sysRoleMapper.selectAllByPage(page);
		HashMap hm = new HashMap<>();
		hm.put("total", list.size());
		hm.put("rows", list);
		return hm;
	}
	
	
	
	@Override
	public JsonResult getByPrimaryKey(String pkSysRole) {
		 JsonResult result;
	        try {
	            SysRole entity = sysRoleMapper.selectByPrimaryKey(pkSysRole);
	            result = JsonResult.success(entity);
	        } catch (Exception ex) {
	            String message = "获取数据失败";
	            logger.error(message, ex);
	            result = JsonResult.error(message);
	        }

	        return result;
	}
	
	
	@Override
	public JsonResult addSysRole(SysRole entity ,ShiroUser shiroUser) {
		try {
			
            if (sysRoleMapper.selectByRoleCode(entity.getRolecode())!=null) {
                return JsonResult.error("角色名称[" + entity.getRolename() + "]已经存在！");
            }
            
            entity.setPkSysRole(UUID.randomUUID().toString());
            entity.setDr("0");
            entity.setCreateuser(shiroUser.pkSysUser);
            
            sysRoleMapper.insert(entity);
            return JsonResult.success();
        } catch (Exception ex) {
            logger.error("新增失败！", ex);
            return JsonResult.error("新增失败！");
        }
	}
	
	
	
	@Override
	public JsonResult modifySysRoleByPrimaryKey(SysRole entity) {
		 try {
	            SysRole entityFromDB = sysRoleMapper.selectByPrimaryKey(entity.getPkSysRole());
	            if (entityFromDB == null) {
	                return JsonResult.error("ID=" + entity.getRolecode() + "不存在！");
	            }
	            sysRoleMapper.updateByPrimaryKey(entity);
	            return JsonResult.success();
	        } catch (Exception ex) {
	            logger.error("修改失败！", ex);
	            return JsonResult.error("修改失败！");
	        }
	}
	
	
	
	
	@Override
	public JsonResult removeByPrimaryKey(String pkSysRole) {
		 try {
	            SysRole entityFromDB = sysRoleMapper.selectByPrimaryKey(pkSysRole);
	            if (entityFromDB == null) {
	                return JsonResult.error("ID=" + entityFromDB.getRolecode() + "不存在！");
	            }

	            //删除前，必须进行校验，是否有用户拥有此角色
	           
	            sysRoleMapper.deleteByPrimaryKey(pkSysRole);

	            return JsonResult.success();
	        } catch (Exception ex) {
	            logger.error("删除失败！", ex);
	            return JsonResult.error("删除失败！");
	        }
	}
	
	
	
	
	@Override
	public List<SysRole> getByPkSysUser(String pkSysUser) {
		return sysRoleMapper.selectByPkSysUser(pkSysUser);
	}


	

	@Override
	public List<SysRole> selectAll() {
		List<SysRole> list = sysRoleMapper.selectAll();
		return list;
	}





	

	


}
