<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_org_u_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_org_u_id"/>
    <input type="hidden" id="sys_org_u_groupid" />
    <input type="hidden" id="sys_org_u_parentid"/>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">机构名称：</td>
            <td><input id="sys_org_u_name" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_org_u_enableflag" checked="checked"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_org_u_displayorder" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">上级机构：</td>
            <td>
                <input id="sys_org_u_parentname" readonly="readonly"/>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="sys_org_u_selOrg()">选择</a>
            </td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">职责：</td>
            <td colspan="3"><textarea id="sys_org_u_duty" rows="3" cols="40%"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="3"><textarea id="sys_org_u_remark" rows="3" cols="40%"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_org_u_form_reset() {
        document.getElementById("sys_org_u_form").reset();
        //如果有隐藏字段，需要手工清理
        $("#sys_org_u_parentid").val("");
    }

    //从后台获取数据后，填充到对话框中
    function sys_org_u_form_setValues(data) {
        $("#sys_org_u_id").val(data.id);
        $("#sys_org_u_groupid").val(data.groupid);
        $("#sys_org_u_name").val(data.name);
        $("#sys_org_u_displayorder").val(data.displayorder);
        $("#sys_org_u_parentid").val(data.parentid);
        $("#sys_org_u_parentname").val(data.parentname);
        $("#sys_org_u_duty").val(data.duty);
        $("#sys_org_u_enableflag").prop("checked", data.enableflag);
        $("#sys_org_u_remark").val(data.remark);
    }

    function sys_org_u_selOrg(){
        hlg.dialog.openorgDialog(false,function(data){
            if(data.length>0){
                $("#sys_org_u_parentid").val(data[0].id);
                $("#sys_org_u_parentname").val(data[0].name);
            }
        });
    }

    //从Form获取数据后，返回数据对象
    function sys_org_u_form_getValues(data) {
        var entity = {};
        entity.id = $("#sys_org_u_id").val();
        entity.groupid = $("#sys_org_u_groupid").val();
        entity.name = $("#sys_org_u_name").val();
        entity.displayorder = $("#sys_org_u_displayorder").val();
        entity.parentid = $("#sys_org_u_parentid").val();
        entity.duty = $("#sys_org_u_duty").val();
        entity.enableflag = $("#sys_org_u_enableflag").prop("checked");
        entity.remark = $("#sys_org_u_remark").val();

        return entity;
    }
</script>