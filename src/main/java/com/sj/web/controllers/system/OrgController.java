package com.sj.web.controllers.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sj.core.utils.BeanMapper;
import com.sj.core.utils.web.easyui.EzPageResult;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.system.OrgTreeGridVO;
import com.sj.web.model.system.SysOrg;
import com.sj.web.services.system.OrgService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * @Description: 组织机构控制器
 * @author tody
 * @date 2017年5月4日下午8:46:27
 */
@Controller
@RequestMapping("/system/org")
public class OrgController extends BaseController {
	// @Autowired
	// private AppRoleService appRoleService;
	//
	@Autowired
	private OrgService service;
	//
	// @Autowired
	// private UserService userService;

	/**
	 * 1.默认的Action，返回功能主页面 URL: /system/org GET
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/org/index";
	}

	/**
	 * 2.获取Grid数据的接口 URL: /system/orga/list GET
	 */
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order) {
		
		// 只显示本人所在机构一下的组织机构（之后优化）
		try {
			List<SysOrg> list = service.getAllOrg();
			List<OrgTreeGridVO> voList = convertToVOList(list);
			return EzPageResult.build(voList.size(), voList);
		} catch (Exception ex) {
			logger.error("获取菜单数据失败！", ex);
			return EzPageResult.build(0, new ArrayList<SysOrg>());
		}
	}

	private List<OrgTreeGridVO> convertToVOList(List<SysOrg> content) {
		List<OrgTreeGridVO> list = new ArrayList<OrgTreeGridVO>();
		for (SysOrg org : content) {
			OrgTreeGridVO vo = BeanMapper.map(org, OrgTreeGridVO.class);
			// EasyUI的TreeGrid需要一个_parentId的隐藏字段，来确认每个项的父ID
			vo._parentId = org.getParentcode();

			list.add(vo);
		}
		return list;
	}

//	/**
//	 * 3.获取指定ID对象的接口 URL: /system/orga/1 GET
//	 *
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("{id}")
//	@ResponseBody
//	public JsonResult get(@PathVariable("id") String id) {
//		JsonResult result;
//		try {
//			Sys_Orga entity = service.get(id);
//
//			OrgaVO vo = BeanMapper.map(entity, OrgaVO.class);
//
//			if (!StringUtils.isEmpty(entity.getParentid())) {
//				Sys_Orga parentEntity = service.get(entity.getParentid());
//				vo.parentname = parentEntity.getName();
//			}
//
//			result = JsonResult.success(vo);
//		} catch (Exception ex) {
//			String message = "获取数据失败";
//			logger.error(message, ex);
//			result = JsonResult.error(message);
//		}
//
//		return result;
//	}
//
//	/**
//	 * 4.新增接口 URL：/system/orga/add POST
//	 * 请求的Body中包含一个City的json数据(或者对应的视图对象，收到后再进行转换)
//	 *
//	 * @param entity
//	 * @return
//	 */
//	@RequestMapping(value = "add", method = RequestMethod.POST)
//	@ResponseBody
//	public JsonResult add(@RequestBody Sys_Orga entity) {
//		try {
//			if (service.existName(entity.getName())) {
//				return JsonResult.error("机构名称[" + entity.getName() + "]已经存在！");
//			}
//
//			// 以随机数作为ID
//			entity.setId(UUID.randomUUID().toString());
//
//			ShiroRealm.ShiroUser shiroUser = getCurrentUser();
//			String groupid = shiroUser.groupid;
//			entity.setGroupid(groupid);
//
//			service.add(entity);
//
//			// 更新系统权限缓存
//			appRoleService.evictAllAppRole();
//
//			return JsonResult.success();
//		} catch (Exception ex) {
//			// 写日志
//			// 做异常处理
//			// 返回错误信息到前台
//			logger.error("新增失败！", ex);
//			return JsonResult.error("新增失败！");
//		}
//	}
//
//	/**
//	 * 5.更新/修改接口 URL：/system/orga/update POST 请求的Body中包含一个City的json数据
//	 *
//	 * @param entity
//	 * @return
//	 */
//	@RequestMapping(value = "update", method = RequestMethod.POST)
//	@ResponseBody
//	public JsonResult update(@RequestBody Sys_Orga entity) {
//		try {
//			Sys_Orga entityFromDB = service.get(entity.getId());
//			if (entityFromDB == null) {
//				return JsonResult.error("ID=" + entity.getId() + "不存在！");
//			}
//
//			if (!StringUtils.isEmpty(entity.getName())) {
//				if (!StringUtils.equals(entity.getName(), entityFromDB.getName())) {
//					if (service.existName(entity.getName())) {
//						return JsonResult.error("机构名称[" + entity.getName() + "]已经存在！");
//					}
//				}
//			}
//
//			if (StringUtils.isEmpty(entity.getGroupid())) {
//				entity.setGroupid(entityFromDB.getGroupid());
//			}
//
//			service.update(entity);
//
//			// 更新系统权限缓存
//			appRoleService.evictAllAppRole();
//
//			return JsonResult.success();
//		} catch (Exception ex) {
//			// 写日志
//			// 做异常处理
//			// 返回错误信息到前台
//			logger.error("修改失败！", ex);
//			return JsonResult.error("修改失败！");
//		}
//	}
//
//	/**
//	 * 6.删除接口 URL：/system/orga/delete/1 POST 请求的Body中无数据，仅在url中包含需要删除的id
//	 *
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
//	@ResponseBody
//	public JsonResult delete(@PathVariable("id") String id) {
//		try {
//			Sys_Orga entityFromDB = service.get(id);
//			if (entityFromDB == null) {
//				return JsonResult.error("ID=" + id + "不存在！");
//			}
//
//			// 删除前，必须进行校验，当前部门下面是否存在用户
//			boolean isExistUser = userService.existByOrgaId(id);
//			if (isExistUser) {
//				return JsonResult.error("当前机构下面存在用户，请先转移用户到其他机构后再进行删除！");
//			}
//
//			service.delete(id);
//
//			// 更新系统权限缓存
//			appRoleService.evictAllAppRole();
//
//			return JsonResult.success();
//		} catch (Exception ex) {
//			// 写日志
//			// 做异常处理
//			// 返回错误信息到前台
//			logger.error("删除失败！", ex);
//			return JsonResult.error("删除失败！");
//		}
//	}
}
