<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div id="sys_roles_DetailsDialog" class="easyui-dialog" title="查看"
     style="width:600px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        $('#sys_roles_DetailsDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_roles_d_form">
        <div class="hlg-title">基本信息</div>
        <input type="hidden" id="sys_roles_d_id" name="pkSysRole"/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
         <tr>
                <td style="text-align: right; width: 15%">角色编码：</td>
                <td><input name="rolecode" readonly="readonly"/></td>
            </tr>
            <tr>
                <td style="text-align: right; width: 15%">角色名称：</td>
                <td><input name="rolename" readonly="readonly"/></td>
            </tr>
            <tr>
                <td style="text-align: right; vertical-align: top;">备注：</td>
                <td colspan="3"><textarea name="remark" rows="3" cols="40%" readonly="readonly"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">

    //打开查看对话框，查看某条数据
    function sys_roles_OpenDetailsDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.pkSysRole;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        //清除对话框中的显示值，如果有的话
        hlg.form.clear("#sys_roles_d_form");

        $('#sys_roles_DetailsDialog').dialog('open');

        var url = "system/roles/" + $rowid;
        hlg.form.load("#sys_roles_d_form", url);
    }
</script>