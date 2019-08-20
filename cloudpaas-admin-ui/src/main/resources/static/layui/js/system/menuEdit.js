layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api"
}).use(['form','layer','jquery','api'], function(){
    var form = layui.form,
        layer = layui.layer,
        api = layui.api,
        $ = layui.jquery;
    
    
    //选择图标
    $("#selectIcon").on("click",function () {
        iconShow =layer.open({
            type: 2,
            title: '选择图标',
            shadeClose: true,
            content: $config.context+'/icon.html'
        });
        layer.full(iconShow);
    });
    
    form.on("submit(editMenu)",function(data){
        if(data.field.id == null){
            layer.msg("菜单ID不存在");
            return false;
        }
       
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        //layer.alert('编辑行：<br>' + JSON.stringify(data))
        
        if(undefined !== data.field.isShow && null != data.field.isShow){
            data.field.isShow = 1;
        }else{
            data.field.isShow = 0;
        }
        
        $.ajax({
            type:"PUT",
            url:api.putMenuEditUrl+data.field.id,
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data.field),
            success:function(res){
                layer.close(loadIndex);
                if(res.code==0){
                    parent.layer.msg("菜单编辑成功！",{time:1000},function(){
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message,{time:1000},function(){
                        //刷新本页面
                        location.reload();
                    });

                }
            }
        });
        return false;
    });
});