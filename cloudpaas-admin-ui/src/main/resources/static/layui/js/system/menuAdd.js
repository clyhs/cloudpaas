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
    
    form.on('submit(addMenu)',function(data){
        
        var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
        
        data.field.createTime = new Date();
        
        $.ajax({
            type:"POST",
            url:api.postMenuAddUrl,
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