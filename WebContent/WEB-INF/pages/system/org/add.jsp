<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_org_a_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_org_a_parentcode"/>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">机构编码：</td>
            <td><input id="sys_org_a_code" class="easyui-validatebox" data-options="required:true"/></td>
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
            <td colspan="4"><textarea id="sys_org_a_duty" rows="4" cols="60%"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="4"><textarea id="sys_org_a_remark" rows="4" cols="60%"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_org_a_form_reset() {
        document.getElementById("sys_org_a_form").reset();
        //如果有隐藏字段，需要手工清理
        $("#sys_org_a_parentcode").val("");
    }

    function sys_org_a_form_init(flag, parentcode, parentname) {
        if (parentcode)
            $("#sys_org_a_parentcode").val(parentcode);
        if (parentname)
            $("#sys_org_a_parentname").val(parentname);
    }

    //从Form获取数据后，返回数据对象
    function sys_org_a_form_getValues(data) {
        var entity = {};
      //  debugger;
        entity.orgname = $("#sys_org_a_name").val();
        entity.orgcode = $("#sys_org_a_code").val();
        entity.displayorder = $("#sys_org_a_displayorder").val();
        entity.parentcode = $("#sys_org_a_parentcode").val();
        entity.duty = $("#sys_org_a_duty").val();
        entity.enableflag = $("#sys_org_a_enableflag").prop("checked");
        entity.remark = $("#sys_org_a_remark").val();

        return entity;
    }
</script>