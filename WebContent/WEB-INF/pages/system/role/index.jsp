<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript">
    //配置Grid1-角色列表
    function sys_roles_ConfigGrid() {
        $("#sys_roles_grid").datagrid({
            url: "system/roles/list",
            columns: [[
                {field: 'pkSysRole', title: '主键', width: 100, sortable: true, hidden: true},
                {field: 'rolecode', title: '角色编码', width: 100, sortable: true},
                {field: 'rolename', title: '角色名称', width: 100, align: 'right', sortable: true}
            ]],
            sortName: "rolecode",     //默认的排序字段
            sortOrder: "asc",   //默认的排序方向
            onClickRow: function (rowIndex, rowData) {
                sys_roles_QueryGrid2(rowData);
            },
            toolbar: sys_roles_Toolbar
        });
    }

    //Grid的顶部工具栏
    var sys_roles_Toolbar = [{
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            sys_roles_openAddDialog();
        }
    }, {
        text: '修改',
        iconCls: 'icon-edit',
        handler: function () {
            var row = hlg.grid.getSelectRow("#sys_roles_grid");
            sys_roles_openUpdateDialog(row);
        }
    }, {
        text: '查看',
        iconCls: 'icon-redo',
        handler: function () {
            var row = hlg.grid.getSelectRow("#sys_roles_grid");
            sys_roles_OpenDetailsDialog(row);
        }
    },
        '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                var row = hlg.grid.getSelectRow("#sys_roles_grid");
                sys_roles_Delete(row);
            }
        }];

    //配置查询、重置按钮动作
    function sys_roles_ConfigQuerybar() {
        //查询按钮动作
        $("#sys_roles_btnQuery").click(function () {
            var queryObj = {};
            queryObj.name = $("#sys_roles_name").val();
            hlg.grid.query("#sys_roles_grid", queryObj);
        });

        //重置按钮动作
        $("#sys_roles_btnReset").click(function () {
            hlg.form.clear("#sys_roles_form");
            hlg.grid.clearQueryParams("#sys_roles_grid");
            hlg.grid.load("#sys_roles_grid");
        });
    }

    //点击角色，查询对应的菜单权限数据
    function sys_roles_QueryGrid2(row) {
        if (!row) {
            return;
        }

        var $rowid = row.pkSysRole;
        if ($rowid == null) {
            return;
        }

        var queryObj = {};
        queryObj.roleid = $rowid;
        hlg.treegrid.query("#sys_roles_grid2", queryObj);

        $("#sys_roles_grid2").treegrid("getPanel").panel("setTitle","[" + row.name + "]权限列表");
    }

    //进行删除
    function sys_roles_Delete(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.pkSysRole;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var msg = "确定删除角色[" + row.rolecode + "]吗？";
        hlg.dialog.showConfirm(msg, function () {
            var url = "system/roles/delete/" + $rowid;
            hlg.ajax.deleteEntity(url, function () {
                hlg.grid.reload("#sys_roles_grid");
                hlg.treegrid.reload("#sys_roles_grid2");
                $("#sys_roles_grid2").treegrid("getPanel").panel("setTitle","权限列表");
            });
        });
    }

    //设置Grid的高度充满
    function sys_roles_SetHeight() {
        var height = $("#main_leftmenudiv").css("height");
        var h = parseInt(height) - topheight - 54 + 12;

        $("#sys_roles_sidebar").css('height', h);

        $("#sys_roles_grid").datagrid('resize', {
            height: h
        });

        $("#sys_roles_grid2").datagrid('resize', {
            height: h
        });

    }

    //配置Grid2-权限菜单列表
    function sys_roles_ConfigGrid2() {
        $("#sys_roles_grid2").treegrid({
            loadMsg: "",
            url: 'system/menu/listrolemenu',
            idField: 'menucode',
            treeField: 'menuname',
            columns: [[
                {field: 'pkSysMenu', title: '编号', width: 100, sortable: false, hidden: true},
                {field: 'menucode', title: 'RoleId', width: 100, sortable: false, hidden: true},
                {field: 'menuid', title: '菜单编号', width: 100, sortable: false, hidden: true},
                {field: 'menuname', title: '菜单名称', width: 120, sortable: false},
                {field: 'displayorder', title: '显示顺序', width: 40, sortable: false, hidden: true},
                {
                    field: 'level', title: '菜单级别', width: 50, formatter: function (value, row, index) {
                    if (value == 0) {
                        return "主菜单";
                    } else {
                        return "子菜单";
                    }
                }
                },
                {field: 'parentid', title: 'ParentID', width: 120, hidden: true},
                {
                    field: 'c1',
                    title: '个人数据',
                    width: 60,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, row, index) {
                        if (row.level == "0")
                            return "";

                        return "<input type='checkbox' checked='checked' onclick='return false;'/>";

                    }
                },
                {
                    field: 'currentorgaflag',
                    title: '个人所在部门数据',
                    width: 90,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, row, index) {
                        if (row.level == "0")
                            return "";

                        if(value)
                            return "<input type='checkbox' checked='checked' onclick='return false;'/>";
                        else
                            return "";

                    }
                },
                {
                    field: 'customuserflag',
                    title: '指定人员数据',
                    width: 80,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, row, index) {
                        if (row.level == "0")
                            return "";

                        if(value)
                            return "<input type='checkbox' checked='checked' onclick='return false;'/>";
                        else
                            return "";

                    }
                },
                {
                    field: 'customorgaflag',
                    title: '指定部门数据',
                    width: 80,
                    align: 'center',
                    sortable: false,
                    formatter: function (value, row, index) {
                        if (row.level == "0")
                            return "";

                        if(value)
                            return "<input type='checkbox' checked='checked' onclick='return false;'/>";
                        else
                            return "";

                    }
                },
                {
                    field: 'alldataflag',
                    title: '所有数据',
                    width: 80,
                    align: 'right',
                    sortable: false,
                    formatter: function (value, row, index) {
                        if (row.level == "0")
                            return "";

                        if(value)
                            return "<input type='checkbox' checked='checked' onclick='return false;'/>";
                        else
                            return "";

                    }
                }
            ]],
            fitColumns: true,
            rownumbers: true,
            lines: true,
            singleSelect: true,
            sortName: "displayorder",     //默认的排序字段
            sortOrder: "asc",   //默认的排序方向
            onDblClickRow: function (row) {
                if (row.level == 0) {
                    return;
                }
                sys_roles_openDataDialog(row);
            },
            toolbar: sys_roles_Toolbar2
        });
    }

    //Grid2的顶部工具栏
    var sys_roles_Toolbar2 = [{
        text: '分配菜单(功能)',
        iconCls: 'icon-add',
        handler: function () {
            var row = hlg.grid.getSelectRow("#sys_roles_grid");
            sys_roles_openMenuSelectDialog(row);
        }
    }, '-', {
        text: '设置数据范围',
        iconCls: 'icon-edit',
        handler: function () {
            var row = hlg.grid.getSelectRow("#sys_roles_grid2");
            sys_roles_openDataDialog(row);
        }
    }];

    //当前页面初始化动作
    $(function () {
        sys_roles_ConfigQuerybar();
        sys_roles_ConfigGrid();
        sys_roles_ConfigGrid2();
        sys_roles_SetHeight();
    });
