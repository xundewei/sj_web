var appMap = new HashTable();
;
(function ($) {
    //ajax全局配置：调用不使用缓存；超时时间：20秒
    $.ajaxSetup({
        cache: false,
        timeout: 20000,
        beforeSend: function (XMLHttpRequest) {
            layer.load();
        },
        complete: function (XMLHttpRequest, textStatus) {
            layer.closeAll('loading');
            //解决easyui ajax超时跳转问题
            if (textStatus == "parsererror") {
                $.messager.alert('提示信息', "登陆超时！请重新登陆！", 'info', function () {
                    window.location.href = 'login';
                });
            } else if (textStatus == "error") {
                $.messager.alert('提示信息', "请求失败！请稍后再试", 'info');
            }
        }
    });

    //easyui grid全局设置
    jQuery.extend(jQuery.fn.datagrid.defaults, {
        loadMsg: "",
        width: 'auto',
        height: 'auto',
        nowrap: false,
        fitColumns: true,    //适应列宽
        rownumbers: true,   //是否显示行号
        singleSelect: true,  //是否单选
        pagination: true,    //是否显示分页
        pageSize: 15,        //每页行数
        pageList: [10, 15, 20],   //是否显示一个每页行数的下拉选择框，“[]”中设置选择项
        onLoadSuccess: function (data) {
            if (data.rows.length == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td width="' + body.width() + '" style="height: 25px; text-align: center;" colspan="6">没有数据</td></tr>');
            }
        }
    });

    //easyui pager全局设置
    jQuery.extend(jQuery.fn.pagination.defaults, {
        layout: ['list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh']
    });

})(jQuery);

var hlg = {};
hlg.main = {};
hlg.dialog = {};
hlg.ajax = {};
hlg.util = {};
hlg.grid = {};
hlg.treegrid = {};
hlg.form = {};

//==============功能函数======================
hlg.util.isEmptyString = function (str) {
    if (str.replace(/(^\s*)|(\s*$)/g, "").length == 0) {
        return true;
    }
    return false;
};

//==============主界面======================
hlg.main.init = function () {
    clockon();//本地时钟
    initLeftMenu();
    initTopBar();
    setPageTop();
    //initLayout();
    setTabMode();
    $("#main_mypage").trigger("click");
};

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

    //$("#tabs").tabs({
    //    onSelect: function (title, index) {
    //        //resetGridSize();
    //        var key = getTabKey(title);
    //        setAjaxHeader(title, key);
    //        var url = getTabUrl(key);
    //
    //        //alert(key);
    //        //alert(url);
    //        //console.log("onSelect:" + title + " " + key);
    //    },
    //    onUpdate: function (title, index) {
    //        //resetGridSize();
    //        var key = getTabKey(title);
    //        setAjaxHeader(title, key);
    //        //console.log("onUpdate:" + title + " " + key);
    //    }
    //});
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

    //var loading = "<div class='loading'><div class='tabloading'>页面正在加载中，请稍候......</div></div>";
    //
    //var tab = $tabs.tabs('getTab', title);
    //$tabs.tabs('update', {
    //    tab: tab,
    //    options: {
    //        content: loading
    //    }
    //});

    $.ajax({
        type: "get",
        url: url,
        dataType: "html",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (context, textStatus) {
            //var tab = $tabs.tabs('getTab', title);
            $tabs.tabs('update', {
                tab: tab,
                options: {
                    content: context
                }
            });
        }
    });
}

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

//初始化主页顶部工具栏
function initTopBar() {
    $("#main_changepwd").click(function () {
        var id = $("#main_userid").val();
        hlg.dialog.openRemoteDialog("sys_user_ChangeDialog", "system/public/p/" + id, true);
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

//初始化业务菜单点击事件
function initLeftMenu() {
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
            hlg.main.addTab(title, href, key);
        } else {
            hlg.main.openContent(href);
        }
    });
}

var layoutTimer = null;
function initLayout() {
    var resizeTimer = null;
    $(window).resize(function () {
        if (resizeTimer)
            clearTimeout(resizeTimer);
        resizeTimer = setTimeout("resetGridSize()", 1000);
    }); //resize事件延迟300毫秒执行
}

