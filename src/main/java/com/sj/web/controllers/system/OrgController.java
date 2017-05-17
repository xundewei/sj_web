package com.sj.web.controllers.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.core.utils.BeanMapper;
import com.sj.core.utils.web.JsonResult;
import com.sj.core.utils.web.easyui.EzPageResult;
import com.sj.web.common.security.ShiroUser;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.system.OrgTreeGridVO;
import com.sj.web.model.system.SysOrg;
import com.sj.web.services.system.OrgService;

/**
 * BUG:本级以下的机构修改的时候不显示
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

	/**
	 * 处理_parentId字段，转换VO LIST<BEAN>-->LIST<VO>
	 * 
	 * @param content
	 * @return
	 */
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

	/**
	 * 处理_parentId字段，转换VO BEAN-->VO
	 * 
	 * @param content
	 * @return
	 */
	private OrgTreeGridVO convertToVO(SysOrg content) {

		OrgTreeGridVO vo = BeanMapper.map(content, OrgTreeGridVO.class);
		// EasyUI的TreeGrid需要一个_parentId的隐藏字段，来确认每个项的父ID
		vo._parentId = content.getParentcode();
		return vo;
	}

	/**
	 * 3.获取指定ID对象的接口 URL: /system/orga/1 GET
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("{pk_sys_org}")
	@ResponseBody
	public JsonResult get(@PathVariable("pk_sys_org") String pk_sys_org) {
		JsonResult result;
		try {
			SysOrg entity = service.selectByPrimaryKey(pk_sys_org);
			OrgTreeGridVO vo = convertToVO(entity);
			// if (!StringUtils.isEmpty(entity.getParentcode())) {
			// SysOrg parentEntity = service.get(entity.getParentid());
			// vo.parentname = parentEntity.getName();
			// }
			result = JsonResult.success(vo);
		} catch (Exception ex) {
			String message = "获取数据失败";
			logger.error(message, ex);
			result = JsonResult.error(message);
		}

		return result;
	}

	/**
	 * 4.新增接口 URL：/system/orga/add POST
	 * 请求的Body中包含一个City的json数据(或者对应的视图对象，收到后再进行转换)
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(@RequestBody SysOrg entity) {
		try {
			// 判断是否存在机构
			if (service.getByOrgcode(entity.getOrgcode()).size() > 0) {
				return JsonResult.error("机构名称[" + entity.getOrgname() + "]已经存在！");
			}

			// 以随机数作为ID
			entity.setPkSysOrg(UUID.randomUUID().toString());

			ShiroUser shiroUser = getCurrentUser();
			entity.setCreateuser(shiroUser.pkSysUser);
			// String groupid = shiroUser.groupid;
			// entity.setGroupid(groupid);

			service.addSysOrg(entity);

			// 更新系统权限缓存
			// appRoleService.evictAllAppRole();

			return JsonResult.success();
		} catch (Exception ex) {
			// 写日志
			// 做异常处理
			// 返回错误信息到前台
			logger.error("新增失败！", ex);
			return JsonResult.error("新增失败！");
		}
	}

	/**
	 * 5.更新/修改接口 URL：/system/orga/update POST 请求的Body中包含一个City的json数据
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@RequestBody SysOrg entity) {
		try {
			//编码不可以修改直接更新
			service.updateSysOrg(entity);
			// 更新系统权限缓存
//			appRoleService.evictAllAppRole();
			return JsonResult.success();
		} catch (Exception ex) {
			// 写日志
			// 做异常处理
			// 返回错误信息到前台
			logger.error("修改失败！", ex);
			return JsonResult.error("修改失败！");
		}
	}

	/**
	 * 6.删除接口 URL：/system/orga/delete/1 POST 请求的Body中无数据，仅在url中包含需要删除的id
	 *  1.删除非末级机构不能删除，需要先删除末级机构
	 *  2.删除机构需要判断机构以下是否存在相关人员
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/id/{id}/code/{code}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable("id") String id,@PathVariable("code") String code) {
		try {
			SysOrg entityFromDB = service.selectByPrimaryKey(id);
			
			List<SysOrg> list = service.getByLastOrg(code);
			
			if (entityFromDB == null) {
				return JsonResult.error("ID=" + id + "不存在！");
			}
			
			if (list.size()>0) {
				return JsonResult.error("此机构不是末级机构，请先删除此机构一下的机构");
			}

//			// 删除前，必须进行校验，当前部门下面是否存在用户
//			boolean isExistUser = userService.existByOrgaId(id);
//			if (isExistUser) {
//				return JsonResult.error("当前机构下面存在用户，请先转移用户到其他机构后再进行删除！");
//			}

			service.deleteSysOrg(id);

			// 更新系统权限缓存
//			appRoleService.evictAllAppRole();

			return JsonResult.success();
		} catch (Exception ex) {
			// 写日志
			// 做异常处理
			// 返回错误信息到前台
			logger.error("删除失败！", ex);
			return JsonResult.error("删除失败！");
		}
	}

	/**
	 * 获取当前Shiro用户
	 * 
	 * @return
	 */
	protected ShiroUser getCurrentUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		// SecurityUtils.getSubject().isPermitted("admin");
		return user;
	}
}
