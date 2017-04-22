/**
 * Created by Administrator on 2014/11/12.
 */
var overlays = [];
function setRange() {
    var overlaycomplete = function (e) {
        overlays.push(e.overlay);
        var hidTermid=document.getElementById("hidTermid").value;
        if(hidTermid=="")
        {
            xy.message.show("请选择客户！");
            return;
        }
        //这是获取矩形的经纬度
        // alert('坐标：' + e.overlay.getPosition().lng + ',' + e.overlay.getPosition().lat);
        //alert('所画的点个数：' + e.overlay.getPath().length);
        for (var i = 0; i < e.overlay.getPath().length; i++) {
           // $("#TextArea1").val(e.overlay.getPath()[i].lng+","+e.overlay.getPath()[i].lat+"#");
            document.getElementById("TextArea1").value+=e.overlay.getPath()[i].lng+","+e.overlay.getPath()[i].lat+"#"+"\r\n";
        }
    };
    var styleOptions = {
        strokeColor: "red",    //边线颜色。
        fillColor: "red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: true, //是否开启绘制模式
        enableDrawingTool: false, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
            scale: 0.8 //工具栏缩放比例
        },
        /*circleOptions: styleOptions, //圆的样式*/
        polylineOptions: styleOptions //线的样式
        /*   polygonOptions: styleOptions, //多边形的样式
         rectangleOptions: styleOptions //矩形的样式*/
    });
    //设置绘制模式
    drawingManager.setDrawingMode(BMAP_DRAWING_POLYLINE);
    //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    //alert(drawingManager.getDrawingMode());

}
function clearAll() {
    for (var i = 0; i < overlays.length; i++) {
        map.removeOverlay(overlays[i]);
    }
    overlays.length = 0;
    document.getElementById("TextArea1").value="";
}


