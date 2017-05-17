<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_menu_d_form">
    <div class="hlg-title">基本信息</div>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">菜单名称：</td>
            <td><input id="sys_menu_d_name" readonly="readonly"/></td>
            <td style="text-align: right; width: 15%">菜单类型：</td>
            <td>
                <select id="sys_menu_d_menutype">
                    <option value="0">物业专用</option>
                    <option value="1">平台专用</option>
                    <option value="2">共用</option>
                </select><br/><br/>
            </td>
        </tr>
        <tr>
            <td style="text-align: right; width: 15%">显示名称：</td>
            <td><input id="sys_menu_d_displayname" readonly="readonly"/></td>
            <td style="text-align: right; width: 15%">菜单标识：</td>
            <td><input id="sys_menu_d_menukey" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_menu_d_displayorder" readonly="readonly"/></td>
            <td style="text-align: right;">上级菜单：</td>
            <td><input id="sys_menu_d_parentname" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">URL：</td>
            <td><input id="sys_menu_d_url" readonly="readonly"/></td>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_menu_d_enableflag" checked="checked" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="3"><textarea id="sys_menu_d_remark" rows="3" cols="40%" readonly="readonly"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_menu_d_form_reset() {
        document.getElementById("sys_menu_d_form").reset();
        //如果有隐藏字段，需要手工清理
    }

    //从后台获取数据后，填充到对话框中
    function sys_menu_d_form_setValues(data) {
        $("#sys_menu_d_name").val(data.name);
        $("#sys_menu_d_menutype").val(data.menutype);
        $("#sys_menu_d_displayname").val(data.displayname);
        $("#sys_menu_d_menukey").val(data.menukey);
        $("#sys_menu_d_displayorder").val(data.displayorder);
        $("#sys_menu_d_parentname").val(data.parentname);
        $("#sys_menu_d_url").val(data.url);
        $("#sys_menu_d_enableflag").prop("checked", data.enableflag);
        $("#sys_menu_d_remark").val(data.remark);
    }
</script>


