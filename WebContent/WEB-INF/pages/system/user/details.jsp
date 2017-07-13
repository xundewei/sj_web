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
        <input type="hidden" id="sys_user_d_orgaid" name="pkSysOrg"/>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
             	<td style="text-align: right; width: 15%">用户编号：</td>
                <td><input name="usercode"  readonly="readonly"/></td>
                <td style="text-align: right; width: 15%">姓名：</td>
                <td><input name="username" readonly="readonly"/></td>
               
            </tr>
            <tr>
                <td style="text-align: right;">性别：</td>
                <td>
                    <input type="radio" name="sex" value="01"/>男
                    <input type="radio" name="sex" value="02"/>女
                </td>
                 <td style="text-align: right;">手机：</td>
                 <td><input name="mobile" readonly="readonly"/></td>
            </tr>
            <tr>
                <td style="text-align: right;">所属机构：</td>
                <td><input name="orgname" readonly="readonly"/></td>
                <td style="text-align: right;">是否可用：</td>
                <td><input type="checkbox" id="sys_user_d_flag" name="flag" readonly="readonly"/></td>
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

        var $rowid = row.pkSysUser;
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
                $("#sys_user_d_flag").prop("checked", true);
            }
        });
    }
</script>