//以Div方式创建Tab
hlg.main.addTab = function (title, url, key) {
    var $tabs = $('#tabs');

    if ($tabs.tabs('exists', title)) {
        $tabs.tabs('select', title);

        return;
    }

    //easyui的原生加载速度太慢，换成jquery的ajax调用
    //$tabs.tabs('add', {
    //    title: title,
    //    href: url,
    //    loadingMessage: "加载中，请稍候……",
    //    closable: true
    //});
    //var loading = "<div class='loading'><div class='tabloading'>页面正在加载中，请稍候......</div></div>";
    //
    //$tabs.tabs('add', {
    //    title: title,
    //    content: loading,
    //    closable: true
    //});

    $.ajax({
        type: "get",
        url: url,
        dataType: "html",
        //headers: {"X-Request-Tab-Token": title},
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (context, textStatus) {
            $tabs.tabs('add', {
                title: title,
                content: context,
                closable: true
            });

            //var tab = $tabs.tabs('getTab', title);
            //tab.attr("title", key);
            //$tabs.tabs('update', {
            //    tab: tab,
            //    options: {
            //        content: context
            //    }
            //});
            $(".place").hide();
        }
    });
};

hlg.main.openContent = function (url) {
    $.ajax({
        type: "get",
        url: url,
        dataType: "html",
        //headers: {"X-Request-Tab-Token": title},
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function (context, textStatus) {
            $("#content").html(context);
            $.parser.parse("#content");
        }
        //,
        //error: function (jqXHR, textStatus, errorThrow) {
        //    var context = "无法加载页面，请检查网络是否正常。";
        //    $("#content").html(context);
        //    //getErrorTab2(title, url);
        //    //var tab = $tabs.tabs('getTab', title);
        //    //$tabs.tabs('update', {
        //    //    tab: tab,
        //    //    options: {
        //    //        content: jqXHR.responseText
        //    //    }
        //    //});
        //}
    });
};

//更换EasyUI主题的方法
hlg.main.changeTheme = function (themeName) {
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr('href', href);
    var $iframe = $('iframe');
    if ($iframe.length > 0) {
        for (var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }
};

//==============grid========================
//设置Grid的分页样式为现代风格
hlg.grid.setPagerStyle = function (grid) {
    $(grid).datagrid('getPager').pagination({
        layout: ['list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh']
    });
};

//根据查询对象，进行后台数据查询
hlg.grid.query = function (grid, queryObj) {
    $(grid).datagrid('options').queryParams = queryObj;
    $(grid).datagrid('load');
};

//清除Grid上已有的查询参数
hlg.grid.clearQueryParams = function (grid) {
    $(grid).datagrid('options').queryParams = null;
};

//加载Grid
hlg.grid.load = function (grid) {
    $(grid).datagrid('load');
};

//重新加载Grid
hlg.grid.reload = function (grid) {
    $(grid).datagrid('reload');
};

//获取Grid被选中的行
hlg.grid.getSelectRow = function (grid) {
    return $(grid).datagrid('getSelected');
};

hlg.grid.buildToolbarAdd = function (func) {
    return {
        text: '新增',
        iconCls: 'icon-add',
        handler: function () {
            func();
        }
    };
};

hlg.grid.buildToolbarUpdate = function (func) {
    return {
        text: '修改',
        iconCls: 'icon-edit',
        handler: function () {
            func();
        }
    };
};

hlg.grid.buildToolbarView = function (func) {
    return {
        text: '查看',
        iconCls: 'icon-redo',
        handler: function () {
            func();
        }
    };
};

hlg.grid.buildToolbarDelete = function (func) {
    return {
        text: '删除',
        iconCls: 'icon-cancel',
        handler: function () {
            func();
        }
    };
};

//==============treegrid========================
//根据查询对象，进行后台数据查询
hlg.treegrid.query = function (grid, queryObj) {
    $(grid).treegrid('options').queryParams = queryObj;
    $(grid).treegrid('load');
};

//清除Grid上已有的查询参数
hlg.treegrid.clearQueryParams = function (grid) {
    $(grid).treegrid('options').queryParams = null;
};

//重新加载TreeGrid
hlg.treegrid.reload = function (grid) {
    $(grid).treegrid('reload');
};

//==============ajax========================
//从后台获取一个对象实体，url需要包含id信息
hlg.ajax.getEntity = function (url, callback) {
    $.ajax({
        type: "get",
        url: url,
        dataType: "json",
        success: function (context, textStatus) {
            if (context.success) {
                if (context.data != null && callback != null)
                    callback(context.data);
            } else {
                //2.前后台通讯正常，但是后台逻辑发生错误，不能返回正确的数据
                //alert(context.Message);
                hlg.dialog.showInfo(context.message);
            }
        }
    });
};

//保存对象到后台，适用于add/update
hlg.ajax.saveEntity = function (url, entity, callback) {
    var jsonEntity = JSON.stringify(entity);
    $.ajax({
        type: "post",
        url: url,
        data: jsonEntity,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (context, textStatus) {
            //if (context.Result == "OK") {
            if (context.success) {
                //hlg.dialog.showSlideMessage("保存成功!");
                layer.msg("保存成功!");
                if (callback != null)
                    callback();
            } else {
                //2.前后台通讯正常，但是后台逻辑发生错误，不能返回正确的数据
                //alert(context.Message);
                hlg.dialog.showInfo(context.message);
            }
        }
    });
};

//删除一个对象实体，url需要包含id信息
hlg.ajax.deleteEntity = function (url, callback) {
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        success: function (context, textStatus) {
            if (context.success) {
                //hlg.dialog.showSlideMessage("删除成功!");
                layer.msg("删除成功!");
                if (callback != null)
                    callback();
            } else {
                //2.前后台通讯正常，但是后台逻辑发生错误，不能返回正确的数据
                hlg.dialog.showInfo(context.message);
            }
        }
    });
};

