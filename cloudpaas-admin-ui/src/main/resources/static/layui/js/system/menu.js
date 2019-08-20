layui.config({
	base : $config.context+"/static/layui/js/extends/",
	version : true
}).extend({
	"api" : "api",
	"treetable":"treetable",
	adminui : "adminui"
}).use([ 'element', 'layer' ,'api',"form","treetable","table","jquery",'adminui'], function() {
	var $ = layui.jquery, element = layui.element, layer = layui.layer,
	    api = layui.api,
	    table = layui.table,
	    treetable = layui.treetable,
        form = layui.form,
        $ = layui.jquery;
	
	treetable.render({
        treeColIndex: 1, //树形图标显示在第几列
        treeSpid: '-1', //最上级的父级id
        treeIdName: 'id', //id字段的名称
        treePidName: 'pId', //pid字段的名称
        treeDefaultClose: true, //是否默认折叠
        elem: '#oTable',
        url: api.getMenuListUrl,
        page: false, //分页，即使设置为true也不进行分页
        treeLinkage: false, //父级展开时是否自动展开所有子级
        cols: [[
        	{type:'radio'},
            {field: 'title', minWidth: 100, title: '菜单名称'},
            {field: 'icon', width: '8%',title: '图标',templet: function (d) {
                    if (d.icon == null) {
                        return '';
                    }
                    return '<i class="'+d.icon+'" ></i>';
                }
             },
            {field: 'url', title: '菜单url', width: '20%'},
            {field: 'sort', width: '8%', align: 'center', title: '排序号'},
            {
                field: 'type', width: '8%', align: 'center', templet: function (d) {
                    if (true) {
                        if(d.type == 1) {
                            return '<span class="layui-badge layui-bg-blue">目录</span>';
                        } else if(d.type == 2){
                            return '<span class="layui-badge-rim">菜单</span>';
                        }else if(d.type == 3){
                        	return '<span class="layui-badge layui-bg-gray">按钮</span>';
                        }
                    }else {
                        return '<span class="layui-badge layui-bg-gray">按钮</span>';
                    }
                }, title: '类型'
            },
            {
            	field: 'isShow', width: '8%', align: 'center', templet: function (d) {
            		if(d.isShow == 1) {
                        return '<input type="checkbox" lay-skin="switch" lay-text="启用|禁用" value='+d.id+' lay-filter="isShow" checked> ';
                    } else{
                        return '<input type="checkbox" lay-skin="switch" lay-text="启用|禁用" value='+d.id+' lay-filter="isShow" > ';
                    }
            	},title:'是否显示'
            },
            {templet: '#oBar', width: '30%', align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });

    function layuiresize(index) {
        $(window).resize(function(){
            layer.full(index);
        });
        layer.full(index);
    };

    table.on('tool(oList)', function(obj){
        var data = obj.data;
        
        if(obj.event === 'edit'){
            var editIndex = layer.open({
                title : "编辑菜单",
                type : 2,
                content : $config.context+"/menu/edit.html?id="+data.id,
                success : function(layero, editIndex){
                    setTimeout(function(){
                        layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            layuiresize(editIndex);
        }
        if(obj.event === "del"){
            layer.confirm("你确定要删除该菜单么？这将会使得其下的所有子菜单都删除",{btn:['是的,我确定','我再想想']},
                function(){
            	$.ajax({
                    type:"DELETE",
                    url:api.deleteMenuDelUrl+data.id,
                    dataType:"json",
                    contentType:"application/json",
                    data:JSON.stringify(data.field),
                    success:function(res){
                        if(res.code==0){
                            parent.layer.msg("菜单删除成功！",{time:1000},function(){
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
                }
            )
        }
    });

    var active={
        btnExpand : function() {
            treetable.expandAll('#oTable');
        },
        btnFold : function () {
            treetable.foldAll('#oTable');
        },
        add : function(){
        	var checkStatus = table.checkStatus('oTable');
        	var data;
        	var pId;
        	
        	if(checkStatus.data.length > 0 && checkStatus.data.length==1){
        		data = checkStatus.data[0];
        		pId = data.id;
        		
        		if(data.type == 3){
        			layer.msg('按钮不能再添加子菜单');
        			return ;
        		}
        	}else{
        		pId = -1;
        	}
        	
            var addIndex = layer.open({
                title : "添加系统菜单",
                type : 2,
                content : $config.context+"/menu/add.html?pId=" + pId,
                success : function(layero, addIndex){
                    setTimeout(function(){
                        layer.tips('点击此处返回角色列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            layuiresize(addIndex);
        }

    };

    $('.layui-inline .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
	
    
    
    
    // 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {

        //执行搜索重载
        table.reload('oTable', {
            page: {
                curr: 1
            }
            , where: data.field
        }, 'data');

        return false;
    });
	
    
    form.on('switch(isShow)', function(data){
    	var id = data.value;

    	var isShow = this.checked ? '1' : '0';
    	
    	var params = {"id" :id,"isShow":isShow  }
    	var loadIndex = layer.load(2, {
            shade: [0.3, '#333']
        });
    	$.ajax({
            type:"PUT",
            url:api.putMenuEditUrl+id,
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(params),
            success:function(res){
                layer.close(loadIndex);
                if(res.code==0){
                    parent.layer.msg("菜单更新成功！",{time:1000},function(){
                        //刷新父页面
                        //parent.location.reload();
                    });
                }else{
                    layer.msg(res.message,{time:1000},function(){
                        //刷新本页面
                        location.reload();
                    });

                }
            }
        });
    });

});