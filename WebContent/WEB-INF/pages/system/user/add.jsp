<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="sys_user_AddDialog" class="easyui-dialog" title="新增"
     style="width:600px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        sys_user_Add();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_user_AddDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_user_a_form">
        <div class="hlg-title">基本信息</div>
        <input type="hidden" id="sys_user_a_orgaid" name="orgaid"/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td style="text-align: right; width: 15%">姓名：</td>
                <td><input name="displayname" class="easyui-validatebox"
                           data-options="required:true"/></td>
                <td style="text-align: right; width: 15%">编号：</td>
                <td><input name="usercode"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">登录名：</td>
                <td><input name="loginname" class="easyui-validatebox"
                           data-options="required:true"/></td>
                <td style="text-align: right;">性别：</td>
                <td>
                    <input type="radio" id="sex" name="sex" value="男" checked="checked"/>男
                    <input type="radio" name="sex" value="女"/>女
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">固定电话：</td>
                <td><input id="sys_user_a_phone" name="phone"/></td>
                <td style="text-align: right;">手机：</td>
                <td><input id="sys_user_a_mobile" name="mobile"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">邮箱：</td>
                <td><input name="email"/></td>
                <td style="text-align: right;">地址：</td>
                <td><input name="address"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">所属机构：</td>
                <td>
                    <input id="sys_user_a_organame" readonly="readonly"/>
                    <a id="sys_user_a_btnQuery" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="sys_user_a_selOrg()">选择</a>
                </td>
                <td style="text-align: right;">是否可用：</td>
                <td><input type="checkbox" checked="checked" id="sys_user_a_enableflag" name="enableflag" value="true" data-unchecked-value="false"/></td>
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
    function sys_user_openAddDialog() {
        //清除对话框中的显示值，如果有的话
        hlg.form.clear("#sys_user_a_form");
        $("#sex").prop("checked", true);
        $("#sys_user_a_enableflag").prop("checked", true);

        //将左侧树选中节点的机构id/name传入过来，作为新增数据的父
        var id = $("#sys_user_orgaid").val();
        $("#sys_user_a_orgaid").val(id);

        var name = $("#sys_user_organame").val();
        $("#sys_user_a_organame").val(name);

        $('#sys_user_AddDialog').dialog('open');
    }

    function sys_user_a_selOrg(){
        hlg.dialog.openOrgaDialog(false,function(data){
           if(data.length>0){
               $("#sys_user_a_orgaid").val(data[0].id);
               $("#sys_user_a_organame").val(data[0].name);
           }
        });
    }

    //进行新增
    function sys_user_Add() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_user_a_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/user/add";
        hlg.form.save("#sys_user_a_form", url, function(){
            $('#sys_user_AddDialog').dialog('close');
            hlg.grid.reload("#sys_user_grid");
        });
    }

</script>