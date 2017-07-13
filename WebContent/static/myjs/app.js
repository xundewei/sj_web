var appMap = new HashTable();
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





var layoutTimer = null;
function initLayout() {
    var resizeTimer = null;
    $(window).resize(function () {
        if (resizeTimer)
            clearTimeout(resizeTimer);
        resizeTimer = setTimeout("resetGridSize()", 1000);
    }); //resize事件延迟300毫秒执行
}




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