<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=10"/>
    <title>三伽财团信息管理系统</title>
    <link id="easyuiTheme" rel="stylesheet" type="text/css" href="${ctx}/lib/easyui/themes/${theme}/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/lib/easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css?v=20150608"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/app.css?v=20150608"/>

</head>
<body class="easyui-layout">

<div id="main_loading">
    <img style="vertical-align:middle;" src="${ctx}/images/main/loading0.gif" alt=""/>系统加载中，请稍候
</div>
<script type="text/javascript" src="${ctx}/lib/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/lib/json2/json2.min.js"></script>
<script type="text/javascript" src="${ctx}/lib/easyui/jquery.easyui.min.js?v=1.5.2"></script>
<script type="text/javascript" src="${ctx}/lib/easyui/easyui-lang-zh_CN.js?v=1.5.2"></script>
<script type="text/javascript" src="${ctx}/js/app.js?v=20170222"></script>
<script type="text/javascript" src="${ctx}/js/reference.js?v=20170222"></script>
<script type="text/javascript" src="${ctx}/lib/echarts/echarts-all.js?v=2.2.7"></script>
<script type="text/javascript" src="${ctx}/lib/layer/layer.js?v=1.9.3"></script>
<script type="text/javascript" src="${ctx}/lib/form/jquery.serializejson.js?v=2.6.1"></script>

<div data-options="region:'north',border:false,split:false" style="height: 85px">
    <div id="top">
        <div id="logo">
             <span>${groupname}</span> 
        </div>
        <div class="fl_r">
            <img alt="" src="${ctx}/images/main/pic_1.jpg"/></div>
    </div>
    <div id="nav">
        <div class="yh">
            欢迎您， <label id="lblSignIn" style="margin-right:15px">${shirouser.username}</label>
            当前时间：<span id="bgclock"></span>
            <input type="hidden" id="main_userid" value="${shirouser.pkSysUser}"/>
            <input type="hidden" id="main_usercode" value="${shirouser.usercode}"/>
            <input type="hidden" id="main_username" value="${shirouser.username}"/>
        </div>
        <div id="menu">
            <ul>
                <li><a href="#" id="main_mypage" class="hlg-menu" data-url="main/home">我的首页</a></li>
                <li><a href="#" id="main_changepwd">修改密码</a></li>
                <li><a href="#" id="main_control" class="hlg-menu" data-url="system/user/config">控制面板</a></li>
                <li><a href="#" id="main_help" class="hlg-menu" data-url="main/help">帮助</a></li>
                <li><a href="#" id="main_exit">安全退出</a></li>
            </ul>
            <input hidden="hidden" style="display:none" type="button" id="main_jump" value="jump"
                   onclick="window.location = 'login'"/>
        </div>
    </div>
</div>
<div id="main_leftmenudiv"
     data-options="region:'west',border:true,split:true" title="导航菜单"
     style="width: 200px;">
    <c:import url="/main/leftmenu"/>
</div>
<div data-options="region:'center'">
    <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
    </div>
    <div id="content">
    </div>
</div>
<div id="mm" class="easyui-menu" style="width: 120px">
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
        main_show();
    };
</script>

