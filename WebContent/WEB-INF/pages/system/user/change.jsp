<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div id="sys_user_ChangeDialog" class="easyui-dialog" title="修改密码"
     style="width:400px;height:250px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                       sys_user_Update3();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_user_ChangeDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_user_cg_form">
        <div class="hlg-title">输入新密码</div>
        <input type="hidden" id="sys_user_cg_id" name="id" value="${id}"/>
        <br/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td style="text-align: right; width: 35%">旧密码：</td>
                <td><input type="password" id="sys_user_cg_p1" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td style="text-align: right; width: 35%">新密码：</td>
                <td><input type="password" id="sys_user_cg_p2" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">再次输入：</td>
                <td><input type="password" id="sys_user_cg_p3" class="easyui-validatebox"
                           data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">

    //进行修改
    function sys_user_Update3() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_user_cg_form").form('validate');
        if (!isValid)
            return;

        var p1 = $("#sys_user_cg_p1").val();
        var p2 = $("#sys_user_cg_p2").val();
        var p3 = $("#sys_user_cg_p3").val();

        if(p2 != p3){
            hlg.dialog.showInfo("两次输入的新密码不一致！");
            return;
        }

        if(p1 == p2){
            hlg.dialog.showInfo("新密码与旧密码相同！");
            return;
        }

        //验证通过后保存数据到后台
        var url = "system/user/update3";
        var entity = {};
        entity.p0=$("#sys_user_cg_id").val();
        entity.p1=p1;
        entity.p2=p2;
        hlg.ajax.saveEntity(url, entity, function(data){
            $('#sys_user_ChangeDialog').dialog('close');
        });
    }
</script>