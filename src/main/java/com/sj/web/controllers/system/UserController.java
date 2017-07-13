package com.sj.web.controllers.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.core.utils.web.JsonResult;
import com.sj.core.utils.web.easyui.EzPage;
import com.sj.core.utils.web.easyui.EzPageResult;
import com.sj.web.common.security.ShiroRealm;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.bean.system.SysUser;
import com.sj.web.model.vo.RequestParamVO;
import com.sj.web.services.system.UserService;

/**
 * 标准的数据Grid后台Controller 接口如下： 1.主页面 URL: /system/user GET 2.获取Grid数据的接口
 * /system/user/list GET 3.获取指定ID对象的接口 URL: /system/user/1 GET 4.新增接口
 * URL：/system/user/add POST 5.更新/修改接口 URL：/system/user/update POST 6.删除接口
 * URL：/system/user/delete/1 POST
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

	// @Autowired
	// private AppRoleService appRoleService;
	//
	@Autowired
	private UserService userService;
	//
	// @Autowired
	// private OrgaService orgaService;
	//
	// @Autowired
	// private UserRolesService userRolesService;
	//
	// @Autowired
	// private RolesService rolesService;
	//
	// @Autowired
	// private TestCacheService testCacheService;

	/**
	 * 1.默认的Action，返回功能主页面 URL: /system/user GET
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/user/index";
	}

	/**
	 * 2.获取Grid数据的接口 URL: /system/user/list GET
	 */
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(EzPage ezPage, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "orgaid", required = false) String orgaid,
			@RequestParam(value = "level", defaultValue = "1") String level) {

		try {
			Map map = userService.getAllSysUsers("");
			return EzPageResult.build(new Integer((int) map.get("total")), map.get("rows"));
		} catch (Exception ex) {
			logger.error("LoadData失败！", ex);
			return EzPageResult.build(0, new ArrayList<SysUser>());
		}
	}

	/**
	 * 3.获取指定ID对象的接口 URL: /system/user/1 GET
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("{id}")
	@ResponseBody
	public JsonResult get(@PathVariable("id") String id) {
		return userService.getSysUserByPrimaryKey(id);
	}

	/**
	 * 4.新增接口 URL：/system/user/add POST
	 * 请求的Body中包含一个User的json数据(或者对应的视图对象，收到后再进行转换)
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(@RequestBody SysUser entity) {
		return userService.add(entity, getCurrentShiroUser());
	}

	/**
	 * 5.更新/修改接口 URL：/system/user/update POST 请求的Body中包含一个User的json数据
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@RequestBody SysUser entity) {
		return userService.modifySysUser(entity);
	}

	/**
	 * 6.删除接口 URL：/system/user/delete/1 POST 请求的Body中无数据，仅在url中包含需要删除的id
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable("id") String id) {
		return userService.removeSysUser(id);
	}

	/**
	 * 7.重置密码 URL：/system/user/resetPwd POST 请求的Body中包含一个ID/PWD的json数据
	 *
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult resetPwd(@RequestBody RequestParamVO entity) {
		return userService.modifySysUserPwd(entity);
	}

	//
	// /**
	// * 8.修改密码
	// * URL：/system/user/update3 POST
	// * 请求的Body中包含一个PwdVO的json数据
	// *
	// * @param entity
	// * @return
	// */
	// @RequestMapping(value = "update3", method = RequestMethod.POST)
	// @ResponseBody
	// public JsonResult update3(@RequestBody PwdVO entity) {
	// try {
	// Sys_User entityFromDB = service.get(entity.p0);
	// if (entityFromDB == null) {
	// return JsonResult.error("用户不存在！");
	// }
	//
	// String oldPwd = Utils.getMD5(entity.p1).toUpperCase();
	// if (!StringUtils.equals(oldPwd, entityFromDB.getPwd())) {
	// return JsonResult.error("您输入的旧密码不正确！");
	// }
	//
	// String newPwd = Utils.getMD5(entity.p2).toUpperCase();
	// entityFromDB.setPwd(newPwd);
	//
	// service.update(entityFromDB);
	//
	// return JsonResult.success();
	// } catch (Exception ex) {
	// //写日志
	// //做异常处理
	// //返回错误信息到前台
	// logger.error("修改失败！", ex);
	// return JsonResult.error("修改失败！");
	// }
	// }
	//
	/**
	 * 显示分配用户角色对话框 URL: setroles/{id} GET
	 */
	@RequestMapping(value = "setroles/{id}", method = RequestMethod.GET)
	public String showRolesDialog(@PathVariable("id") String id, ModelMap model) {
		model = userService.modifyUserRole(id, model);
		return "system/user/setroles";
	}

}