<script type="text/javascript">
  var tabmode = ${tabmode};
    //执行初始化动作
    $(function () {
        clockon();//本地时钟
        initTopButton();
        initTopBar();
        setPageTop();
        setTabMode();
        $("#main_mypage").trigger("click");
    });
    
    
  //本地时钟
    function clockon() {
        var now = new Date();
        var year = now.getFullYear(); //getFullYear getYear
        var month = now.getMonth();
        var date = now.getDate();
        var day = now.getDay();
        var hour = now.getHours();
        var minu = now.getMinutes();
        var sec = now.getSeconds();
        var week;
        month = month + 1;
        if (month < 10) month = "0" + month;
        if (date < 10) date = "0" + date;
        if (hour < 10) hour = "0" + hour;
        if (minu < 10) minu = "0" + minu;
        if (sec < 10) sec = "0" + sec;
        var arr_week = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        week = arr_week[day];
        var time = "";
        time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;
        $("#bgclock").html(time);
        var timer = setTimeout("clockon()", 200);
    }
  
  
    //初始化TOP功能
    function initTopButton() {
        $(document).on("click", ".hlg-menu", function () {
            var $this = $(this);
            var href = $this.attr('data-url');
            var key = $this.attr('data-key');
            var title = $this.text();
            if (href == null)
                return;
            if (href == "")
                return;
            if (tabmode) {
                addTab(title, href, key);
            } else {
            	openContent(href);
            }
        });
    }
    
    function addTab (title, url, key) {
        var $tabs = $('#tabs');
        if ($tabs.tabs('exists', title)) {
            $tabs.tabs('select', title);
            return;
        }
        $.ajax({
            type: "get",
            url: url,
            dataType: "html",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (context, textStatus) {
                $tabs.tabs('add', {
                    title: title,
                    content: context,
                    closable: true
                });
                $(".place").hide();
            }
        });
    };
        
     function openContent (url) {
            $.ajax({
                type: "get",
                url: url,
                dataType: "html",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (context, textStatus) {
                    $("#content").html(context);
                    $.parser.parse("#content");
                }
            });
        };
    //需要弹出对话框
    function initTopBar() {
        $("#main_changepwd").click(function () {
            var id = $("#main_userid").val();
            sys.dialog.open_RemoteDialog("sys_user_ChangeDialog", "system/user/change/" + id, true);
        });

        $("#main_exit").click(function () {
            var msg = "您确定要退出吗？";
            hlg.dialog.showConfirm(msg, function () {
                window.location.href = "logout";
            });
        });

        $("#main_test").click(function () {
            hlg.dialog.openUserDialog(true, function (data) {
                //console.log(data);
            })
        });
    }
    
    var topheight = 28;
  //设置每个内容页顶部高度的全局变量，用于进行Grid的高度计算
    function setPageTop() {
        //tab标签的高度
        var tabheight = 28;
        //所有页面顶部的标题路径高度，用于页面计算。具体的值由app.css中的.place决定，这里需要与.place定义的高度保持一致
        var pathHeight = 28;

        topheight = tabmode ? tabheight : pathHeight;
    }

  //设置使用单页面模式，还是多Tab页模式
    function setTabMode() {
        if (tabmode) {
            initTab();
            initTabMenu();
            $("#content").hide();
            $(".place").hide();
        } else {
            $("#tabs").hide();
            $("#mm").hide();
        }
    }
  
  //添加Tab页菜单和双击事件
    function initTab() {
        $(document).on("dblclick", ".tabs-inner", function () {
            var subtitle = $(this).children(".tabs-closable").text();
            $('#tabs').tabs('close', subtitle);
        });

        $(document).on("contextmenu", ".tabs-inner", function (e) {
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });

            var subtitle = $(this).children(".tabs-closable").text();

            $('#mm').data("currtab", subtitle);
            $('#tabs').tabs('select', subtitle);
            return false;
        });
    }
  
  //初始化Tab页菜单处理事件
    function initTabMenu() {
        var home = "我的首页";
        //刷新
        $('#mm-tabupdate').click(function () {
            var $tabs = $('#tabs');
            var currTab = $tabs.tabs('getSelected');
            var title = currTab.panel('options').title;
            var url = "";

            $(".hlgmenu").each(function () {
                var t = $(this).text();
                if (t == title) {
                    url = $(this).attr("data-url");
                    return false;
                }
            });

            if (title == home)
                url = "main/home";

            if (url != "") {
                refreshTab(title, url);
            }
        });

        //关闭当前
        $('#mm-tabclose').click(function () {
            var currtabTitle = $('#mm').data("currtab");
            $('#tabs').tabs('close', currtabTitle);
        });

        //全部关闭
        $('#mm-tabcloseall').click(function () {
            $('.tabs-inner span').each(function (i, n) {
                var t = $(n).text();
                if (t != home) {
                    $('#tabs').tabs('close', t);
                }
            });
        });

        //关闭除当前之外的tab
        $('#mm-tabcloseother').click(function () {
            var prevall = $('.tabs-selected').prevAll();
            var nextall = $('.tabs-selected').nextAll();
            if (prevall.length > 0) {
                prevall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    if (t != home) {
                        $('#tabs').tabs('close', t);
                    }
                });
            }
            if (nextall.length > 0) {
                nextall.each(function (i, n) {
                    var t = $('a:eq(0) span', $(n)).text();
                    if (t != home) {
                        $('#tabs').tabs('close', t);
                    }
                });
            }
            return false;
        });

        //关闭当前右侧的tab
        $('#mm-tabcloseright').click(function () {
            var nextall = $('.tabs-selected').nextAll();
            if (nextall.length == 0) {
                return false;
            }
            nextall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                if (t != home) {
                    $('#tabs').tabs('close', t);
                }
            });
            return false;
        });

        //关闭当前左侧的tab
        $('#mm-tabcloseleft').click(function () {
            var prevall = $('.tabs-selected').prevAll();
            if (prevall.length == 0) {
                return false;
            }
            prevall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                if (t != home) {
                    $('#tabs').tabs('close', t);
                }
            });
            return false;
        });

        //退出
        $("#mm-exit").click(function () {
            $('#mm').menu('hide');
        });
    }
  
  //刷新标签
    function refreshTab(title, url) {
        var $tabs = $('#tabs');
        if (!$tabs.tabs('exists', title)) {
            return;
        }
        $.ajax({
            type: "get",
            url: url,
            dataType: "html",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (context, textStatus) {
                $tabs.tabs('update', {
                    tab: tab,
                    options: {
                        content: context
                    }
                });
            }
        });
    }
</script>
</body>
</html>

