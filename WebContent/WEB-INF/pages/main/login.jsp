<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=10"/>
    <title>欢迎登录三伽财团管理系统</title>
    <link href="${ctx}/ui/css/login.css" rel="stylesheet" type="text/css"/>
    <script language="JavaScript" src="${ctx}/lib/jquery/jquery-1.11.2.min.js"></script>
    <script src="${ctx}/ui/js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function () {
            $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            $(window).resize(function () {
                $('.loginbox').css({'position': 'absolute', 'left': ($(window).width() - 692) / 2});
            })
        });

        var btn = document.getElementById("main_jump");
        if (btn) {
            $("#main_jump").trigger("click");
        }
    </script>

</head>

<body class="login_body">

<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>

<div class="logintop">
    <span>欢迎登录三伽财团管理系统</span>
    <ul>
        <li><a href="#">帮助</a></li>
        <li><a href="#">关于</a></li>
    </ul>
</div>

<div class="loginbody">

    <span class="systemlogo"></span>

    <div class="loginbox">
        <form id="loginform" name="loginform" action="" method="post" onsubmit="return check();">
            <ul>
                <li><input name="username" id="username" type="text" class="loginuser" placeholder="用户名" value=""/></li>
                <li><input name="password" id="password" type="password" class="loginpwd" placeholder="密码" value=""/></li>
                <li><input type="submit" class="btn_flat" value="登录"/>
                    <label><a href="#">忘记密码？</a></label>
                    <br/><br/><span id="errmsg" style="color:red">${errorMessage}</span>
                </li>
            </ul>
        </form>

    </div>

</div>

<div class="loginbm">版权所有 2017 <a href="http://www.ihuolong.com/">欢迎登录三伽财团管理系统</a></div>
<script type="text/javascript">
    var txtUser = document.getElementById("username");
    var txtPass = document.getElementById("password");
    txtUser.focus();

    var errmsg = document.getElementById("errmsg");

    function check() {
        if (txtUser.value == "" && txtPass.value == "") {
            errmsg.style.display = "block";
            errmsg.innerHTML = "提示信息：登录用户和密码不能为空！";
            txtUser.focus();
            return false;
        }

        if (txtUser.value == "") {
            errmsg.style.display = "block";
            errmsg.innerHTML = "提示信息：登录用户不能为空！";
            txtUser.focus();
            return false;
        }

        if (txtPass.value == "") {
            errmsg.style.display = "block";
            errmsg.innerHTML = "提示信息：登录密码不能为空！";
            txtPass.focus();
            return false;
        }

        document.getElementById("errmsg").innerText = "";
        return true;
    }
</script>

</body>

</html>
