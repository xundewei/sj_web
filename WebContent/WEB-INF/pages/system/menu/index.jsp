<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript">
    function sys_menu_ConfigGrid() {
        $("#sys_menu_grid").treegrid({
            loadMsg: "",
            url: 'system/menu/list',
            idField: 'menucode',
            treeField: 'menucode',
            columns: [[
                {field: 'pkSysMenu', title: '菜单ID', width: 60, align: 'right', hidden: true},
                {field: 'menucode', title: '菜单编码', width: 80},
                {field: 'menuname', title: '菜单名称', width: 80},
                {field: 'menukey', title: '菜单标识', width: 40, sortable: true},
                {field: 'displayorder', title: '显示顺序', width: 40, sortable: true},
                {
                    field: 'lev', title: '菜单级别', width: 40, formatter: function (value, row, index) {
                    if (value == 1) {
                        return "主菜单";
                    } else {
                        return "子菜单";
                    }
                }
                },
                {field: 'url', title: 'URL', width: 120},
                {field: 'parentcode', title: 'parentcode', width: 120, hidden: true},
                {
                    field: 'enableflag', title: '是否可用', width: 80, formatter: function (value, row, index) {
                    if (value) {
                        return "可用";
                    } else {
                        return "不可用";
                    }
                }, styler: function (value, row, index) {
                    if (!value) {
                        return 'color:red;';
                    }
                    return 'color:green;';
                }
                },
                {
                    field: 'menutype', title: '菜单类型', width: 80, formatter: function (value, row, index) {
                        if (value==0) {
                            return "物业专用";
                        }else if(value==1){
                            return "平台专用"
                        }else{
                            return ""
                        }
                    }, styler: function (value, row, index) {
                        if (value==0) {
                            return 'color:green;';
                        }else if(value==1){
                            return 'color:red';
                        }else{
                            return '';
                        }
                    }
                }
            ]],
            fitColumns: true,
            rownumbers: true,
            lines: true,
            singleSelect: true,
            sortName: "displayorder",
            sortOrder: "asc",
            onDblClickRow: function () {
                var row = $('#sys_menu_grid').treegrid('getSelected');
                sys_menu_OpenDetailsDialog(row);
            },
            toolbar: "#sys_menu_toolbar",
            onContextMenu: sys_menu_OnContextMenu
        });
    }

    //右键菜单
    function sys_menu_OnContextMenu(e, row) {
        e.preventDefault();
        $(this).treegrid('select', row.id);
        $('#sys_menu_mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    }

    //配置Grid工具栏
    function sys_menu_ConfigToolbar() {
        $("#sys_menu_btnAddMain").click(function () {
            sys_menu_AddMain();
        });

        $("#sys_menu_btnAddChild").click(function () {
            sys_menu_AddSub();
        });

        $("#sys_menu_btnEdit").click(function () {
            var row = $('#sys_menu_grid').treegrid('getSelected');
            sys_menu_openUpdateDialog(row);
        });

        $("#sys_menu_btnView").click(function () {
            var row = $('#sys_menu_grid').treegrid('getSelected');
            sys_menu_OpenDetailsDialog(row);
        });

        $("#sys_menu_btnDelete").click(function () {
            var row = $('#sys_menu_grid').treegrid('getSelected');
            sys_menu_Delete(row);
        });
    }

    //打开新增主菜单对话框
    function sys_menu_AddMain() {
        sys_menu_openAddDialog("main");
    }

    //打开新增子菜单对话框
    function sys_menu_AddSub() {
        var row = $('#sys_menu_grid').treegrid('getSelected');
        if (row == null) {
            hlg.dialog.showInfo("请先选择主菜单！");
            return;
        }

        var parentcode = row.id;
        var parentname = row.displayname;
        var parentlevel = row.level;
//        if (row.level == 0) {
//            parentcode = row.id;
//            parentname = row.displayname;
//        } else {
//            hlg.dialog.showInfo("请先选择主菜单！");
//            return;
//        }

        sys_menu_openAddDialog("child", parentcode, parentname, parentlevel);
    }

    //打开新增对话框
    function sys_menu_openAddDialog(flag, parentcode, parentname, parentlevel) {
        //清除对话框中的显示值，如果有的话
        sys_menu_a_form_reset();
        sys_menu_a_form_init(flag, parentcode, parentname, parentlevel);
        $('#sys_menu_AddDialog').dialog('open');
    }

    //进行新增
    function sys_menu_Add() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_menu_a_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/menu/add";
        var entity = sys_menu_a_form_getValues();
        hlg.ajax.saveEntity(url, entity, function () {
            $('#sys_menu_AddDialog').dialog('close');
            hlg.treegrid.reload("#sys_menu_grid");
        });
    }

    //打开修改对话框
    function sys_menu_openUpdateDialog(row) {
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
        sys_menu_u_form_reset();
        $('#sys_menu_UpdateDialog').dialog('open');

        var url = "system/menu/" + $rowid;
        hlg.ajax.getEntity(url, function (data) {
            //从后台获取数据后，填充到对话框中
            sys_menu_u_form_setValues(data);
        });
    }

    //点右键，选择编辑
    function sys_menu_RightUpdate() {
        var row = $('#sys_menu_grid').treegrid('getSelected');
        sys_menu_openUpdateDialog(row);
    }

    //进行修改
    function sys_menu_Update() {
        //在提交保存动作时进行一次校验，校验失败直接返回，不再进行保存动作
        var isValid = $("#sys_menu_u_form").form('validate');
        if (!isValid)
            return;

        //验证通过后保存数据到后台
        var url = "system/menu/update";
        var entity = sys_menu_u_form_getValues();
        hlg.ajax.saveEntity(url, entity, function () {
            $('#sys_menu_UpdateDialog').dialog('close');
            hlg.treegrid.reload("#sys_menu_grid");
        });
    }

    //打开查看对话框，查看某条数据
    function sys_menu_OpenDetailsDialog(row) {
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
        sys_menu_d_form_reset();

        $('#sys_menu_DetailsDialog').dialog('open');

        var url = "system/menu/" + $rowid;
        hlg.ajax.getEntity(url, function (data) {
            //从后台获取数据后，填充到对话框中
            sys_menu_d_form_setValues(data);
        });
    }

    //右键删除
    function sys_menu_RightDelete() {
        var row = $('#sys_menu_grid').treegrid('getSelected');
        sys_menu_Delete(row);
    }

    //进行删除
    function sys_menu_Delete(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.id;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var msg = "确定删除菜单[" + row.name + "]吗？";
        hlg.dialog.showConfirm(msg, function () {
            var url = "system/menu/delete/" + $rowid;
            hlg.ajax.deleteEntity(url, function () {
                hlg.treegrid.reload("#sys_menu_grid");
            });
        });
    }

    //设置Grid的高度充满
    function sys_orga_SetHeight() {
        var height = $("#main_leftmenudiv").css("height");
        var h = parseInt(height);

        $("#sys_menu_grid").datagrid('resize', {
            height: h - topheight + 12
        });

    }

    //当前页面初始化动作
    $(function () {
        sys_menu_ConfigGrid();
        sys_menu_ConfigToolbar();
        sys_orga_SetHeight();
    });

</script>
<div class="place">
    <ul class="placeul">
        <li><a href="#">菜单管理</a></li>
    </ul>
</div>

<%--<div style="padding: 5px; border: 1px solid #ddd;">--%>
<div style="padding: 6px;">
    <div id="sys_menu_toolbar">
        <a id="sys_menu_btnAddMain" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增主菜单</a>
        <a id="sys_menu_btnAddChild" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增子菜单</a>
        <a id="sys_menu_btnEdit" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a>
        <a id="sys_menu_btnView" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">查看</a>
        <a id="sys_menu_btnDelete" href="#" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'icon-cancel'">删除</a>
    </div>
    <div>
        <table id="sys_menu_grid" class="hlg-grid"></table>
    </div>

    <div id="sys_menu_mm" class="easyui-menu" style="width:120px;">
        <div onclick="sys_menu_AddSub()" data-options="iconCls:'icon-add'">新增子菜单</div>
        <div onclick="sys_menu_RightUpdate()" data-options="iconCls:'icon-edit'">修改</div>
        <div onclick="sys_menu_RightDelete()" data-options="iconCls:'icon-cancel'">删除</div>
    </div>
</div>

<div id="sys_menu_AddDialog" class="easyui-dialog" title="新增"
     style="width:800px;height:400px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        sys_menu_Add();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_menu_AddDialog').dialog('close');
                    }
                }]
            ">
    <jsp:include page="add.jsp" flush="true"/>
</div>

<div id="sys_menu_UpdateDialog" class="easyui-dialog" title="修改"
     style="width:800px;height:400px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                       sys_menu_Update();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_menu_UpdateDialog').dialog('close');
                    }
                }]
            ">
    <jsp:include page="update.jsp" flush="true"/>
</div>

<div id="sys_menu_DetailsDialog" class="easyui-dialog" title="查看"
     style="width:800px;height:400px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        $('#sys_menu_DetailsDialog').dialog('close');
                    }
                }]
            ">
    <jsp:include page="details.jsp" flush="true"/>
</div>