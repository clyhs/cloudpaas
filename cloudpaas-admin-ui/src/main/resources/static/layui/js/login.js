layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api"
}).use([ 'element', 'layer' ,'api','form'], function() {
	var $ = layui.jquery, element = layui.element, layer = layui.layer,
		form = layui.form,
	    api = layui.api;


	
	// 登录过期的时候，跳出ifram框架
	if (top.location != self.location) top.location = self.location;
	
	// 粒子线条背景
	$(document).ready(function(){
	    $('.layui-container').particleground({
	        dotColor:'#5cbdaa',
	        lineColor:'#5cbdaa'
	    });
	});
	
	// 进行登录操作
	form.on('submit(login)', function (obj) {
	    data = obj.field;
	    if (data.username == '') {
	        layer.msg('用户名不能为空');
	        return false;
	    }
	    if (data.password == '') {
	        layer.msg('密码不能为空');
	        return false;
	    }
	    if (data.captcha == '') {
	        layer.msg('验证码不能为空');
	        return false;
	    }
	    //layer.msg(JSON.stringify(data));
	    /*
	    $.ajax({
            type:"POST",
            url:$config.context+"/login.json",
            dataType:"json",
            contentType:"application/json",
            
            data:JSON.stringify(data),
            success:function(res){
                layer.close(loadIndex);
                if(res.code==0){
                	layer.msg('登录成功', function () {
            	        window.location = $config.context+'/main/index.html';
            	    });
                }else{
                    layer.msg(res.msg);
                }
            }
        });*/
	    
	    var params = {"username":data.username,"password":data.password,"captcha":data.captcha};
	    
	    $.post($config.context+"/login.json",params,function(res,status){
	    	
            if(res.code==0){
            	layer.msg('登录成功', function () {
        	        window.location = $config.context+'/main/index.html';
        	    });
            }else{
            	
                layer.msg(res.code+','+res.msg);
                $("#captchaPic").click();
                
            }
		});
	    return false;
	});
	
	$("#captchaPic").click(function () {
		this.src = $config.context+"/kaptcha?"+Math.random();
	});
	
});