//==============easyui form========================
//清除form
hlg.form.clear = function (form) {
    $(form).form("clear");
};

//从指定url加载数据到form中, callback回调中可以对获取的data进行操作，比如checkbox无法自动load，可以在这里手工load
hlg.form.load = function (form, url, callback) {
    hlg.ajax.getEntity(url, function (data) {
        $(form).form("load", data);
        if (callback)
            callback(data);
    });
};

//获取form序列化后的对象
hlg.form.serialize = function (form) {
    return $(form).serializeJSON();
};

//保存form所有字段到后台
hlg.form.save = function (form, url, callback) {
    var entity = $(form).serializeJSON();
    hlg.ajax.saveEntity(url, entity, callback);
};

//==============dialog======================
//普通对话框封装，使用Easyui实现
//无图标，自定义标题
hlg.dialog.showMessage = function (title, msg) {
    $.messager.alert(title, msg);
};

//错误图标
hlg.dialog.showError = function (msg) {
    $.messager.alert('错误', msg, 'error');
};

//信息图标
hlg.dialog.showInfo = function (msg) {
    $.messager.alert('提示', msg, 'info');
};

//警告图标
hlg.dialog.showWarning = function (msg) {
    $.messager.alert('警告', msg, 'warning');
};

//选择“是/否”对话框
hlg.dialog.showConfirm = function (msg, callback) {
    $.messager.confirm("确认", msg, function (r) {
        if (r) {
            callback();
        }
    });
};

//右下角滑动提示
hlg.dialog.showSlideMessage = function (msg) {
    $.messager.show({
        title: '提示',
        msg: msg,
        timeout: 3000,
        showType: 'slide'
    });
};

//进度条
hlg.dialog.showProcess = function (isShow, msg) {
    var title = "提示";
    if (!isShow) {
        $.messager.progress('close');
        return;
    }
    var win = $.messager.progress({
        title: title,
        text: msg,
        interval: 50
    });
};

