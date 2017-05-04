<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_org_a_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_org_a_parentid"/>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">机构名称：</td>
            <td><input id="sys_org_a_name" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_org_a_enableflag" checked="checked"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_org_a_displayorder" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">上级机构：</td>
            <td><input id="sys_org_a_parentname" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">职责：</td>
            <td colspan="3"><textarea id="sys_org_a_duty" rows="3" cols="40%"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="3"><textarea id="sys_org_a_remark" rows="3" cols="40%"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_org_a_form_reset() {
        document.getElementById("sys_org_a_form").reset();
        //如果有隐藏字段，需要手工清理
        $("#sys_org_a_parentid").val("");
    }

    function sys_org_a_form_init(flag, parentid, parentname) {
        if (parentid)
            $("#sys_org_a_parentid").val(parentid);
        if (parentname)
            $("#sys_org_a_parentname").val(parentname);
    }

    //从Form获取数据后，返回数据对象
    function sys_org_a_form_getValues(data) {
        var entity = {};
        entity.name = $("#sys_org_a_name").val();
        entity.displayorder = $("#sys_org_a_displayorder").val();
        entity.parentid = $("#sys_org_a_parentid").val();
        entity.duty = $("#sys_org_a_duty").val();
        entity.enableflag = $("#sys_org_a_enableflag").prop("checked");
        entity.remark = $("#sys_org_a_remark").val();

        return entity;
    }
</script>