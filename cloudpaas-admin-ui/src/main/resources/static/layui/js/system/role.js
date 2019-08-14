layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api"
}).use([ 'element', 'layer' ,'api',"table","form"], function() {
	var $ = layui.jquery, element = layui.element, layer = layui.layer,
	    api = layui.api,
	    table = layui.table,
        form = layui.form;
	
	table.render({
        elem: '#roleTable',
        url: api.getRolePageUrl,
        cols: [[
            {type: "checkbox", width: 50, fixed: "left"},
            {field: 'id', width: 80, title: 'ID', sort: true},
            {field: 'code', width: 80, title: '角色代号'},
            {field: 'name', title: '角色名称', width: 150},
            {field: 'remark', minWidth: 50, title: '描述' },
            {title: '操作', minWidth: 50, templet: '#roleBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10, 15, 20, 25, 50, 100],
        limit: 15,
        page: true
    });
	
	// 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = JSON.stringify(data.field);
        layer.alert(result, {
            title: '最终的搜索信息'
        });

        //执行搜索重载
        table.reload('roleTable', {
            page: {
                curr: 1
            }
            , where: {
                searchParams: result
            }
        }, 'data');

        return false;
    });

    

    //监听表格复选框选择
    table.on('checkbox(roleList)', function (obj) {
        console.log(obj)
    });

    table.on('tool(roleList)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            layer.alert('编辑行：<br>' + JSON.stringify(data))
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        }
    });

});