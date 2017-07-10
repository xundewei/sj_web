<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<script type="text/javascript">
    //配置Grid
    function sys_user_ConfigGrid() {
        $("#sys_user_grid").datagrid({
            url: "system/user/list",
            columns: [[
                {field: 'pkSysUser', title: '用户ID', width: 80, sortable: true, hidden: true},
                {field: 'usercode', title: '用户编号', width: 80, sortable: true},
                {field: 'username', title: '用户名称', width: 80, sortable: true},
                {	field: 'sex', 
                	title: '性别', 
                	width: 80, 
                	sortable: true,
                	formatter: function (value, row, index) {
                        if (value=="01") {
                            return "男";
                        } else {
                            return "女";
                        }
                    }
                },
                {field: 'mobile', title: '手机', width: 80, sortable: false},
                {field: 'pkSysOrg', title: '机构ID', width: 160, align: 'right', sortable: false, hidden: true},
                {
                    field: 'flag',
                    title: '是否可用',
                    width: 50,
                    sortable: true,
                    formatter: function (value, row, index) {
                        if (value) {
                            return "可用";
                        } else {
                            return "不可用";
                        }
                    },
                    styler: function (value, row, index) {
                        if (value) {
                            return 'color:blue;';
                        } else{
                        	return 'color:red;';
                        }
                    }
                },
                {field: 'remark', title: '备注', width: 100, align: 'right', sortable: false}
            ]],
            sortName: "usercode",     //默认的排序字段
            sortOrder: "asc",   //默认的排序方向
            onDblClickRow: function (rowIndex, rowData) {
                sys_user_OpenDetailsDialog(rowData);
            },//双击事件
            toolbar: sys_user_toolbar    //关联的顶部Toolbar
        });
    }

    //Grid的顶部工具栏
    var sys_user_toolbar = [{
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            sys_user_openAddDialog();
        }
    }, {
        text: '修改',
        iconCls: 'icon-edit',
        handler: function () {
            var row = hlg.grid.getSelectRow("#sys_user_grid");
            sys_user_openUpdateDialog(row);
        }
    }, {
        text: '查看',
        iconCls: 'icon-redo',
        handler: function () {
            var row = hlg.grid.getSelectRow("#sys_user_grid");
            sys_user_OpenDetailsDialog(row);
        }
    },
        '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                var row = hlg.grid.getSelectRow("#sys_user_grid");
                sys_user_Delete(row);
            }
        },
        '-',{
            text: '重置密码',
            iconCls: 'icon-undo',
            handler: function () {
                var row = hlg.grid.getSelectRow("#sys_user_grid");
                sys_user_OpenResetDialog(row);
            }
        },
        '-', {
            text: '分配角色',
            iconCls: 'icon-lock',
            handler: function () {
                var row = hlg.grid.getSelectRow("#sys_user_grid");
                sys_user_Role(row);
            }
        }];

    //配置查询区域
    function sys_user_ConfigQuerybar() {
        //查询按钮动作
        $("#sys_user_btnQuery").click(function () {
            sys_user_doQuery();
        });

        //重置按钮动作
        $("#sys_user_btnReset").click(function () {
            $("#sys_user_name").prop("value", "");
            $("#sys_user_orgcode").val("");
            $("#sys_user_orgname").val("");
            $('#sys_user_tree1').tree("select", "");
            hlg.grid.clearQueryParams("#sys_user_grid");
            hlg.grid.load("#sys_user_grid");
        });
    }

    //配置组织树
    function sys_user_ConfigTree() {
        $('#sys_user_tree1').tree({
            url: 'system/reference/loadorgtree',
            lines: true,
            onClick: function (node) {
                $("#sys_user_orgcode").val("");
                $("#sys_user_orgname").val("");
                $("#sys_user_pk_sys_code").val("");
                $("#sys_user_orgcode").val(node.id);
                $("#sys_user_orgname").val(node.text);
                $("#sys_user_pk_sys_code").val(node.attributes);
                sys_user_doQuery();
            }
        });
    }

    //根据条件进行查询，查询结果填充到Grid
    function sys_user_doQuery() {
        var queryObj = {};
        queryObj.name = $("#sys_user_name").val();
        queryObj.orgaid = $("#sys_user_orgcode").val();
        if($("#sys_user_level").prop("checked")){
            queryObj.level = "1"
        }else{
            queryObj.level = "2";
        }

        hlg.grid.query("#sys_user_grid", queryObj);
    }

    //进行删除
    function sys_user_Delete(row) {
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.id;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var msg = "确定删除用户[" + row.displayname + "]吗？";
        hlg.dialog.showConfirm(msg, function () {
            var url = "system/user/delete/" + $rowid;
            hlg.ajax.deleteEntity(url, function () {
                hlg.grid.reload("#sys_user_grid");
            });
        });
    }

    //分配角色
    function sys_user_Role(row){
        if (!row) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var $rowid = row.pkSysUser;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先选择一条记录！");
            return;
        }

        var url = "system/user/setroles/" + $rowid;
        hlg.dialog.openRemoteDialog("sys_user_setRolesDialog",url, true);
    }

    //设置Tree、Grid的高度充满
    function sys_user_SetHeight(){
        var height = $("#main_leftmenudiv").css("height");
        var h = parseInt(height) - topheight - 54 + 12;

        $("#sys_user_sidebar").css('height', h);
        $("#sys_user_sidepanel").css('height', h);

        $("#sys_user_grid").datagrid('resize', {
            height: h
        });

    }

    //当前页面初始化动作
    $(function () {
        sys_user_ConfigQuerybar();
        sys_user_ConfigGrid();
        sys_user_ConfigTree();
        sys_user_SetHeight();
    });
</script>

<div class="place">
    <ul class="placeul">
        <li><a href="#">用户管理</a></li>
    </ul>
</div>

<%--<div style="padding: 5px; border: 1px solid #ddd;">--%>
<div style="padding: 6px;">

    <div id="sys_user_querybar" style="height:54px;">
        <div class="hlg-search">条件查询</div>
        <div style="padding: 5px;">
            <form id="sys_user_form">
                用户名称：<input id="sys_user_name" name="sys_user_name" type="text"/>
                <input type="hidden" id="sys_user_orgcode"/>
                <input type="hidden" id="sys_user_pk_sys_code"/>
                <input type="hidden" id="sys_user_orgname"/>
                <a id="sys_user_btnQuery" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
                <a id="sys_user_btnReset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">重置</a>
                <input type="checkbox" checked="checked" id="sys_user_level"/>只显示选中机构直属用户
            </form>
        </div>
    </div>

    <%--左右布局--%>
    <div style="margin:0 auto; width:100%;">
         <div id="sys_user_sidebar" style=" float:left; width:250px; height:500px;padding-right:5px;">
            <div id="sys_user_sidepanel" class="easyui-panel hlg-leftpanel" style="height:500px;"
                 data-options="fit:true" title="组织机构">
                <ul id="sys_user_tree1"></ul>
            </div>
        </div> 
        <div id="sys_user_content">
            <table id="sys_user_grid" ></table>
        </div>
    </div>
</div>

<jsp:include page="add.jsp" flush="true"/>
<jsp:include page="update.jsp" flush="true"/>
<jsp:include page="details.jsp" flush="true"/>
<jsp:include page="reset.jsp" flush="true"/>



