<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<style>

    /* 覆盖easyui-tree 原始的样式 */
    .tree-node {
        height: 24px;
        white-space: nowrap;
        cursor: pointer;
    }

    .tree-title {
        font-size: 12px;
    }

    .tree-node-selected {
        background-color: inherit;
        color: #000000;
    }

    .tree-file {
        background: url(images/main/Menu2.png) no-repeat 2px 1px;
        background-size: 12px;
    }
    .tree-folder {
        background: url(images/main/file_close.png);
        background-size: cover;
    }
    .tree-folder-open {
        background: url(images/main/file_open.png);
        background-size: cover;
    }


</style>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div id="main_leftmenu" class="easyui-accordion" data-options="fit:true,border:false">
    <c:set var="first" value="true"/>
    <c:forEach items="${leftmenu}" var="entry"><!-- 一级菜单集合-->
        <div title="${entry.displayname}" data-options="iconCls:'icon-menu',selected:${first}"><!-- 一级菜单名称 -->
            <%-- <c:forEach items="${entry.value}" var="menu"> --%>
            <!-- 根据没一级菜单找到相关子树 -->
              <%--   <ul class="easyui-tree" data-options="url:'tree/menutree/${menu.id}',method:'get',animate: true,loadFilter:myLoadFilter,onClick:treeNodeClick"></ul> --%>
    <%--         </c:forEach> --%>

        </div>
        <c:set var="first" value="false"/>
    </c:forEach>
</div>
<script type="text/javascript">

    function treeNodeClick(node) {
        /*判断是否是叶子节点*/
        if (!$(this).tree('isLeaf', node.target)) {

            $(this).tree('toggle', node.target);
            return;
        }

        var attributes = node.attributes;
        var obj = $.parseJSON(attributes);
        var url;
        var key;
        $.each(obj, function (k, v) {
            switch (k) {
                case 'url':
                    url = v;
                    break;
                case 'key':
                    key = v;
                    break;
            }
        });
        if (url == "" || url == null || url == undefined) {
            return;
        }
        var title = node.text;
        var tabmode = ${tabmode};

        if (tabmode) {
            hlg.main.addTab(title, url, key);
        } else {
            hlg.main.openContent(url);
        }


    }

    function myLoadFilter(data, parent) {
        /*var state = $.data(this, 'tree');

         function setData(){
         var serno = 1;
         var todo = [];
         for(var i=0; i<data.length; i++){
         todo.push(data[i]);
         }
         while(todo.length){
         var node = todo.shift();
         if (node.id == undefined){
         node.id = '_node_' + (serno++);
         }
         if (node.children){
         node.state = 'closed';
         node.children1 = node.children;
         node.children = undefined;
         todo = todo.concat(node.children1);
         }
         }
         state.tdata = data;
         }
         function find(id){
         var data = state.tdata;
         var cc = [data];
         while(cc.length){
         var c = cc.shift();
         for(var i=0; i<c.length; i++){
         var node = c[i];
         if (node.id == id){
         return node;
         } else if (node.children1){
         cc.push(node.children1);
         }
         }
         }
         return null;
         }

         setData();

         var t = $(this);
         var opts = t.tree('options');
         opts.onBeforeExpand = function(node){
         var n = find(node.id);
         if (n.children && n.children.length){return}
         if (n.children1){
         var filter = opts.loadFilter;
         opts.loadFilter = function(data){return data;};
         t.tree('append',{
         parent:node.target,
         data:n.children1
         });
         opts.loadFilter = filter;
         n.children = n.children1;
         }
         };*/
        return data;
    }
</script>