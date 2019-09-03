layui.config({
	base : $config.context+"/static/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api"
}).use(['form','layer','jquery','api'], function(){
    var form = layui.form,
        layer = layui.layer,
        api = layui.api,
        $ = layui.jquery;
    
    form.on("submit(editSubmit)",function(data){
        if(data.field.id == null){
            layer.msg("ID不存在");
            return false;
        }
       
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        //layer.alert('编辑行：<br>' + JSON.stringify(data))
        
        $.ajax({
            type:"PUT",
            url:api.putRoleEditUrl,
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(data.field),
            success:function(res){
                layer.close(loadIndex);
                if(res.code==0){
                    parent.layer.msg("编辑成功！",{time:1000},function(){
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