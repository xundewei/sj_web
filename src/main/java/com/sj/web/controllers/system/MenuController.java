package com.sj.web.controllers.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.core.utils.web.JsonResult;
import com.sj.core.utils.web.easyui.EzPageResult;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.bean.system.SysMenu;
import com.sj.web.model.vo.system.MenuTreeGridVO;
import com.sj.web.services.system.SysMenuService;


/**
 * 
* @ClassName: MenuController
* @Description: 菜单控制器<br>
* {
* 	1.默认的Action，返回菜单功能主页面 URL: /system/menu GET <br>
* 	2.获取Grid数据的接口 URL: /system/menu/list GET <br>
* 	3.获取指定ID对象的接口 URL: /system/menu/1 GET 
*  }
* @author TODY happyming886@126.com
* @date 2017年7月8日 下午3:38:06
*
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {


	@Autowired
	private SysMenuService sysMenuService;
	

	/**
	 * 
	* @Title: index
	* @Description: 1.默认的Action，返回菜单功能主页面 URL: /system/menu GET
	* @return
	* @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/menu/index";
	}


	/**
	 * 
	* @Title: list
	* @Description: 2.获取Grid数据的接口 URL: /system/menu/list GET
	* @param sort
	* @param order
	* @return
	* @throws
	 */
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order) {
		try {
			List<MenuTreeGridVO> voList = sysMenuService.getAll();
			return EzPageResult.build(voList.size(), voList);
		} catch (Exception ex) {
			logger.error("获取菜单数据失败！", ex);
			return EzPageResult.build(0, new ArrayList<MenuTreeGridVO>());
		}
	}
	
	
	/**
	 * 
	* @Title: list
	* @Description: 2.获取Grid数据的接口 URL: /system/menu/listrolemenu GET
	* @param sort
	* @param order
	* @return
	* @throws
	 */
	@RequestMapping("listrolemenu")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(value = "roleid", required = false) String roleid) {
		try {
			List<MenuTreeGridVO> voList = sysMenuService.getAll();
			return EzPageResult.build(voList.size(), voList);
		} catch (Exception ex) {
			logger.error("获取菜单数据失败！", ex);
			return EzPageResult.build(0, new ArrayList<MenuTreeGridVO>());
		}
	}

	
	
	/**
	 * 
	* @Title: get
	* @Description: 3.获取指定ID对象的接口 URL: /system/menu/1 GET
	* @param id 菜单主键
	* @return
	* @throws
	 */
	@RequestMapping("{id}")
	@ResponseBody
	public JsonResult get(@PathVariable("id") String id) {
		JsonResult result;
		try {
			result = JsonResult.success(sysMenuService.getByPrimaryKey(id));
		} catch (Exception ex) {
			String message = "获取数据失败";
			logger.error(message, ex);
			result = JsonResult.error(message);
		}
		return result;
	}

	
	
	

	/**
	 * 
	* @Title: add
	* @Description: 4.新增接口 URL：/system/menu/add POST
	* 				     请求的Body中包含一个City的json数据(或者对应的视图对象，收到后再进行转换)
	* @param entity 菜单对象
	* @return
	* @throws
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(@RequestBody SysMenu entity) {
		String msg=sysMenuService.addSysMenu(entity);
		if(msg.equals("成功")){
			return JsonResult.success();
		}else if(msg.equals("失败")){
			return JsonResult.error("新增失败！");
		}else{
			return JsonResult.error("菜单编码[" + entity.getMenucode() +"]已经被使用，请修改！");	
		}
	}
	
	
	


	
	
	

	
	
	//
	// /**
	// * 5.更新/修改接口
	// * URL：/system/menu/update POST
	// * 请求的Body中包含一个City的json数据
	// * @param entity
	// * @return
	// */
	// @RequestMapping(value = "update", method = RequestMethod.POST)
	// @ResponseBody
	// public JsonResult update(@RequestBody Sys_Menu entity) {
	// try {
	// Sys_Menu entityFromDB = service.get(entity.getId());
	// if (entityFromDB == null) {
	// return JsonResult.error("ID=" + entity.getId() + "不存在！");
	// }
	//
	// if(StringUtils.isEmpty(entity.getMenukey())){
	// return JsonResult.error("菜单标识不能为空！");
	// }
	//
	// if(!StringUtils.isEmpty(entity.getMenukey())){
	// if(!StringUtils.equals(entity.getMenukey(), entityFromDB.getMenukey())){
	// if(service.existByMenukey(entity.getMenukey()))
	// return JsonResult.error("菜单标识[" + entity.getMenukey() + "]已经被使用，请修改！");
	// }
	// }
	//
	// service.update(entity);
	//
	// //更新系统权限缓存
	// appRoleService.evictAllAppRole();
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
	// /**
	// * 6.删除接口
	// * URL：/system/menu/delete/1 POST
	// * 请求的Body中无数据，仅在url中包含需要删除的id
	// * @param id
	// * @return
	// */
	// @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	// @ResponseBody
	// public JsonResult delete(@PathVariable("id") String id) {
	// try {
	// Sys_Menu entityFromDB = service.get(id);
	// if (entityFromDB == null) {
	// return JsonResult.error("ID=" + id + "不存在！");
	// }
	//
	// service.delete(id);
	//
	// //更新系统权限缓存
	// appRoleService.evictAllAppRole();
	//
	// return JsonResult.success();
	// } catch (Exception ex) {
	// //写日志
	// //做异常处理
	// //返回错误信息到前台
	// logger.error("删除失败！", ex);
	// return JsonResult.error("删除失败！");
	// }
	// }
}
