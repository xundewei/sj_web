package com.sj.web.controllers.system;


import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sj.core.utils.web.easyui.EzPage;
import com.sj.core.utils.web.easyui.EzPageResult;
import com.sj.web.controllers.BaseController;
import com.sj.web.model.system.SysUser;
import com.sj.web.services.system.UserService;

/**
 * 标准的数据Grid后台Controller
 * 接口如下：
 * 1.主页面    URL: /system/user GET
 * 2.获取Grid数据的接口    /system/user/list GET
 * 3.获取指定ID对象的接口    URL: /system/user/1 GET
 * 4.新增接口    URL：/system/user/add POST
 * 5.更新/修改接口    URL：/system/user/update POST
 * 6.删除接口 URL：/system/user/delete/1 POST
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

//    @Autowired
//    private AppRoleService appRoleService;
//
    @Autowired
    private UserService service;
//
//    @Autowired
//    private OrgaService orgaService;
//
//    @Autowired
//    private UserRolesService userRolesService;
//
//    @Autowired
//    private RolesService rolesService;
//
//    @Autowired
//    private TestCacheService testCacheService;

    /**
     * 1.默认的Action，返回功能主页面
     * URL: /system/user GET
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "system/user/index";
    }

    /**
     * 2.获取Grid数据的接口
     * URL: /system/user/list GET
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(EzPage ezPage,
                                    @RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "orgaid", required = false) String orgaid,
                                    @RequestParam(value = "level", defaultValue = "1") String level) {
//        //构建一个分页请求对象
//        Pageable pageRequest = ezPage.buildJPAPageRequest();
//
//        //构建条件过滤组(And)
//        SearchFilter group = SearchFilter.buildAndGroup();
//        if (!StringUtils.isEmpty(name))
//            group.addFilter("displayname", name, SearchConst.START_WITH);

//        //只显示本公司用户
//        ShiroRealm.ShiroUser shiroUser = getCurrentUser();
//        String groupid = shiroUser.groupid;
//        group.addFilter("groupid", groupid, SearchConst.EQ);

        try {
//            //获取分页结果
//            Page<Sys_User> pageResult;
//            if (StringUtils.equals(level, "1")) {
//                //只获取指定机构下面一层用户
//                if (!StringUtils.isEmpty(orgaid))
//                    group.addFilter("orgaid", orgaid, SearchConst.EQ);
//                pageResult = service.getListByPage(pageRequest, group);
//            } else {
//                //获取指定机构下面全部层级的用户
//                if (StringUtils.isEmpty(orgaid))
//                    orgaid = "";
//                List<Sys_Orga> allNode = orgaService.getAllChildById(orgaid);
//
//                //再构建一个或的过滤组，里面是所有层级的机构IDE
//                /*SearchFilter group2 = SearchFilter.buildOrGroup();
//                for(Orga orga: allNode){
//                    group2.addFilter("orgaid", orga.getId(), SearchConst.EQ);
//                }
//                group2.addFilter("orgaid", orgaid, SearchConst.EQ);
//
//                group.addChildFilterGroup(group2);*/
//                ArrayList ids = new ArrayList();
//                for (Sys_Orga orga : allNode) {
//                    ids.add(orga.getId());
//                }
//                ids.add(orgaid);
//                group.addFilter("orgaid", ids, SearchConst.IN);
//
//                pageResult = service.getListByPage(pageRequest, group);
//            }
        	Map map=service.getAllSysUsers("");
            return EzPageResult.build(new Integer((int) map.get("total")), map.get("rows"));
        } catch (Exception ex) {
            logger.error("LoadData失败！", ex);
            return EzPageResult.build(0, new ArrayList<SysUser>());
        }
    }
