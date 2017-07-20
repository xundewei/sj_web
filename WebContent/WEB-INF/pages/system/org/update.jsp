<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_org_u_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_org_u_pksysorg"/>
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
            <td style="text-align: right; width: 15%">机构编码：</td>
            <td><input id="sys_org_u_orgcode" class="easyui-validatebox" readonly="readonly"/></td>
            <td style="text-align: right; width: 15%">机构名称：</td>
            <td><input id="sys_org_u_orgname" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_org_u_enableflag" checked="checked"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_org_u_displayorder" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">上级机构：</td>
            <td>
                <input id="sys_org_u_parentcode" readonly="readonly"/>
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
        $("#sys_org_u_parentcode").val("");
    }

    //从后台获取数据后，填充到对话框中
    function sys_org_u_form_setValues(data) {
        $("#sys_org_u_pksysorg").val(data.pkSysOrg);
        $("#sys_org_u_orgcode").val(data.orgcode);
        $("#sys_org_u_orgname").val(data.orgname);
        $("#sys_org_u_displayorder").val(data.displayorder);
        $("#sys_org_u_parentcode").val(data.parentcode);
        $("#sys_org_u_parentname").val(data.parentname);
        $("#sys_org_u_duty").val(data.duty);
        $("#sys_org_u_enableflag").prop("checked", data.enableflag);
        $("#sys_org_u_remark").val(data.remark);
    }

    function sys_org_u_selOrg(){
        sys.dialog.openReferenceOrgDialog(false,function(data){
            if(data.length>0){
                $("#sys_org_u_parentcode").val(data[0].code);
        //        $("#sys_org_u_parentname").val(data[0].name);
            }
        });
    }

    //从Form获取数据后，返回数据对象
    function sys_org_u_form_getValues(data) {
        var entity = {};
        entity.pkSysOrg = $("#sys_org_u_pksysorg").val();
        entity.orgcode = $("#sys_org_u_orgcode").val();
        entity.orgname = $("#sys_org_u_orgname").val();
        entity.displayorder = $("#sys_org_u_displayorder").val();
        entity.parentcode = $("#sys_org_u_parentcode").val();
        entity.duty = $("#sys_org_u_duty").val();
        entity.enableflag = $("#sys_org_u_enableflag").prop("checked");
        entity.remark = $("#sys_org_u_remark").val();

        return entity;
    }
</script>