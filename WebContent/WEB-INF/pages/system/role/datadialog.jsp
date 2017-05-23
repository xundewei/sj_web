<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="sys_roles_DataDialog" class="easyui-dialog" title="设置数据范围"
     style="width:600px;height:400px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        sys_roles_data_update();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_roles_DataDialog').dialog('close');
                    }
                }]
            ">
    <input type="hidden" id="sys_roles_data_id" name="id"/>
    <input type="hidden" id="sys_roles_data_roleid" name="roleid"/>
    <input type="hidden" id="sys_roles_data_menuid" name="menuid"/>

    <form id="sys_roles_data_form">
        <div class="hlg-title">常规设置</div>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td style="text-align: right; width: 35%">允许查看个人数据(默认)：</td>
                <td><input type="checkbox" id="sys_roles_data_owner" checked="checked" onclick='return false;'/> </td>
            </tr>
            <tr>
                <td style="text-align: right; width: 35%">允许查看个人所在部门数据：</td>
                <td><input type="checkbox" id="sys_roles_data_currentorga" /> </td>
                <td style="text-align: right; width: 35%">允许查看所有数据：</td>
                <td><input type="checkbox" id="sys_roles_data_alldata" /> </td>
            </tr>
        </table>
        <br/>
        <div class="hlg-title">查看以下人员数据</div>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td colspan="3">
                    <textarea id="sys_roles_data_customuser" rows="3" cols="48%" readonly="readonly"></textarea>
                </td>
                <td style="text-align: right; vertical-align: top;">
                    <a id="sys_roles_data_seluser" href="#" class="easyui-linkbutton"
                       data-options="iconCls:'icon-search'" onclick="sys_roles_data_selectUser()">选择人员</a>
                    <a href="#" class="easyui-linkbutton"
                       data-options="iconCls:'icon-undo'" onclick="sys_roles_data_resetUser()">重置人员</a>
                </td>
            </tr>
        </table>
        <br/>
        <div class="hlg-title">查看以下部门数据</div>
        <table style="width: 100%; border-collapse: collapse; padding: 10px;">
            <tr>
                <td colspan="3">
                    <textarea id="sys_roles_data_customorga" rows="3" cols="48%" readonly="readonly"></textarea>
                </td>
                <td style="text-align: right; vertical-align: top;">
                    <a id="sys_roles_data_selorga" href="#" class="easyui-linkbutton"
                       data-options="iconCls:'icon-search'" onclick="sys_roles_data_selectOrga()">选择部门</a>
                    <a href="#" class="easyui-linkbutton"
                       data-options="iconCls:'icon-undo'" onclick="sys_roles_data_resetOrga()">重置部门</a>
                </td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">

    //打开数据范围对话框
    function sys_roles_openDataDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.id;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        if (row.level == 0) {
            hlg.dialog.showInfo("请选择子菜单进行数据范围设置！");
            return;
        }

        //清除对话框中的显示值，如果有的话
        hlg.form.clear("#sys_roles_data_form");
        $("#sys_roles_data_owner").prop("checked", true);
        $("#sys_roles_data_currentorga").prop("checked", false);
        $("#sys_roles_data_alldata").prop("checked", false);

        //init
        $("#sys_roles_data_id").val($rowid);
        $("#sys_roles_data_roleid").val(row.roleid);
        $("#sys_roles_data_menuid").val(row.menuid);

        //$("#sys_roles_data_title").html("[" + row.displayname + "]数据范围设置");

        $('#sys_roles_DataDialog').dialog('open');

        //从后台获取权限数据
        var url = "system/perms/" + $rowid;
        hlg.ajax.getEntity(url, function(data){
            $("#sys_roles_data_currentorga").prop("checked", data.currentorgaflag);
            $("#sys_roles_data_alldata").prop("checked", data.alldataflag);

            //初始化自选用户列表，显示到文本框
            if(data.customuserflag){
                var  userStr = "";
                sys_roles_data_selectUser_array = [];
                for(var i=0;i<data.customuser.length;i++){
                    var user = data.customuser[i];
                    userStr += user.username+ " ";
                    sys_roles_data_selectUser_array[i] = user.userid;
                }
                $("#sys_roles_data_customuser").val(userStr);
            }

            //初始化自选组织列表，显示到文本框
            if(data.customorgaflag){
                var  orgaStr = "";
                sys_roles_data_selectOrga_array = [];
                for(var i=0;i<data.customorga.length;i++){
                    var orga = data.customorga[i];
                    orgaStr += orga.organame+ " ";
                    sys_roles_data_selectOrga_array[i] = orga.orgaid;
                }
                $("#sys_roles_data_customorga").val(orgaStr);
            }
        });
    }

    //重置选择的用户
    function sys_roles_data_resetUser(){
        sys_roles_data_selectUser_array = [];
        $('#sys_roles_data_customuser').val('');
    }

    //选择用户
    var sys_roles_data_selectUser_array = [];
    function sys_roles_data_selectUser(){
        hlg.dialog.openUserDialog(true, function(data){
            if (data == null)
                return;

            if (data.length == 0)
                return;

            sys_roles_data_selectUser_array = [];
            var roles = "";
            for (var i = 0; i < data.length; i++) {
                var node = data[i];
                roles += node.displayname + " ";
                sys_roles_data_selectUser_array[i] = node.id;
            }
            $("#sys_roles_data_customuser").val(roles);
        });
    }

    //重置选择的组织机构
    function sys_roles_data_resetOrga(){
        sys_roles_data_selectOrga_array = [];
        $('#sys_roles_data_customorga').val('');
    }

    //选择组织机构
    var sys_roles_data_selectOrga_array = [];
    function sys_roles_data_selectOrga(){
        hlg.dialog.openOrgaDialog(true,  function(data){
            if (data == null)
                return;

            if (data.length == 0)
                return;

            sys_roles_data_selectOrga_array = [];
            var roles = "";
            for (var i = 0; i < data.length; i++) {
                var node = data[i];
                roles += node.name + " ";
                sys_roles_data_selectOrga_array[i] = node.id;
            }
            $("#sys_roles_data_customorga").val(roles);
        });
    }

    //进行更新
    function sys_roles_data_update() {
        var entity = {};
        entity.id = $("#sys_roles_data_id").val();
        entity.roleid = $("#sys_roles_data_roleid").val();
        entity.menuid = $("#sys_roles_data_menuid").val();

        entity.currentorgaflag = $("#sys_roles_data_currentorga").prop("checked");
        entity.alldataflag = $("#sys_roles_data_alldata").prop("checked");

        //获取自定义用户列表
        entity.customuserflag = sys_roles_data_selectUser_array.length != 0;
        entity.customuser = sys_roles_data_selectUser_array;

        //获取自定义组织列表
        entity.customorgaflag = sys_roles_data_selectOrga_array.length != 0;
        entity.customorga = sys_roles_data_selectOrga_array;


        //验证通过后保存数据到后台
        var url = "system/perms/update";
        hlg.ajax.saveEntity(url, entity, function(data){
            sys_roles_data_selectUser_array=[];
            sys_roles_data_selectOrga_array=[];
            $('#sys_roles_DataDialog').dialog('close');
            hlg.treegrid.reload("#sys_roles_grid2");
        });

    }

</script>