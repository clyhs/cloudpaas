layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api",
	layuimini : "layuimini"
}).use([ 'element', 'layer', 'layuimini' ], function() {
	var $ = layui.jquery, element = layui.element, layer = layui.layer;

	layuimini.init('');

	$('.login-out').on("click", function() {
		layer.msg('退出登录成功', function() {
			window.location = '#';
		});
	});
});