<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_menu_u_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_menu_u_id"/>
    <input type="hidden" id="sys_menu_u_level"/>
    <input type="hidden" id="sys_menu_u_parentid"/>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">菜单名称：</td>
            <td><input id="sys_menu_u_name" readonly="readonly"/></td>
            <td style="text-align: right; width: 15%">菜单类型：</td>
            <td>
                <select id="sys_menu_u_menutype">
                    <option value="0">物业专用</option>
                    <option value="1">平台专用</option>
                    <option value="2">共用</option>
                </select><br/><br/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right; width: 15%">显示名称：</td>
            <td><input id="sys_menu_u_displayname" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right; width: 15%">菜单标识：</td>
            <td><input id="sys_menu_u_menukey"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_menu_u_displayorder" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">上级菜单：</td>
            <td><input id="sys_menu_u_parentname" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">URL：</td>
            <td><input id="sys_menu_u_url"/></td>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_menu_u_enableflag" checked="checked"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="3"><textarea id="sys_menu_u_remark" rows="3" cols="40%"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_menu_u_form_reset() {
        document.getElementById("sys_menu_u_form").reset();
        //如果有隐藏字段，需要手工清理
        $("#sys_menu_u_level").val("");
        $("#sys_menu_u_parentid").val("");
    }

    //从后台获取数据后，填充到对话框中
    function sys_menu_u_form_setValues(data) {
        $("#sys_menu_u_id").val(data.id);
        $("#sys_menu_u_menutype").val(data.menutype);
        $("#sys_menu_u_name").val(data.name);
        $("#sys_menu_u_displayname").val(data.displayname);
        $("#sys_menu_u_menukey").val(data.menukey);
        $("#sys_menu_u_displayorder").val(data.displayorder);
        $("#sys_menu_u_level").val(data.level);
        $("#sys_menu_u_parentid").val(data.parentid);
        $("#sys_menu_u_parentname").val(data.parentname);
        $("#sys_menu_u_url").val(data.url);
        $("#sys_menu_u_enableflag").prop("checked", data.enableflag);
        $("#sys_menu_u_remark").val(data.remark);
    }

    //从Form获取数据后，返回数据对象
    function sys_menu_u_form_getValues(data) {
        var entity = {};
        entity.id = $("#sys_menu_u_id").val();
        entity.menutype = $("#sys_menu_u_menutype").val();
        entity.name = $("#sys_menu_u_name").val();
        entity.displayname = $("#sys_menu_u_displayname").val();
        entity.menukey = $("#sys_menu_u_menukey").val();
        entity.displayorder = $("#sys_menu_u_displayorder").val();
        entity.level = $("#sys_menu_u_level").val();
        entity.parentid = $("#sys_menu_u_parentid").val();
        entity.url = $("#sys_menu_u_url").val();
        entity.enableflag = $("#sys_menu_u_enableflag").prop("checked");
        entity.remark = $("#sys_menu_u_remark").val();

        return entity;
    }
</script>