<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" language="java" %>

<div class="place">
    <ul class="placeul">
        <li><a href="#">控制面板</a></li>
    </ul>
</div>

<div style="padding: 26px;">
    <form id="sys_userconfig_form">
        <input type="hidden" id="sys_userconfig_id" name="pkSysUser"/>

        <div class="hlg-title">个人信息</div>
        个人信息
        <br/><br/>

        <div class="hlg-title">页面展现模式</div>
        <input type="radio" name="tabmode" value="false"/>单页面模式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="radio" name="tabmode" value="true"/>多标签模式<br/>
        退出后重新登录生效
        <br/><br/>

        <div class="hlg-title">整体界面主题皮肤</div>
        选择皮肤：
        <select name="theme" style="width:100px;">
            <option value="default">经典</option>
            <option value="bootstrap">现代</option>
            <option value="black">黑色</option>
            <option value="gray">灰色</option>
            <option value="metro">摩登</option>
            <option value="metro-blue">摩登-blue</option>
            <option value="metro-gray">摩登-gray</option>
            <option value="metro-green">摩登-green</option>
            <option value="metro-orange">摩登-orange</option>
            <option value="metro-red">摩登-red</option>
            <option value="material">material</option>
            <option value="ui-cupertino">ui-cupertino</option>
            <option value="ui-dark-hive">ui-dark-hive</option>
            <option value="ui-pepper-grinder">ui-pepper-grinder</option>
            <option value="ui-sunny">ui-sunny</option>
        </select>
        <br/><br/>

    </form>
    <a id="sys_userconfig_ok" href="#" class="easyui-linkbutton" style="width:100px;"
       data-options="iconCls:'icon-ok'" onclick="sys_userconfig_ok()">确定</a>
</div>

<script type="text/javascript">
    var currenttheme;
    $(function () {
        var url = "system/user/getconfig";
        hlg.ajax.getEntity(url, function (data) {
            $("#sys_userconfig_form").form("load", data);
            currenttheme = data.theme;
        });
    });

    function sys_userconfig_ok() {
        var entity = $("#sys_userconfig_form").serializeJSON();

        var url = "system/user/updateconfig";
        hlg.ajax.saveEntity(url, entity, function () {
            if (currenttheme != entity.theme)
                hlg.main.changeTheme(entity.theme);
        });
    }

</script>