package com.sj.web.controllers.system;


import java.util.ArrayList;
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
import com.sj.core.utils.web.easyui.EzPage;
import com.sj.core.utils.web.easyui.EzPageResult;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.bean.system.SysRole;
import com.sj.web.services.system.RoleService;

/**
 * 角色Controller
 */
@Controller
@RequestMapping("/system/roles")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;


    /**
     * 1.默认的Action，返回功能主页面
     * URL: /system/roles GET
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "system/role/index";
    }

    /**
     * 2.获取Grid数据的接口
     * URL: /system/roles/list GET
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(EzPage ezPage,@RequestParam(value = "name", required = false) String name) {
        try {
            return roleService.getAllSysRole();
        } catch (Exception ex) {
            logger.error("LoadData失败！", ex);
            return EzPageResult.build(0, new ArrayList());
        }
    }

    /**
     * 3.获取指定ID对象的接口
     * URL: /system/roles/1 GET
     *
     * @param id
     * @return
     */
    @RequestMapping("{id}")
    @ResponseBody
    public JsonResult get(@PathVariable("id") String id) {
       return roleService.getByPrimaryKey(id);
    }


    /**
     * 4.新增接口
     * URL：/system/roles/add POST
     * 请求的Body中包含一个Roles的json数据(或者对应的视图对象，收到后再进行转换)
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult add(@RequestBody SysRole entity) {
    	 return roleService.addSysRole(entity, getCurrentShiroUser());
    }

    /**
     * 5.更新/修改接口
     * URL：/system/roles/update POST
     * 请求的Body中包含一个Roles的json数据
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult update(@RequestBody SysRole entity) {
    	return roleService.modifySysRoleByPrimaryKey(entity);
    }

    /**
     * 6.删除接口
     * URL：/system/roles/delete/1 POST
     * 请求的Body中无数据，仅在url中包含需要删除的id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult delete(@PathVariable("id") String id) {
    	return roleService.removeByPrimaryKey(id);
    }

}
