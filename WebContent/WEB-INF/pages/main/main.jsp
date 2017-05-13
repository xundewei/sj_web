<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=10"/>
    <title>三伽财团信息管理系统</title>
    <%--<link rel="stylesheet" type="text/css" href="${ctx}/lib/easyui/themes/default/easyui.css?v=1.4.2"/>--%>
    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="${ctx}/lib/easyui/themes/${theme}/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css?v=20150608"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/app.css?v=20150608"/>

</head>
<body class="easyui-layout">

<div id="main_loading">
    <img style="vertical-align:middle;" src="${ctx}/images/main/loading0.gif" alt=""/>系统加载中，请稍候
</div>
<script type="text/javascript" src="${ctx}/lib/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/lib/json2/json2.min.js"></script>
<script type="text/javascript" src="${ctx}/lib/easyui/jquery.easyui.min.js?v=1.4.4"></script>
<script type="text/javascript" src="${ctx}/lib/easyui/easyui-lang-zh_CN.js?v=1.4.4"></script>
<script type="text/javascript" src="${ctx}/js/app.js?v=20150608"></script>
<script type="text/javascript" src="${ctx}/js/reference.js?v=20150608"></script>
<script type="text/javascript" src="${ctx}/lib/echarts/echarts-all.js?v=2.2.7"></script>
<script type="text/javascript" src="${ctx}/lib/layer/layer.js?v=1.9.3"></script>
<script type="text/javascript" src="${ctx}/lib/form/jquery.serializejson.js?v=2.6.1"></script>

<div data-options="region:'north',border:false" style="height: 85px">
    <div id="top">
        <div id="logo">
            <span>${groupname}</span>
        </div>
        <div class="fl_r">
            <img alt="" src="${ctx}/images/main/pic_1.jpg"/></div>
    </div>
    <div id="nav">
        <div class="yh">
            欢迎您， <label id="lblSignIn" style="margin-right:15px">${userDisplayName}</label>
            当前时间：<span id="bgclock"></span>
            <input type="hidden" id="main_userid" value="${userId}"/>
            <input type="hidden" id="main_username" value="${userDisplayName}"/>
            <input type="hidden" id="main_userloginname" value="${userLoginName}"/>
        </div>
        <div id="menu">
            <ul>
                <li><a href="#" id="main_mypage" class="hlg-menu" data-url="main/home">我的首页</a></li>
                <li><a href="#" id="main_changepwd">修改密码</a></li>
                <li><a href="#" id="main_control" class="hlg-menu" data-url="system/userconfig">控制面板</a></li>
                <li><a href="#" id="main_help" class="hlg-menu" data-url="main/help">帮助</a></li>
                <li><a href="#" id="main_exit">安全退出</a></li>
                <%--<li><a href="#" id="main_test">Test</a></li>--%>
            </ul>
            <input hidden="hidden" style="display:none" type="button" id="main_jump" value="jump"
                   onclick="window.location = 'login'"/>
        </div>
    </div>
</div>
<div id="main_leftmenudiv"
     data-options="region:'west',border:true,split:false, onCollapse:layoutCollapse, onExpand:layoutExpand" title="导航菜单"
     style="width: 200px;">
    <c:import url="/main/leftmenu"/>
</div>
<div data-options="region:'center'">
    <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
        <%--<div id="main_mypagediv" title="我的首页" style="padding: 10px">--%>
            <%--<c:import url="/main/home"/>--%>
        <%--</div>--%>
    </div>
    <div id="content">
        <%--<c:import url="/main/home"/>--%>
    </div>
</div>
<div id="mm" class="easyui-menu" style="width: 120px">
    <%--<div id="mm-tabupdate">刷新</div>--%>
    <div id="mm-tabclose">关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseother">关闭其他标签</div>
    <div id="mm-tabcloseall">关闭全部标签</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">关闭右侧标签</div>
    <div id="mm-tabcloseleft">关闭左侧标签</div>
</div>

<script type="text/javascript">
    function main_show() {
        $("#main_loading").fadeOut("normal", function () {
            $(this).remove();
        });
    }
    var main_delayTime;
    $.parser.onComplete = function () {
        //if (main_delayTime)
        //    clearTimeout(main_delayTime);
        //main_delayTime = setTimeout(main_show, 100);
        main_show();
    };
</script>

<script type="text/javascript">
    var tabmode = ${tabmode};

    //执行初始化动作
    $(function () {
        hlg.main.init();
    });

    //导航栏收起
    function layoutCollapse() {
        //console.log("Collapse");
        //var tabWidth = $("#tabs").tabs("options").width;
        //console.log(tabWidth);

        //var cw = parseInt(tabWidth);
        //$(".xygrid").each(function () {
        //    $(this).setGridWidth(cw-20);
        //});
        //resetGridSize();
    }

    //导航栏展开
    function layoutExpand() {
        //console.log("Expand");

        //var tabWidth = $("#tabs").tabs("options").width;
        //console.log(tabWidth);

        //$(".xygrid").each(function () {
        //    $(this).setGridWidth(tabWidth);
        //});
//        if (layoutTimer)
//            clearTimeout(layoutTimer);
//        layoutTimer = setTimeout("resetGridSize()", 1000);

    }


</script>
</body>
</html>

