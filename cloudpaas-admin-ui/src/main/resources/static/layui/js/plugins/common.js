//定义模块
layui.define(['jquery',"layer"],function(exports) {
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;
    var obj = {
        //错误信息提示
        outErrorMsg: function (xmlHttpRequest) {
            // 状态码
            console.log(xmlHttpRequest.status);
            // 返回信息
            var jsonObj = $.parseJSON(xmlHttpRequest.responseText);
            console.log(jsonObj.msg);
            layer.msg(jsonObj.msg);
        }
    }
    //输出模块
    exports('common', obj);
});