<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="sys_roles_AddDialog" class="easyui-dialog" title="新增"
     style="width:600px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        sys_roles_Add();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_roles_AddDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_roles_a_form">
        <div class="hlg-title">基本信息</div>
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

    //打开新增对话框
    function sys_roles_openAddDialog() {
        //清除对话框中的显示值，如果有的话
        hlg.form.clear("#sys_roles_a_form");

        $('#sys_roles_AddDialog').dialog('open');
    }

    //进行新增
    function sys_roles_Add() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_roles_a_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/roles/add";
        hlg.form.save("#sys_roles_a_form", url, function(){
            $('#sys_roles_AddDialog').dialog('close');
            hlg.grid.reload("#sys_roles_grid");
        });
    }

</script>