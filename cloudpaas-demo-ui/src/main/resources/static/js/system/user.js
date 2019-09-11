layui.config({
	base : $config.context+"/static/js/extends/",
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
        elem: '#oTable',
        url: api.getUserPageUrl,
        cellMinWidth: 60,
        toolbar: '#oToolbar',
        cols: [[
            {type: "checkbox", width: 60, fixed: "left"},
            {field: 'id', width: 80, title: 'ID', sort: true},
            {field: 'username', width: 80, title: '用户名'},
            
            
            {field: 'name', title: '真实姓名', width: 150},
            {field: 'tel', width: 100, title: '手机号', sort: true},
            
            {field: 'sex', width: 80, title: '性别', sort: true,templet: function (d) {
	            	if(d.sex == 1) {
	                    return '<span class="layui-badge layui-bg-blue">男</span>';
	                } else if(d.sex == 2){
	                    return '<span class="layui-badge layui-bg-gray">女</span>';
	                }else if(d.sex == 0){
	                	return '<span class="layui-badge layui-bg-red">保密</span>';
	                }
        		}
            },
            
            {field: 'createTime', minWidth: 60, title: '注册时间'},
            {title: '操作', minWidth: 60, templet: '#oBar', fixed: "right", align: "center"}
        ]],
        limits: [5,10, 15, 20, 25, 50, 100],
        limit: 15,
        page: true
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
                title : "编辑用户",
                type : 2,
                content : $config.context+"/user/edit.html?id="+data.id,
                area: ['80%', '80%'],
                maxmin: true,
                success : function(layero, editIndex){
                    setTimeout(function(){
                        layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            //layuiresize(editIndex);
        }
        if(obj.event === "del"){
            layer.confirm("你确定要删除该用户么？",{btn:['是的,我确定','我再想想']},
                function(){
            	$.ajax({
                    type:"DELETE",
                    url:api.deleteUserDelUrl+"?id="+data.id,
                    dataType:"json",
                    contentType:"application/json",
                    data:JSON.stringify(data.field),
                    success:function(res){
                        if(res.code==0){
                            parent.layer.msg("删除成功！",{time:1000},function(){
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
            add : function(){
                addIndex = layer.open({
                    title : "添加用户",
                    type : 2,
                    content : $config.context+"/user/add.html",
                    area: ['80%', '80%'],
                    maxmin: true,
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        },500);
                    }
                });
                //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                //layuiresize(addIndex);
            },
            //批量删除
            delBatch : function(){
                var checkStatus = table.checkStatus('oTable'),
                    data = checkStatus.data;
                if(data.length > 0){
                    console.log(JSON.stringify(data));
                    //layer.msg(JSON.stringify(data));
                    
                    layer.confirm("你确定要删除这些用户么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            var deleteindex = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                            $.ajax({
                                type:"DELETE",
                                url:api.deleteBatchUserUrl,
                                dataType:"json",
                                contentType:"application/json",
                                data:JSON.stringify(data),
                                success:function(res){
                                    layer.close(deleteindex);
                                    if(res.code==0){
                                        layer.msg("删除成功",{time: 1000},function(){
                                        	location.reload();
                                        });
                                    }else{
                                        layer.msg(res.message);
                                    }
                                }
                            });
                        }
                    )
                }else{
                    layer.msg("请选择需要删除的用户",{time:1000});
                }
            }
        };
    
    //监听工具拦的操作按钮
    $('.layui-inline .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
	

	// 监听搜索操作
    form.on('submit(data-search-btn)', function (data) {
        var result = JSON.stringify(data.field);
        /*
        layer.alert(result, {
            title: '最终的搜索信息'
        });*/

        //执行搜索重载
        table.reload('oTable', {
            page: {
                curr: 1
            }
            , where: data.field
        }, 'data');

        return false;
    });



    //监听表格复选框选择
    table.on('checkbox(oList)', function (obj) {
        console.log(obj)
    });
    
  //头工具栏事件
    table.on('toolbar(oList)', function(obj){
      var checkStatus = table.checkStatus(obj.config.id);
      switch(obj.event){
        case 'getCheckData':
          var data = checkStatus.data;
          layer.alert(JSON.stringify(data));
        break;
        case 'getCheckLength':
          var data = checkStatus.data;
          layer.msg('选中了：'+ data.length + ' 个');
        break;
        case 'isAll':
          layer.msg(checkStatus.isAll ? '全选': '未全选');
        break;
      };
    });

    
});