//加载服务器端定义的一个对话框(即并未在当前网页预定义)，并显示出来
hlg.dialog.openRemoteDialog = function (dId, dUrl, isModel, callback) {
    var divId = dId + "_div";
    var $divId = "#" + divId;
    var ddiv = document.getElementById(divId);
    ;
    if (ddiv == null) {
        var dContent = "<div id='" + divId + "'></div>";
        $("body").append(dContent);
    } else {
        $($divId).empty();
    }

    var dQueryId = "#" + dId;
    $.ajax({
        type: "get",
        url: dUrl,
        dataType: "html",
        success: function (context, textStatus) {
            $($divId).html(context);
            $.parser.parse($divId);
            $(dQueryId).dialog({
                modal: isModel,
                onClose: function (event, ui) {
                    var data = appMap.getValue(dId);
                    appMap.remove(dId);

                    //console.info("callback:" + data);
                    if (callback != null && data != null) {
                        callback(data);
                    }

                    $(dQueryId).dialog("destroy");
                    $(dQueryId).remove();
                    //console.info("remove dialog:" + dId);
                }

            });

            $(dQueryId).dialog("open");
        }
    });

};

//在关闭服务器对话框前，准备好要返回的数据，一般不需要特意调用，直接使用closeRemoteDialog即可
hlg.dialog.passRemoteDialogData = function (dId, data) {
    //console.info("pass dialog data:" + dId + " data:" + data);
    if (data) {
        appMap.add(dId, data);
    }
};

//关闭通过openRemoteDialog方法打开的指定id的窗口，并传回数据（没有数据，不传即可）
hlg.dialog.closeRemoteDialog = function (dId, data) {
    hlg.dialog.passRemoteDialogData(dId, data);
    var dQueryId = "#" + dId;
    $(dQueryId).dialog("close");
};

//打开组织机构选择对话框，multiSelect=true代表多选
hlg.dialog.openOrgaDialog = function (multiSelect, callback) {
    hlg.dialog.openRemoteDialog("pub_select_orga_Dialog", "system/public/showorgadialog?multi=" + multiSelect, true, callback);
};

//关闭组织机构选择对话框，并传回数据（没有数据，不传即可）
hlg.dialog.closeOrgaDialog = function (data) {
    hlg.dialog.closeRemoteDialog("pub_select_orga_Dialog", data);
};

//打开菜单选择对话框，multiSelect=true代表多选
hlg.dialog.openMenuDialog = function (multiSelect, callback) {
    hlg.dialog.openRemoteDialog("pub_select_menu_Dialog", "system/public/showmenudialog?multi=" + multiSelect, true, callback);
};

//关闭菜单选择对话框，并传回数据（没有数据，不传即可）
hlg.dialog.closeMenuDialog = function (data) {
    hlg.dialog.closeRemoteDialog("pub_select_menu_Dialog", data);
};

//打开用户选择对话框，multiSelect=true代表多选
hlg.dialog.openUserDialog = function (multiSelect, callback) {
    hlg.dialog.openRemoteDialog("pub_select_user_Dialog", "system/public/showuserdialog?multi=" + multiSelect, true, callback);
};

//关闭用户选择对话框，并传回数据（没有数据，不传即可）
hlg.dialog.closeUserDialog = function (data) {
    hlg.dialog.closeRemoteDialog("pub_select_user_Dialog", data);
};

//显示一个加载进度
hlg.dialog.showLoading = function(){
    layer.load();
};

//关闭加载进度
hlg.dialog.hideLoading = function(){
    layer.closeAll('loading');
};


//------------------------------------------------------
//HashTable的伪实现
function HashTable() {
    var size = 0;
    var entry = new Object();

    this.add = function (key, value) {
        if (!this.containsKey(key)) {
            size++;
        }
        entry[key] = value;
    };

    this.getValue = function (key) {
        return this.containsKey(key) ? entry[key] : null;
    };

    this.remove = function (key) {
        if (this.containsKey(key) && (delete entry[key])) {
            size--;
        }
    };

    this.containsKey = function (key) {
        return (key in entry);
    };

    this.containsValue = function (value) {
        for (var prop in entry) {
            if (entry[prop] == value) {
                return true;
            }
        }
        return false;
    };

    this.getValues = function () {
        var values = new Array();
        for (var prop in entry) {
            values.push(entry[prop]);
        }
        return values;
    };

    this.getKeys = function () {
        var keys = new Array();
        for (var prop in entry) {
            keys.push(prop);
        }
        return keys;
    };

    this.getSize = function () {
        return size;
    };

    this.clear = function () {
        size = 0;
        entry = new Object();
    };
}