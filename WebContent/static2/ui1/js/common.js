(function ($) {
    $gps = $.gps = ({

        //根据页面上预定义的对话框id弹出此对话框，如果找不到此id对应的对话框，那么新增一个
        //请勿大量新增对话框，而应该将常用的都封装起来，如：统一定义“orgdialog”作为
        //组织结构对话框
        openDialog: function (dId, dTitle, dHeight, dWidth, dUrl, callback, isModel) {
            var ddiv = document.getElementById(dId);
            ;
            if (ddiv == null) {
                var dContent = "<div id='" + dId + "'></div>";
                //dContent += "<input type='hidden' id='" + dId + "_data' />";
                $("Body").append(dContent);
            }
            var dQueryId = "#" + dId;
            //dUrl += "?" + dData;
            $.ajax({
                type: "post",
                url: dUrl,
                dataType: "html",
                beforeSend: function (XMLHttpRequest) {
                },
                success: function (context, textStatus) {
                  // alert("5");
                    var d = dialog({
                        title: 'message',
                        content: context
                    });
                    d.showModal();
                },
                complete: function (XMLHttpRequest, textStatus) {

                },
                error: function () {


                }
            });
        },
        //显示用户选择对话框,multiSelect:是否多选；callback:回调，function callback(data)
        showJxsDialog: function (multiSelect, callback) {
            var dTitle = "选择用户";
            var dHeight = 470;
            var dWidth = 700;
            var dUrl = "../jxs/queryshow";
            var dData = "multiSelect=";
            if (multiSelect == "")
                dData += "0";
            if (multiSelect == true)
                dData += "1";
            $gps.openDialog("_jxsDialog", dTitle, dHeight, dWidth, dUrl, callback, true);
        }
    });

})(jQuery);










