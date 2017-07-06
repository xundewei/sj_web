<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<div id="pub_select_org_Dialog" class="easyui-dialog" title="选择组织机构" style="width:500px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        pub_select_org_Dialog_OK();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        sys.dialog.closeReferenceOrgDialog();
                    }
                }]
            ">
    <ul  class="easyui-tree" id="pub_org_tree"></ul> 
</div>

<div id="pub_select_menu_Dialog" class="easyui-dialog" title="选择菜单" style="width:500px;height:350px;padding:10px"
     data-options="
                closed: true,
                modal: true,
                buttons: [{
                    text:'确定',
                    iconCls:'icon-ok',
                    handler:function(){
                        pub_select_menu_Dialog_OK();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancel',
                    handler:function(){
                        close_memu_RemoteDialog();
                    }
                }]
            ">
    <ul  class="easyui-tree" id="pub_menu_tree"></ul> 
</div>


<script type="text/javascript">
var nodeId; 
    $(function () {
        var isMulti = ${multiSelect};
        $('#pub_org_tree').tree({
            url: 'system/reference/loadorgtree',
            lines: true,
            animate:true,
            checkbox: isMulti,
            onClick: function (node) {
            	nodeId=node.id;
            }
        });
    });
    
    $(function () {
        var isMulti = ${multiSelect};
        $('#pub_menu_tree').tree({
            url: 'system/reference/loadorgtree',
            lines: true,
            animate:true,
            checkbox: isMulti,
            onClick: function (node) {
            	nodeId=node.id;
            }
        });
    });

    //确定按钮动作
    function pub_select_org_Dialog_OK() {
        var data = [];
        var $tree = $('#pub_org_tree');
        var ischeck = $tree.tree("options").checkbox;

        if (ischeck) {
            var checkedNodes = $tree.tree("getChecked");
            if (checkedNodes.length==0) {
                hlg.dialog.showInfo("请选择一个组织机构！");
                return;
            }
            for (var i = 0; i < checkedNodes.length; i++) {
                var checkedNode = checkedNodes[i];
                var cnode = {};
                cnode.id = checkedNode.id;
                cnode.name = checkedNode.text;
                data[i] = cnode;
            }
        } else {
            var selectednodes = $tree.tree("getSelected");
            if (!selectednodes) {
                hlg.dialog.showInfo("请选择一个组织机构！");
                return;
            }

            var node = {};
            node.id = selectednodes.id;
            node.name = selectednodes.text;
            data[0] = node;
        }

        //传递数据回去
        close_org_RemoteDialog("pub_select_org_Dialog", data);
    }
    
  //关闭组织机构选择对话框，并传回数据（没有数据，不传即可）
    function close_org_RemoteDialog (data) {
        sys.dialog.close_RemoteDialog("pub_select_org_Dialog", data);
    };
    
  //确定按钮动作
    function pub_select_menu_Dialog_OK() {
        var data = [];
        var $tree = $('#pub_menu_tree');
        var ischeck = $tree.tree("options").checkbox;

        if (ischeck) {
            var checkedNodes = $tree.tree("getChecked");
            if (checkedNodes.length==0) {
                hlg.dialog.showInfo("请选择一个组织机构！");
                return;
            }
            for (var i = 0; i < checkedNodes.length; i++) {
                var checkedNode = checkedNodes[i];
                var cnode = {};
                cnode.id = checkedNode.id;
                cnode.name = checkedNode.text;
                data[i] = cnode;
            }
        } else {
            var selectednodes = $tree.tree("getSelected");
            if (!selectednodes) {
                hlg.dialog.showInfo("请选择一个菜单！");
                return;
            }

            var node = {};
            node.id = selectednodes.id;
            node.name = selectednodes.text;
            data[0] = node;
        }

        //传递数据回去
        close_memu_RemoteDialog("pub_select_menu_Dialog", data);
    }
  //关闭组织机构选择对话框，并传回数据（没有数据，不传即可）
    function close_memu_RemoteDialog (data) {
        sys.dialog.close_RemoteDialog("pub_select_menu_Dialog", data);
    };


</script>