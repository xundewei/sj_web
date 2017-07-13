/**
 * Created by Administrator on 2014-10-31.
 */
/**
 *改变图片
 */
function alterImg(url) {
    var verify = document.getElementById('codeimage');
    verify.setAttribute('src', url + Math.random());
}
/**
 显示锁车级别介绍*
 */
function radioShowTip() {
    var target = document.getElementById("PopupMenu");
    target.style.display = "block";
}
/**
 *隐藏锁车级别介绍
 */
function radioHidTip() {
    var target = document.getElementById("PopupMenu");
    target.style.display = "none";
}
/**
 *验证验证码输入是否正确
 */
function check() {
    $('#imgForm').submit();
}
/**
 * 验证验证码是否正确
 */
function checkbtn() {
    $("#imgForm").ajaxSubmit(success, error);
}

/**
 * 选择要解锁车的客户
 */
function selectCus() {
    var r = document.getElementsByName("chk");
    var arrayObj = new Array();
    for (var i = 0; i < r.length; i++) {
        if (r[i].checked) {
            arrayObj.push(r[i].value);// 将一个或多个新元素添加到数组结尾，并返回数组新长度
        }
    }
    return arrayObj;
}
/**
 * 得到单选按钮的值
 * @param name
 * @returns {*}
 */
function getRadioValue(name) {
    var radioes = document.getElementsByName(name);
    for (var i = 0; i < radioes.length; i++) {
        if (radioes[i].checked) {
            return radioes[i].value;
        }
    }
    return false;
}

/**
 *全选
 */
function selectAll() {
    var chkAll = document.getElementById("chkAll");
    var r = document.getElementsByName("chk");
    if (chkAll.checked == true) {
        alert("hehe");
        for (var i = 0; i < r.length; i++) {
            r[i].checked = true;
        }
    }
    else {
        alert(chkAll.checked);
        for (var i = 0; i < r.length; i++) {
            r[i].checked = false;
        }
    }
}