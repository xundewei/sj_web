<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="sys_roles_UpdateDialog" class="easyui-dialog" title="修改"
     style="width:600px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                       sys_roles_Update();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_roles_UpdateDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_roles_u_form">
        <div class="hlg-title">基本信息</div>
        <input type="hidden" id="sys_roles_u_id" name="pkSysRole"/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
         <tr>
                <td style="text-align: right; width: 15%">角色编码：</td>
                <td><input name="rolecode" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td style="text-align: right; width: 15%">角色名称：</td>
                <td><input name="rolename" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td style="text-align: right; vertical-align: top;">备注：</td>
                <td colspan="3"><textarea name="remark" rows="3" cols="40%"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">
    //打开修改对话框
    function sys_roles_openUpdateDialog(row) {
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
        hlg.form.clear("#sys_roles_u_form");

        $('#sys_roles_UpdateDialog').dialog('open');

        var url = "system/roles/" + $rowid;
        hlg.form.load("#sys_roles_u_form", url);
    }

    //进行修改
    function sys_roles_Update() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_roles_u_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/roles/update";
        hlg.form.save("#sys_roles_u_form", url, function(){
            $('#sys_roles_UpdateDialog').dialog('close');
            hlg.grid.reload("#sys_roles_grid");
        });
    }

</script>