//
//    /**
//     * 3.获取指定ID对象的接口
//     * URL: /system/user/1 GET
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("{id}")
//    @ResponseBody
//    public JsonResult get(@PathVariable("id") String id) {
//        JsonResult result;
//        try {
//            Sys_User entity = service.get(id);
//            UserVO vo = BeanMapper.map(entity, UserVO.class);
//
//            //根节点下面的用户无父机构
//            if (!StringUtils.isEmpty(entity.getOrgaid())) {
//                Sys_Orga orga = orgaService.get(entity.getOrgaid());
//                vo.setOrganame(orga.getName());
//            }
//
//            result = JsonResult.success(vo);
//        } catch (Exception ex) {
//            String message = "获取数据失败";
//            logger.error(message, ex);
//            result = JsonResult.error(message);
//        }
//
//        return result;
//    }
//
//    /**
//     * 4.新增接口
//     * URL：/system/user/add POST
//     * 请求的Body中包含一个User的json数据(或者对应的视图对象，收到后再进行转换)
//     *
//     * @param entity
//     * @return
//     */
//    @RequestMapping(value = "add", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResult add(@RequestBody Sys_User entity) {
//        try {
//
//            if (service.existUserCode(entity.getUsercode())) {
//                return JsonResult.error("用户编号[" + entity.getUsercode() + "]已经存在！");
//            }
//
//            if (service.existLoginName(entity.getLoginname())) {
//                return JsonResult.error("用户登录名[" + entity.getLoginname() + "]已经存在！");
//            }
//
//            entity.setId(UUID.randomUUID().toString());
//            entity.setDeleteflag(false);
//
//            //设置为当前公司
//            ShiroRealm.ShiroUser shiroUser = getCurrentUser();
//            String groupid = shiroUser.groupid;
//            entity.setGroupid(groupid);
//
//            //默认密码1234
//            entity.setPwd(Utils.getMD5("1234").toUpperCase());
//
//            service.add(entity);
//
//            //更新系统权限缓存
//            appRoleService.evictAllAppRole();
//
//            return JsonResult.success();
//        } catch (Exception ex) {
//            //写日志
//            //做异常处理
//            //返回错误信息到前台
//            logger.error("新增失败！", ex);
//            return JsonResult.error("新增失败！");
//        }
//    }
//
//    /**
//     * 5.更新/修改接口
//     * URL：/system/user/update POST
//     * 请求的Body中包含一个User的json数据
//     *
//     * @param entity
//     * @return
//     */
//    @RequestMapping(value = "update", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResult update(@RequestBody Sys_User entity) {
//        try {
//            Sys_User entityFromDB = service.get(entity.getId());
//            if (entityFromDB == null) {
//                return JsonResult.error("ID=" + entity.getId() + "不存在！");
//            }
//
//            if(!StringUtils.isEmpty(entity.getUsercode())){
//                if(!StringUtils.equals(entity.getUsercode(), entityFromDB.getUsercode())){
//                    if (service.existUserCode(entity.getUsercode())) {
//                        return JsonResult.error("用户编号[" + entity.getUsercode() + "]已经存在！");
//                    }
//                }
//            }
//
//            if(!StringUtils.isEmpty(entity.getLoginname())){
//                if(!StringUtils.equals(entity.getLoginname(), entityFromDB.getLoginname())){
//                    if (service.existLoginName(entity.getLoginname())) {
//                        return JsonResult.error("用户登录名[" + entity.getLoginname() + "]已经存在！");
//                    }
//                }
//            }
//
//            if(StringUtils.isEmpty(entity.getGroupid())){
//                entity.setGroupid(entityFromDB.getGroupid());
//            }
//
//            service.update(entity);
//
//            //更新系统权限缓存
//            appRoleService.evictAllAppRole();
//
//            return JsonResult.success();
//        } catch (Exception ex) {
//            //写日志
//            //做异常处理
//            //返回错误信息到前台
//            logger.error("修改失败！", ex);
//            return JsonResult.error("修改失败！");
//        }
//    }
//
//    /**
//     * 6.删除接口
//     * URL：/system/user/delete/1 POST
//     * 请求的Body中无数据，仅在url中包含需要删除的id
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResult delete(@PathVariable("id") String id) {
//        try {
//            Sys_User entityFromDB = service.get(id);
//            if (entityFromDB == null) {
//                return JsonResult.error("ID=" + id + "不存在！");
//            }
//
//            if (entityFromDB.getLoginname().toLowerCase().equals("admin") || entityFromDB.getLoginname().toLowerCase().equals("superadmin")) {
//                return JsonResult.error("管理员不允许删除！");
//            }
//
//            service.delete(id);
//
//            //更新系统权限缓存
//            appRoleService.evictAllAppRole();
//
//            return JsonResult.success();
//        } catch (Exception ex) {
//            //写日志
//            //做异常处理
//            //返回错误信息到前台
//            logger.error("删除失败！", ex);
//            return JsonResult.error("删除失败！");
//        }
//    }
//
//    /**
//     * 7.重置密码
//     * URL：/system/user/update2 POST
//     * 请求的Body中包含一个ID/PWD的json数据
//     *
//     * @param entity
//     * @return
//     */
//    @RequestMapping(value = "update2", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResult update2(@RequestBody IdNameVO entity) {
//        try {
//            Sys_User entityFromDB = service.get(entity.id);
//            if (entityFromDB == null) {
//                return JsonResult.error("用户不存在！");
//            }
//
//            entityFromDB.setPwd(Utils.getMD5(entity.name).toUpperCase());
//
//            service.update(entityFromDB);
//
//            return JsonResult.success();
//        } catch (Exception ex) {
//            //写日志
//            //做异常处理
//            //返回错误信息到前台
//            logger.error("修改失败！", ex);
//            return JsonResult.error("修改失败！");
//        }
//    }
//
//    /**
//     * 8.修改密码
//     * URL：/system/user/update3 POST
//     * 请求的Body中包含一个PwdVO的json数据
//     *
//     * @param entity
//     * @return
//     */
//    @RequestMapping(value = "update3", method = RequestMethod.POST)
//    @ResponseBody
//    public JsonResult update3(@RequestBody PwdVO entity) {
//        try {
//            Sys_User entityFromDB = service.get(entity.p0);
//            if (entityFromDB == null) {
//                return JsonResult.error("用户不存在！");
//            }
//
//            String oldPwd = Utils.getMD5(entity.p1).toUpperCase();
//            if (!StringUtils.equals(oldPwd, entityFromDB.getPwd())) {
//                return JsonResult.error("您输入的旧密码不正确！");
//            }
//
//            String newPwd = Utils.getMD5(entity.p2).toUpperCase();
//            entityFromDB.setPwd(newPwd);
//
//            service.update(entityFromDB);
//
//            return JsonResult.success();
//        } catch (Exception ex) {
//            //写日志
//            //做异常处理
//            //返回错误信息到前台
//            logger.error("修改失败！", ex);
//            return JsonResult.error("修改失败！");
//        }
//    }
//
//    /**
//     * 显示分配用户角色对话框
//     * URL: setroles/{id} GET
//     */
//    @RequestMapping(value = "setroles/{id}", method = RequestMethod.GET)
//    public String showRolesDialog(@PathVariable("id") String id, ModelMap model) {
//        Sys_User entityFromDB = service.get(id);
//
//        List<Sys_Userroles> oldList = userRolesService.getListByUserId(id);
//        UserRolesVO vo = new UserRolesVO();
//        vo.userid = id;
//        vo.username = entityFromDB.getDisplayname();
//        vo.roles = new ArrayList<Sys_Roles>();
//        for (Sys_Userroles ur : oldList) {
//            Sys_Roles roles = rolesService.get(ur.getRoleid());
//            vo.roles.add(roles);
//        }
//
//        //List<Sys_Roles> allRoles = rolesService.getListBySort("name", "asc");
//        ShiroRealm.ShiroUser shiroUser = getCurrentUser();
//        String groupid = shiroUser.groupid;
//        List<Sys_Roles> allRoles = rolesService.getCurrentGroupRoles(groupid);
//        List<SetRolesVO> voList = new ArrayList<SetRolesVO>();
//        for (Sys_Roles r : allRoles) {
//            SetRolesVO rolesVO = new SetRolesVO();
//            rolesVO.setId(r.getId());
//            rolesVO.setName(r.getName());
//            rolesVO.setRemark(r.getRemark());
//
//            for (Sys_Roles ur : vo.roles) {
//                if (StringUtils.equals(r.getId(), ur.getId())) {
//                    rolesVO.setChecked("1");
//                }
//            }
//
//            voList.add(rolesVO);
//        }
//
//        model.addAttribute("userid", vo.userid);
//        model.addAttribute("username", vo.username);
//        model.addAttribute("list", voList);
//
//        return "system/user/setroles";
//    }


}
