<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/taglibs.jsp"%>
<div class="place">
    <ul class="placeul">
        <li><a href="#">帮助</a></li>
    </ul>
</div>
<div style="padding: 10px;">
    <div style="padding: 10px;border: 1px solid #ddd;background-color: beige">
        <div class="hlg-title">系统需要用什么浏览器？</div>
        <div>
            IE浏览器需要9以上版本，否则无法获得良好的用户体验。建议安装以下浏览器来登录系统：
            <ul>
                <li>
                    <img src="${ctx}/images/help/Chrome.png" alt="Chrome" />谷歌浏览器Chrome
                    <a href="http://www.baidu.com/baidu?wd=%B9%C8%B8%E8%E4%AF%C0%C0%C6%F7&tn=monline_dg" target="_blank">下载地址1</a>
                    <a href="http://rj.baidu.com/soft/detail/14744.html?ald" target="_blank">下载地址2</a>
                </li>
                <li>
                    <img src="${ctx}/images/help/Firefox.png" alt="Firefox" />火狐浏览器Firefox
                    <a href="http://www.mozillaonline.com/" target="_blank">下载地址1</a>
                </li>
                <br/>
                <li>
                    或者您可以尝试使用腾讯QQ浏览器、360浏览器、猎豹浏览器等现代浏览器。
                </li>
            </ul>

            如果您的IE浏览器版本过低，可以选择升级您的IE浏览器： <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">升级IE浏览器</a><br />
            <div style="color:#F00;">
            有些网站和旧系统（如银行、网银或者需要支持ActiveX插件的系统），需要IE8以下的浏览器。为了兼容起见，建议您可以暂不升级IE浏览器，而是使用上面列出的浏览器来运行系统。
            </div>
        </div>
        <br/>
        <div class="hlg-title">上传按钮为什么不起作用？</div>
        <div>
            上传需要浏览器开启Flash支持，请根据自己的浏览器来点击以下链接(在对应的浏览器中点击)：<br/>
            谷歌浏览器Chrome：默认已开启Flash支持<br />
            火狐浏览器Firefox：<a target="_blank" href="https://support.mozilla.org/zh-CN/kb/%E5%AE%89%E8%A3%85%20Flash%20%E6%8F%92%E4%BB%B6">安装Flash插件</a><br />
            IE浏览器：<a target="_blank" href="http://get.adobe.com/cn/flashplayer/">安装Flash插件</a><br />
        </div>
        <br />
        <div class="hlg-title">如何清除浏览器缓存？</div>
        <div>
            当本系统发布更新后，可能需要清除旧的缓存，请按以下方法来清除。<br />
            谷歌浏览器Chrome：菜单->设置->显示高级设置->隐私设置->清除浏览数据：<br />
            <img src="${ctx}/images/help/chrome-cache.png" alt="Chrome" /><br /><br />
            火狐浏览器Firefox：菜单->工具->选项->高级->网络->网络内容缓存：<br />
            <img src="${ctx}/images/help/ff-cache.png" alt="FireFox" /><br /><br />
            IE浏览器:菜单->工具->Internet选项->常规->浏览历史记录->删除:<br/>
            在打开的新窗口中取消勾选“保留收藏夹网站数据”，勾选其它选框，点击“删除”即可。<br />
            <img src="${ctx}/images/help/ie-cache.png" alt="IE" /><br /><br />
        </div>
    </div>
</div>
