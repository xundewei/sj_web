/**
 * 
 * 此JS 用于所有参照的使用的加载
 * 依赖app.js
 * 
 * 
 * **/

var sys = {};
sys.dialog = {};
//打开组织机构选择对话框，multiSelect=true代表多选
sys.dialog.openReferenceOrgDialog = function (multiSelect, callback) {
    sys.dialog.open_RemoteDialog("pub_select_org_Dialog", "system/reference/showorgdialog?multi=" + multiSelect, true, callback);
};
sys.dialog.openReferenceMenuDialog = function (multiSelect, callback) {
    sys.dialog.open_RemoteDialog("pub_select_menu_Dialog", "system/reference/showorgdialog?multi=" + multiSelect, true, callback);
};

sys.dialog.openReferenceDialog = function (dId, dUrl, callback) {
    sys.dialog.open_RemoteDialog(dId, dUrl, true, callback);
};


//关闭组织机构选择对话框，并传回数据（没有数据，不传即可）
sys.dialog.closeReferenceOrgDialog = function (data) {
    sys.dialog.close_RemoteDialog("pub_select_org_Dialog", data);
};



//关闭通过openRemoteDialog方法打开的指定id的窗口，并传回数据（没有数据，不传即可）
sys.dialog.close_RemoteDialog = function (dId, data) {
  sys.dialog.pass_RemoteDialogData(dId, data);
  var dQueryId = "#" + dId;
  $(dQueryId).dialog("close");
};


//在关闭服务器对话框前，准备好要返回的数据，一般不需要特意调用，直接使用closeRemoteDialog即可
sys.dialog.pass_RemoteDialogData = function (dId, data) {
  //console.info("pass dialog data:" + dId + " data:" + data);
  if (data) {
      appMap.add(dId, data);
  }
};

//加载服务器端定义的一个对话框(即并未在当前网页预定义)，并显示出来
sys.dialog.open_RemoteDialog = function (dId, dUrl, isModel, callback) {
    var divId = dId + "_div";
    var $divId = "#" + divId;
    var ddiv = document.getElementById(divId);
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