</script>

<div class="place">
    <ul class="placeul">
        <li><a href="#">角色管理</a></li>
    </ul>
</div>

<%--<div style="padding: 5px; border: 1px solid #ddd;">--%>
<div style="padding: 6px;">
    <div id="sys_roles_querybar" style="height:54px;">
        <div class="hlg-search">条件查询</div>
        <div style="padding: 5px;">
            <form id="sys_roles_form">
                名称：<input id="sys_roles_name" name="sys_roles_name" type="text"/>
                <a id="sys_roles_btnQuery" href="#" class="easyui-linkbutton"
                   data-options="iconCls:'icon-search'">查询</a>
                <a id="sys_roles_btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
            </form>
        </div>
    </div>

    <%--左右布局--%>
    <div style="margin:0 auto; width:100%;">
        <div id="sys_roles_sidebar" style=" float:left; width:290px; height:500px;padding-right:5px;">
            <table title="角色列表" id="sys_roles_grid" class="hlg-grid"></table>
        </div>
        <div>
            <table title="角色权限列表" id="sys_roles_grid2" class="hlg-grid"></table>
        </div>
    </div>

</div>

<jsp:include page="add.jsp" flush="true"/>
<jsp:include page="update.jsp" flush="true"/>
<jsp:include page="details.jsp" flush="true"/>
<jsp:include page="menudialog.jsp" flush="true"/>
<jsp:include page="datadialog.jsp" flush="true"/>

