<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="sys_roles_select_menu_Dialog" class="easyui-dialog" title="选择菜单"
     style="width:500px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                toolbar: [{
                    text:'全选',
                    iconCls:'icon-ok',
                    handler:function(){
                        selectAll();
                    }
                },'-',{
                    text:'全不选',
                    iconCls:'icon-redo',
                    handler:function(){
                        unSelectAll();
                    }
                }],
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        sys_roles_select_menu_Dialog_OK();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        $('#sys_roles_select_menu_Dialog').dialog('close');
                    }
                }]
            ">
    <ul id="sys_roles_select_menu_tree"></ul>
    <input type="hidden" id="sys_roles_select_menu_roleid" />
</div>
<script type="text/javascript">
    //打开修改对话框
    function sys_roles_openMenuSelectDialog(row) {
        if (!row) {
            hlg.dialog.showInfo("请先在左侧角色列表中选择一个角色！");
            return;
        }

        var $rowid = row.pkSysRole;
        if ($rowid == null) {
            hlg.dialog.showInfo("请先在左侧角色列表中选择一个角色！");
            return;
        }

        $('#sys_roles_select_menu_Dialog').dialog('open');
        $("#sys_roles_select_menu_roleid").val($rowid);

        //配置菜单TreeGrid
        $('#sys_roles_select_menu_tree').tree({
            url: 'system/permmenu/loadmenutree?id=' + $rowid,
            lines: true,
            checkbox: true,
            cascadeCheck: false,
            onClick: function (node) {

            }
        });
    }

    //确定按钮动作
    function sys_roles_select_menu_Dialog_OK() {
        var roleid = $("#sys_roles_select_menu_roleid").val();
        var data = [];
        var $tree = $('#sys_roles_select_menu_tree');
        var ischeck = $tree.tree("options").checkbox;

        if (ischeck) {
            var checkedNodes = $tree.tree("getChecked");
            for (var i = 0; i < checkedNodes.length; i++) {
                var checkedNode = checkedNodes[i];
                var checkedObj = JSON.parse(checkedNode.attributes);

                var cnode = {};
                cnode.id = checkedNode.id;
                cnode.name = checkedNode.text;
                data[i] = cnode;
                //只返回新增的
//                if(!checkedObj.hasExist)
//                    data[i] = cnode;
            }
        } else {
            var selectednodes = $tree.tree("getSelected");
            var checkedObj2 = JSON.parse(selectednodes.attributes);
            var node = {};
            node.id = selectednodes.id;
            node.name = selectednodes.text;
            data[0] = node;
//            if(!checkedObj2.hasExist){
//                data[0] = node;
//            }
        }

        var dataObj = {};
        dataObj.roleid = roleid;
        dataObj.list = data;

        //传递数据回去
        sys_roles_select_menu_Dialog_update(dataObj);
    }

    function sys_roles_select_menu_Dialog_update(data){
        console.log(data);

        var url = "system/permmenu/update";
        hlg.ajax.saveEntity(url, data, function () {
            $('#sys_roles_select_menu_Dialog').dialog('close');
            hlg.treegrid.reload("#sys_roles_grid2");
        });

    }

    function selectAll(){
        var $tree = $("#sys_roles_select_menu_tree");
        var roots = $tree.tree("getRoots");

        selectAllChild($tree, roots, true);
    }

    function unSelectAll(){
        var $tree = $("#sys_roles_select_menu_tree");
        var roots = $tree.tree("getRoots");

        selectAllChild($tree, roots, false);
    }

    function selectAllChild(tree, roots, check){
        for(var i=0;i<roots.length;i++){
            var node = tree.tree('find', roots[i].id);//查找节点
            if(check)
                tree.tree("check", node.target);//将得到的节点选中
            else
                tree.tree("uncheck", node.target);//将得到的节点选中

            var childs = node.children;
            console.log(childs);
            selectAllChild(tree, childs, check);
        }
    }

</script>