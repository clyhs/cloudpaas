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
    
    form.on('submit(addSubmit)',function(data){
        
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        
        data.field.createTime = new Date();
        //layer.alert('编辑行：<br>' + JSON.stringify(data))
        if(undefined !== data.field.enabled && null != data.field.enabled){
            data.field.enabled = 1;
        }else{
            data.field.enabled = 0;
        }
        
        $.ajax({
            type:"POST",
            url:api.postRouterAddUrl,
            dataType:"json",
            contentType:"application/json",
            
            data:JSON.stringify(data.field),
            success:function(res){
                layer.close(loadIndex);
                if(res.code==0){
                    parent.layer.msg("添加成功！",{time:1000},function(){
                        //刷新父页面
                        parent.location.reload();
                    });
                }else{
                    layer.msg(res.message);
                }
            }
        });
        return false;
    });
});