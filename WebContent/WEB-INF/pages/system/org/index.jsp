<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript">
    function sys_org_ConfigGrid() {
        $("#sys_org_grid").treegrid({
            loadMsg: "",
            url: 'system/org/list',
            idField: 'orgcode',
            treeField: 'orgcode',
            columns: [[
            	{field: 'pkSysOrg', title: '主键', width: 60, align: 'right', hidden: true},
                {field: 'orgcode', title: '机构编码', width: 60},
                {field: 'orgname', title: '机构名称', width: 80},
                {field: 'displayorder', title: '显示顺序', width: 40, sortable: true},
                {field: 'parentcode', title: '父编码', width: 120, hidden: true},
                {field: 'duty', title: '职责', width: 120},

                {
                    field: 'enableflag', title: '是否可用', width: 80, formatter: function (value, row, index) {
                    if (value) {
                        return "可用";
                    } else {
                        return "不可用";
                    }
                }, styler: function (value, row, index) {
                    if (value) {
                        return 'color:red;';
                    }
                    return 'color:green;';
                }
                }
            ]],
            fitColumns: true,
            lines: true,
            rownumbers: true,
            singleSelect: true,
            sortName: "displayorder",
            sortOrder: "asc",
            onDblClickRow: function () {
                var row = $('#sys_org_grid').treegrid('getSelected');
                sys_org_OpenDetailsDialog(row);
            },
            toolbar: "#sys_org_toolbar",
            onContextMenu: sys_org_OnContextMenu
        });
    }

    //右键菜单
    function sys_org_OnContextMenu(e, row) {
        e.preventDefault();
        $(this).treegrid('select', row.orgcode);
        $('#sys_org_mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    }

    //配置Grid工具栏
    function sys_org_ConfigToolbar() {
        $("#sys_org_btnAddMain").click(function () {
            sys_org_AddMain();
        });

        $("#sys_org_btnAddChild").click(function () {
            sys_org_AddSub();
        });

        $("#sys_org_btnEdit").click(function () {
            var row = $('#sys_org_grid').treegrid('getSelected');
            sys_org_openUpdateDialog(row);
        });

        $("#sys_org_btnView").click(function () {
            var row = $('#sys_org_grid').treegrid('getSelected');
            sys_org_OpenDetailsDialog(row);
        });

        $("#sys_org_btnDelete").click(function () {
            var row = $('#sys_org_grid').treegrid('getSelected');
            sys_org_Delete(row);
        });
    }

    //打开新增根机构对话框
    function sys_org_AddMain() {
        sys_org_openAddDialog("main");
    }

    //打开新增子机构对话框
    function sys_org_AddSub() {
        var row = $('#sys_org_grid').treegrid('getSelected');
        if (row == null) {
            hlg.dialog.showInfo("请先选择根机构！");
            return;
        }

        var parentcode = row.orgcode;
        var parentname = row.orgname;
        sys_org_openAddDialog("child", parentcode, parentname);
    }

    //打开新增对话框
    function sys_org_openAddDialog(flag, parentcode, parentname) {
        //清除对话框中的显示值，如果有的话
        sys_org_a_form_reset();
        sys_org_a_form_init(flag, parentcode, parentname);
        $('#sys_org_AddDialog').dialog('open');
    }

    //进行新增
    function sys_org_Add() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_org_a_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/org/add";
        var entity = sys_org_a_form_getValues();
        hlg.ajax.saveEntity(url, entity, function () {
            $('#sys_org_AddDialog').dialog('close');
            hlg.treegrid.reload("#sys_org_grid");
        });
    }

    //打开修改对话框
    function sys_org_openUpdateDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.pkSysOrg;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        //清除对话框中的显示值，如果有的话
        sys_org_u_form_reset();
        $('#sys_org_UpdateDialog').dialog('open');

        var url = "system/org/" + $rowid;
        hlg.ajax.getEntity(url, function (data) {
            //从后台获取数据后，填充到对话框中
            sys_org_u_form_setValues(data);
        });
    }

    //点右键，选择编辑
    function sys_org_RightUpdate() {
        var row = $('#sys_org_grid').treegrid('getSelected');
        sys_org_openUpdateDialog(row);
    }

    //进行修改
    function sys_org_Update() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_org_u_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/org/update";
        var entity = sys_org_u_form_getValues();
        hlg.ajax.saveEntity(url, entity, function () {
            $('#sys_org_UpdateDialog').dialog('close');
            hlg.treegrid.reload("#sys_org_grid");
        });
    }

    //打开查看对话框，查看某条数据
    function sys_org_OpenDetailsDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.pkSysOrg;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        //清除对话框中的显示值，如果有的话
        sys_org_d_form_reset();

        $('#sys_org_DetailsDialog').dialog('open');

        var url = "system/org/" + $rowid;
        hlg.ajax.getEntity(url, function (data) {
            //从后台获取数据后，填充到对话框中
            sys_org_d_form_setValues(data);
        });
    }

    //右键删除
    function sys_org_RightDelete() {
        var row = $('#sys_org_grid').treegrid('getSelected');
        sys_org_Delete(row);
    }

    //进行删除
    function sys_org_Delete(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.pkSysOrg;
       
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }
        var $rowcode = row.orgcode;
        
        var msg = "确定删除机构[" + row.name + "]吗？";
        hlg.dialog.showConfirm(msg, function () {
            var url = "system/org/delete/id/" + $rowid+"/code/"+$rowcode;
            hlg.ajax.deleteEntity(url, function () {
                hlg.treegrid.reload("#sys_org_grid");
            });
        });
    }

    //设置Grid的高度充满
    function sys_org_SetHeight(){
        var height = $("#main_leftmenudiv").css("height");
        var h = parseInt(height);

        $("#sys_org_grid").datagrid('resize', {
            height: h - topheight + 12
        });

    }

    //当前页面初始化动作
    $(function () {
        sys_org_ConfigGrid();
        sys_org_ConfigToolbar();
        sys_org_SetHeight();
    });

