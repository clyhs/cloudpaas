var $, tab, dataStr, layer;
layui.config({
	base : "/static/js/modules/"
}).extend({
	"bodyTab" : "bodyTab"
});

layui.use(['bodyTab','form','element','layer','jquery'],function(){
	
});

//打开新窗口
function addTab(_this){
	tab.tabAdd(_this);
}