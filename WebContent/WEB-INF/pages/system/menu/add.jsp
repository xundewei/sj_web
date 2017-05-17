<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_menu_a_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_menu_a_level"/>
    <input type="hidden" id="sys_menu_a_parentcode"/>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">菜单名称：</td>
            <td><input id="sys_menu_a_name" class="easyui-validatebox" data-options="required:true"/></td>
             <td style="text-align: right; width: 15%">菜单编码：</td>
            <td><input id="sys_menu_a_code" class="easyui-validatebox" data-options="required:true"/></td>
        </tr>
        <tr>
        	<td style="text-align: right; width: 15%">菜单类型：</td>
            <td>
                <select id="sys_menu_a_menutype">
                    <option value="0">物业专用</option>
                    <option value="1">平台专用</option>
                    <option value="2">共用</option>
                </select>
            </td>
            <td style="text-align: right; width: 15%">菜单标识：</td>
            <td><input id="sys_menu_a_menukey"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_menu_a_displayorder" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">上级菜单：</td>
            <td><input id="sys_menu_a_parentname" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">URL：</td>
            <td><input id="sys_menu_a_url"/>
            </td>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_menu_a_enableflag" checked="checked"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="3"><textarea id="sys_menu_a_remark" rows="3" cols="40%"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_menu_a_form_reset() {
        document.getElementById("sys_menu_a_form").reset();
        //如果有隐藏字段，需要手工清理
        $("#sys_menu_a_level").val("");
        $("#sys_menu_a_parentcode").val("");
    }

    function sys_menu_a_form_init(flag, parentcode, parentname, parentlevel) {
        if (flag == "main")
            $("#sys_menu_a_level").val("1");
        else {
            var level = parseInt(parentlevel) + 1;
            $("#sys_menu_a_level").val(level);
        }

        if (parentcode)
            $("#sys_menu_a_parentcode").val(parentcode);
        if (parentname)
            $("#sys_menu_a_parentname").val(parentname);
    }

    //从Form获取数据后，返回数据对象
    function sys_menu_a_form_getValues(data) {
        var entity = {};
        entity.menuname = $("#sys_menu_a_name").val();
        entity.menucode = $("#sys_menu_a_code").val();
        entity.displayorder = $("#sys_menu_a_displayorder").val();
        entity.lev = $("#sys_menu_a_level").val();
        entity.parentcode = $("#sys_menu_a_parentcode").val();
        entity.url = $("#sys_menu_a_url").val();
        entity.enableflag = $("#sys_menu_a_enableflag").prop("checked");
        entity.remark = $("#sys_menu_a_remark").val();

        return entity;
    }
</script>