<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div id="sys_user_DetailsDialog" class="easyui-dialog" title="查看"
     style="width:600px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        $('#sys_user_DetailsDialog').dialog('close');
                    }
                }]
            ">
    <form id="sys_user_d_form">
        <div class="hlg-title">基本信息</div>
        <input type="hidden" id="sys_user_d_orgaid" name="orgaid"/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td style="text-align: right; width: 15%">姓名：</td>
                <td><input name="displayname" readonly="readonly"/></td>
                <td style="text-align: right; width: 15%">编号：</td>
                <td><input name="usercode"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">登录名：</td>
                <td><input name="loginname" readonly="readonly"/></td>
                <td style="text-align: right;">性别：</td>
                <td>
                    <input type="radio" name="sex" value="男"/>男
                    <input type="radio" name="sex" value="女"/>女
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">固定电话：</td>
                <td><input name="phone" readonly="readonly"/></td>
                <td style="text-align: right;">手机：</td>
                <td><input name="mobile" readonly="readonly"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">邮箱：</td>
                <td><input name="email" readonly="readonly"/></td>
                <td style="text-align: right;">地址：</td>
                <td><input name="address" readonly="readonly"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">所属机构：</td>
                <td><input name="organame" readonly="readonly"/></td>
                <td style="text-align: right;">是否可用：</td>
                <td><input type="checkbox" id="sys_user_d_enableflag" name="enableflag" readonly="readonly"/></td>
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
    function sys_user_OpenDetailsDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.id;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        //清除对话框中的显示值，如果有的话
        hlg.form.clear("#sys_user_d_form");

        $('#sys_user_DetailsDialog').dialog('open');

        var url = "system/user/" + $rowid;
        hlg.form.load("#sys_user_d_form", url, function(data){
            if(data.enableflag){
                $("#sys_user_d_enableflag").prop("checked", true);
            }
        });
    }
</script>