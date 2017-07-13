<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<div id="sys_user_setRolesDialog" class="easyui-dialog" title="分配角色"
     style="width:600px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        sys_user_SetRoles();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_user_setRolesDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_user_set_form">
        <div class="hlg-title">[${username}]角色列表</div>
        <input type="hidden" id="sys_user_set_id" name="id" value="${userid}"/>

        <div>
                <c:forEach items="${list}" var="role">
                    <c:if test="${role.isown=='0'}">
                        <input type="checkbox" name="roles[]" value="${role.rolecode}"/>${role.rolename}
                    </c:if>
                    <c:if test="${role.isown=='1'}">
                        <input type="checkbox" checked="checked" name="roles[]" value="${role.rolecode}"/>${role.rolename}
                    </c:if>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                </c:forEach>
        </div>
    </form>
</div>

<script type="text/javascript">
    function sys_user_SetRoles(){
        var url = "system/userroles/update";
        hlg.form.save("#sys_user_set_form", url, function(){
            $('#sys_user_setRolesDialog').dialog('close');
        });
    }

</script>