</script>
<div class="place">
    <ul class="placeul">
        <li><a href="#">组织机构</a></li>
    </ul>
</div>

<%--<div style="padding: 5px; border: 1px solid #ddd;">--%>
<div style="padding: 6px;">
    <div id="sys_org_toolbar">
        <a id="sys_org_btnAddMain" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增根机构</a>
        <a id="sys_org_btnAddChild" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增子机构</a>
        <a id="sys_org_btnEdit" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a>
        <a id="sys_org_btnView" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">查看</a>
        <a id="sys_org_btnDelete" href="#" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-cancel'">删除</a>
    </div>
    <div>
        <table id="sys_org_grid" class="hlg-grid"></table>
    </div>

    <div id="sys_org_mm" class="easyui-menu" style="width:120px;">
        <div onclick="sys_org_AddSub()" data-options="iconCls:'icon-add'">新增子机构</div>
        <div onclick="sys_org_RightUpdate()" data-options="iconCls:'icon-edit'">修改</div>
        <div onclick="sys_org_RightDelete()" data-options="iconCls:'icon-cancel'">删除</div>
    </div>
</div>

<div id="sys_org_AddDialog" class="easyui-dialog" title="新增"
     style="width:800px;height:400px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        sys_org_Add();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_org_AddDialog').dialog('close');
                    }
                }]
            ">
    <jsp:include page="add.jsp" flush="true"/>
</div>

<div id="sys_org_UpdateDialog" class="easyui-dialog" title="修改"
     style="width:800px;height:400px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                       sys_org_Update();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_org_UpdateDialog').dialog('close');
                    }
                }]
            ">
    <jsp:include page="update.jsp" flush="true"/>
</div>

<div id="sys_org_DetailsDialog" class="easyui-dialog" title="查看"
     style="width:800px;height:400px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        $('#sys_org_DetailsDialog').dialog('close');
                    }
                }]
            ">
    <jsp:include page="details.jsp" flush="true"/>
</div>