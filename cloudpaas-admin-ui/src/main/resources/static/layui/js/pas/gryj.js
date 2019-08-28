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
        elem: '#oTable',
        url: api.getGryjPageUrl,
        cols: [[
            {type: "checkbox", width: 50, fixed: "left"},
            {field: 'id', width: 80, title: 'ID', sort: true},
            {field: 'jgdh', width: 80, title: '机构代号'},
            {field: 'jgmc', title: '机构名称', width: 110},
            {field: 'hydh', width: 60, title: '行员代号', sort: true},
            {field: 'hymc', title: '行员名称', width: 100},
            {field: 'zbmc', title: '指标名称', width: 120},
            {field: 'bz', title: '币种', width: 50},
            {field: 'sdbs', title: '时段标识', width: 60},
            {field: 'zbz', title: '指标值', width: 80},
            {field: 'zbdw', title: '指标单位', width: 60},
            {field: 'khjs', title: '考核基数', width: 100},
            
            {title: '操作', minWidth: 50, templet: '#oBar', fixed: "right", align: "center"}
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
                title : "编辑业绩",
                type : 2,
                content : $config.context+"/gryj/edit.html?id="+data.id,
                success : function(layero, editIndex){
                    setTimeout(function(){
                        layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500);
                }
            });
            layuiresize(editIndex);
        }
        if(obj.event === "del"){
            layer.confirm("你确定要删除该用户么？",{btn:['是的,我确定','我再想想']},
                function(){
            	$.ajax({
                    type:"DELETE",
                    url:api.deleteGryjDelUrl+"?id="+data.id,
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
                    title : "添加业绩",
                    type : 2,
                    content : $config.context+"/gryj/add.html",
                    success : function(layero, addIndex){
                        setTimeout(function(){
                            layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
            delBatch : function(){
                var checkStatus = table.checkStatus('oTable'),
                    data = checkStatus.data;
                if(data.length > 0){
                    console.log(JSON.stringify(data));
                    //layer.msg(JSON.stringify(data));
                    
                    layer.confirm("你确定要删除这些业绩么？",{btn:['是的,我确定','我再想想']},
                        function(){
                            var deleteindex = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
                            $.ajax({
                                type:"DELETE",
                                url:api.deleteBatchGryjUrl,
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

    
});