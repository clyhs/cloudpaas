layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api",
	layuimini : "layuimini"
}).use([ 'element', 'layer', 'layuimini' ,'api'], function() {
	var $ = layui.jquery, element = layui.element, layer = layui.layer,
	    api = layui.api;

	layuimini.init(api.getIndexMenuTreeUrl);

	$('.login-out').on("click", function() {
		layer.msg('退出登录成功', function() {
			window.location = '#';
		});
	});
});