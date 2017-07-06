<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<form id="sys_menu_u_form">
    <div class="hlg-title">基本信息</div>
    <input type="hidden" id="sys_menu_u_id"/>
    <input type="hidden" id="sys_menu_u_level"/>
<!--     <input type="hidden" id="sys_menu_u_parentcode"/> -->
    <table style="width: 100%; border-collapse: collapse; padding: 10px;">
        <tr>
           <td style="text-align: right; width: 15%">菜单编码：</td>
            <td><input id="sys_menu_u_code" readonly="readonly"/></td> 
            <td style="text-align: right; width: 15%">菜单名称：</td>
            <td><input id="sys_menu_u_name" readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right; width: 15%">显示名称：</td>
            <td><input id="sys_menu_u_displayname" class="easyui-validatebox" data-options="required:true"/></td>
            <td style="text-align: right;">显示顺序：</td>
            <td><input id="sys_menu_u_displayorder" class="easyui-validatebox" data-options="required:true"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">上级菜单：</td>
            <td><input id="sys_menu_u_parentcode" class="easyui-validatebox" /><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="sys_org_u_selMenu()">选择</a></td> 
            <td style="text-align: right;">URL：</td>
            <td><input id="sys_menu_u_url"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">是否可用：</td>
            <td><input type="checkbox" id="sys_menu_u_enableflag" checked="checked"/></td>
        </tr>
        <tr>
            <td style="text-align: right; vertical-align: top;">备注：</td>
            <td colspan="3"><textarea id="sys_menu_u_remark" rows="3" cols="40%"/></td>
        </tr>
    </table>
</form>

<script type="text/javascript">
    //重置Form，清空所有的值
    function sys_menu_u_form_reset() {
        document.getElementById("sys_menu_u_form").reset();
        //如果有隐藏字段，需要手工清理
        $("#sys_menu_u_id").val("");
        $("#sys_menu_u_level").val("");
        $("#sys_menu_u_parentcode").val("");
    }
    
    

    //从后台获取数据后，填充到对话框中
    function sys_menu_u_form_setValues(data) {
        $("#sys_menu_u_id").val(data.pkSysMenu);
        $("#sys_menu_u_code").val(data.menucode);
        $("#sys_menu_u_name").val(data.menuname);
        $("#sys_menu_u_displayname").val(data.displayname);
        $("#sys_menu_u_displayorder").val(data.displayorder);
        $("#sys_menu_u_level").val(data.lev);
        $("#sys_menu_u_parentcode").val(data.parentcode);
        $("#sys_menu_u_parentname").val(data.parentname);
        $("#sys_menu_u_url").val(data.url);
        $("#sys_menu_u_enableflag").prop("checked", data.enableflag);
        $("#sys_menu_u_remark").val(data.remark);
    }

    //从Form获取数据后，返回数据对象
    function sys_menu_u_form_getValues(data) {
        var entity = {};
        entity.pkSysMenu = $("#sys_menu_u_id").val();
        entity.menucode = $("#sys_menu_u_code").val();
        entity.menuname = $("#sys_menu_u_name").val();
        entity.displayname = $("#sys_menu_u_displayname").val();
        entity.displayorder = $("#sys_menu_u_displayorder").val();
        entity.lev = $("#sys_menu_u_level").val();
        entity.parentcode = $("#sys_menu_u_parentcode").val();
        entity.url = $("#sys_menu_u_url").val();
        entity.enableflag = $("#sys_menu_u_enableflag").prop("checked");
        entity.remark = $("#sys_menu_u_remark").val();

        return entity;
    }
    
    
    function sys_org_u_selMenu(){
    	sys.dialog.openReferenceMenuDialog(false,function(data){
            if(data.length>0){
                $("#sys_org_u_parentcode").val(data[0].id);
        //        $("#sys_org_u_parentname").val(data[0].name);
            }
        });
     //   sys.dialog.openReferenceDialog('pub_select_org_Dialog','system/reference/showorgdialog',false,function(data){
       //     if(data.length>0){
         //       $("#sys_org_u_parentcode").val(data[0].id);
        //        $("#sys_org_u_parentname").val(data[0].name);
           // }
        //});
    }
</script>