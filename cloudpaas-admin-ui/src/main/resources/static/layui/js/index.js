layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api",
	adminui : "adminui"
}).use([ 'element', 'layer', 'adminui' ,'api'], function() {
	var $ = layui.jquery, element = layui.element, layer = layui.layer,
	    api = layui.api;

	adminui.init($config.context+"/menu/tree.json");

	 
	$('.login-out').on("click", function() {
		var loadIndex = layer.load(2, {
	         shade: [0.3, '#333']
	     });
		layer.msg('退出登录成功', {time:1000},function() {
			layer.close(loadIndex);
			window.location = $config.context+'/loginout.html';
		});
	});
});