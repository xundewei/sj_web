<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="sys_user_AddDialog" class="easyui-dialog" title="新增"
     style="width:800px;height:350px;padding:5px"
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
        <input type="hidden" id="sys_user_a_pk_sys_code" name="pkSysOrg"/>
        <table style="width: 100%; border-collapse: collapse; padding: 5px;">
            <tr>
                <td style="text-align: right; width: 15%">姓名：</td>
                <td><input name="username" class="easyui-validatebox"data-options="required:true"/></td>
                <td style="text-align: right; width: 15%">编号：</td>
                <td><input name="usercode"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">性别：</td>
                <td>
                    <input type="radio" id="sex" name="sex" value="01" checked="checked"/>男
                    <input type="radio" name="sex" value="02"/>女
                </td>
                <td style="text-align: right;">手机：</td>
                <td><input id="sys_user_a_mobile" name="mobile"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">所属机构：</td>
                <td>
                    <input id="sys_user_a_orgname" readonly="readonly"/>
                    <a id="sys_user_a_btnQuery" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="sys_user_a_selOrg()">选择</a>
                    
                </td>
                <td style="text-align: right;">是否可用：</td>
                <td><input type="checkbox" checked="checked" id="sys_user_a_flag" name="flag" value="true" data-unchecked-value="false"/></td>
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
        $("#sys_user_a_flag").prop("checked", true);
        //将左侧树选中节点的机构id/name传入过来，作为新增数据的父
        var name = $("#sys_user_orgname").val();
        var pkSysOrg= $("#sys_user_pk_sys_code").val();

        $("#sys_user_a_pk_sys_code").val(pkSysOrg);
        $("#sys_user_a_orgname").val(name);
        
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