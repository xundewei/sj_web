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
import com.sj.web.model.bean.system.SysOrg;
import com.sj.web.model.vo.system.OrgTreeGridVO;
import com.sj.web.services.system.OrgService;

/**
 * 
* @ClassName: OrgController
* @Description: 组织机构控制器
* 				{ <br>
* 					1.默认的Action，返回功能主页面 URL: /system/org GET<br>
* 					2.获取Grid数据的接口 URL: /system/org/list GET<br>
* 					3.获取指定ID对象的接口 URL: /system/org/1 GET<br>
* 					4.新增接口 URL：/system/org/add POST<br>
* 					5.更新/修改接口 URL：/system/org/update POST<br>
* 					6.删除接口 URL：/system/org/delete/1 POST <br>
* 				}
* @author TODY happyming886@126.com
* @date 2017年7月8日 上午11:58:17
*
 */
@Controller
@RequestMapping("/system/org")
public class OrgController extends BaseController {
	@Autowired
	private OrgService orgService;
	
	
	
	/**
	 * 
	* @Title: index
	* @Description: 1.默认的Action，返回功能主页面 URL: /system/org GET
	* @return
	* @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "system/org/index";
	}

	
	
	/**
	 * 
	* @Title: list
	* @Description: 2.获取Grid数据的接口 URL: /system/org/list GET
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
			List<OrgTreeGridVO> voList = orgService.getAllOrg();
			return EzPageResult.build(voList.size(), voList);
		} catch (Exception ex) {
			logger.error("获取菜单数据失败！", ex);
			return EzPageResult.build(0, new ArrayList<SysOrg>());
		}
	}


	/**
	 * 
	* @Title: get
	* @Description: 3.获取指定ID对象的接口 URL: /system/org/pk_sys_org GET
	* @param pk_sys_org
	* @return
	* @throws
	 */
	@RequestMapping("{pk_sys_org}")
	@ResponseBody
	public JsonResult get(@PathVariable("pk_sys_org") String pk_sys_org) {
		JsonResult result;
		try {
			OrgTreeGridVO vo = orgService.getByPrimaryKey(pk_sys_org);
			result = JsonResult.success(vo);
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
	* @Description: 4.新增接口 URL：/system/orga/add POST
	* @param entity
	* @return
	* @throws
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(@RequestBody SysOrg entity) {
		String msg=orgService.addSysOrg(entity, getCurrentShiroUser());
		if(msg.equals("成功")){
			return JsonResult.success();
		}else if(msg.equals("失败")){
			return JsonResult.error("新增失败！");
		}else{
			return JsonResult.error("菜单编码[" + entity.getOrgcode() +"]已经被使用，请修改！");	
		}
	}

	/**
	 * 
	* @Title: update
	* @Description: 5.更新/修改接口 URL：/system/org/update POST 请求的Body中包含一个City的json数据
	* @param entity
	* @return
	* @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult update(@RequestBody SysOrg entity) {
		return orgService.modifySysOrg(entity,getCurrentShiroUser());
	}

	
	/**
	 * 
	* @Title: delete
	* @Description: * 6.删除接口 URL：/system/orga/delete/1 POST 请求的Body中无数据，仅在url中包含需要删除的id
	 *  					1.删除非末级机构不能删除，需要先删除末级机构
	 *  					2.删除机构需要判断机构以下是否存在相关人员
	* @param id
	* @param code
	* @return
	* @throws
	 */
	@RequestMapping(value = "delete/id/{id}/code/{code}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable("id") String id,@PathVariable("code") String code) {
		return orgService.removeSysOrg(id, code);
	}
}
