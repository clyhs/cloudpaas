layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"tool" : "tool",
	"api" : "api"
}).use([ 'element', 'layer' ,'api',"table","form","tool"], function() {
	var $ = layui.jquery, element = layui.element, layer = layui.layer,
	    api = layui.api,
	    table = layui.table,
        form = layui.form,
        tool = layui.tool;
	
	table.render({
        elem: '#roleTable',
        url: api.getRolePageUrl,
        cols: [[
            {type: "checkbox", width: 50, fixed: "left"},
            {field: 'id', width: 80, title: 'ID', sort: true},
            {field: 'code', width: 180, title: '角色代号'},
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
        	var editIndex = layer.open({
                title : "编辑角色",
                type : 2,
                content : "/admin/system/role/edit?id="+data.id,
                success : function(layero, index){
                    setTimeout(function(){
                        layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
            $(window).resize(function(){
                layer.full(editIndex);
            });
            layer.full(editIndex);
        } else if (obj.event === 'delete') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                layer.close(index);
            });
        }
    });
    
    var active={
            addRole : function(){
                addIndex = layer.open({
                    title : "添加角色",
                    type : 2,
                    content : $config.context+"/role/add.html",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                $(window).resize(function(){
                    layer.full(addIndex);
                });
                layer.full(addIndex);
            },
            //批量删除
            deleteBatch : function(){
                var checkStatus = table.checkStatus('roleTable'),
                    data = checkStatus.data;
                if(data.length > 0){
                    console.log(JSON.stringify(data));
                    layer.confirm("你确定要删除这些角色么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            var deleteindex = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                            $.ajax({
                                type:"POST",
                                url:$config.context+"role/deleteBatch.json",
                                dataType:"json",
                                contentType:"application/json",
                                data:JSON.stringify(data),
                                success:function(res){
                                    layer.close(deleteindex);
                                    if(res.success){
                                        layer.msg("删除成功",{time: 1000},function(){
                                            table.reload('roleTable', t);
                                        });
                                    }else{
                                        layer.msg(res.message);
                                    }
                                }
                            });
                        }
                    )
                }else{
                    layer.msg("请选择需要删除的角色",{time:1000});
                }
            }
        };
    
    
    $('.layui-inline .layui-btn').on('click', function(){
    	
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});