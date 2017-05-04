<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_org_d_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_org_d_parentid"/>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">机构名称：</td>
            <td><input id="sys_org_d_name" readonly="readonly"/></td>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_org_d_enableflag" checked="checked" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_org_d_displayorder"  readonly="readonly"/></td>
            <td style="text-align: right;">上级机构：</td>
            <td><input id="sys_org_d_parentname" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">职责：</td>
            <td colspan="3"><textarea id="sys_org_d_duty" rows="3" cols="40%" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="3"><textarea id="sys_org_d_remark" rows="3" cols="40%" readonly="readonly"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_org_d_form_reset() {
        document.getElementById("sys_org_d_form").reset();
        //如果有隐藏字段，需要手工清理
        $("#sys_org_d_parentid").val("");
    }

    //从后台获取数据后，填充到对话框中
    function sys_org_d_form_setValues(data) {
        $("#sys_org_d_name").val(data.name);
        $("#sys_org_d_displayorder").val(data.displayorder);
        $("#sys_org_d_parentname").val(data.parentname);
        $("#sys_org_d_duty").val(data.duty);
        $("#sys_org_d_enableflag").prop("checked", data.enableflag);
        $("#sys_org_d_remark").val(data.remark);
    }
</script>