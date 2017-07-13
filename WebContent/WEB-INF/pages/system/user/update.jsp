<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="sys_user_UpdateDialog" class="easyui-dialog" title="修改"
     style="width:600px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                       sys_user_Update();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_user_UpdateDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_user_u_form">
        <div class="hlg-title">基本信息</div>
        <input type="hidden" id="sys_user_u_orgaid" name="pkSysOrg"/>
        <input type="hidden" id="sys_user_u_pkSysUser" name="pkSysUser"/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td style="text-align: right; width: 15%">用户编号：</td>
                <td><input name="usercode" /></td>
                <td style="text-align: right; width: 15%">姓名：</td>
                <td><input name="username" class="easyui-validatebox"data-options="required:true"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">性别：</td>
                <td>
                    <input type="radio" id="sex" name="sex" value="01" checked="checked"/>男
                    <input type="radio" name="sex" value="02"/>女
                </td>
                 <td style="text-align: right;">手机：</td>
                <td><input name="mobile"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">所属机构：</td>
                <td>
                    <input id="sys_user_u_organame" name="orgname" readonly="readonly"/>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="sys_user_u_selOrg()">选择</a>
                </td>
                <td style="text-align: right;">是否可用：</td>
                <td><input type="checkbox" checked="checked" id="sys_user_u_flag" name="flag" value="true" data-unchecked-value="false"/></td>
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
    function sys_user_openUpdateDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.pkSysUser;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        //清除对话框中的显示值，如果有的话
        hlg.form.clear("#sys_user_u_form");

        $('#sys_user_UpdateDialog').dialog('open');

        var url = "system/user/" + $rowid;
        hlg.form.load("#sys_user_u_form", url, function(data){
            if(data.flag){
                $("#sys_user_u_flag").prop("checked", true);
            }
        });
    }

    function sys_user_u_selOrg(){
        hlg.dialog.openOrgaDialog(false,function(data){
            if(data.length>0){
                $("#sys_user_u_orgaid").val(data[0].id);
                $("#sys_user_u_organame").val(data[0].name);
            }
        });
    }


    //进行修改
    function sys_user_Update() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_user_u_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/user/update";
        hlg.form.save("#sys_user_u_form", url, function(){
            $('#sys_user_UpdateDialog').dialog('close');
            hlg.grid.reload("#sys_user_grid");
        });
    }

</script>