<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div id="sys_user_ResetDialog" class="easyui-dialog" title="重置密码"
     style="width:400px;height:250px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                       sys_user_Update2();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_user_ResetDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_user_r_form">
        <div class="hlg-title">输入新密码</div>
        <input type="hidden" id="sys_user_r_id" name="id"/>
        <br/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td style="text-align: right; width: 35%">新密码：</td>
                <td><input type="password" id="sys_user_r_p1" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">再次输入：</td>
                <td><input type="password" id="sys_user_r_p2" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">

    function sys_user_OpenResetDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一行记录！");
            return;
        }

        var $rowid = row.pkSysUser;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        //清除对话框中的显示值，如果有的话
        hlg.form.clear("#sys_user_r_form");

        $('#sys_user_ResetDialog').dialog('open');
        $("#sys_user_r_id").val($rowid);
    }

    //进行修改
    function sys_user_Update2() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_user_r_form").form('validate');
        if (!isValid)
            return;

        var p1 = $("#sys_user_r_p1").val();
        var p2 = $("#sys_user_r_p2").val();
        if(p1 != p2){
            hlg.dialog.showInfo("两次输入的新密码不一致！");
            return;
        }

        //验证通过后保存数据到后台
        var url = "system/user/resetPwd";
        var entity = {};
        entity.param1=$("#sys_user_r_id").val();
        entity.param2=p1;
        hlg.ajax.saveEntity(url, entity, function(data){
            $('#sys_user_ResetDialog').dialog('close');
        });
    }
</script>