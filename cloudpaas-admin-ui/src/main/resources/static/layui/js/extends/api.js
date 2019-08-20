layui.define(['tool','jquery'], function (exports) {

    var tool = layui.tool,
        $ = layui.jquery;// 拿到模块变量

    /**
     * 封装一个post
     * */
    function doPost(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"post",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        });
    }

    /**
     * 封装一个get
     * */
    function doGet(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"get",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        });
    }

    /**
     * 封装一个支持更多参数的post
     * */
    function doComplexPost(url,req,config,successCallback,errorCallback) {
        var defaultConfig = {
            url:url,
            data:req,
            method:"post",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        };
        var ajaxConfig = $.extend({},config,defaultConfig); // 合并参数

        $.ajax(ajaxConfig);
    }

    // API列表,工程庞大臃肿后可以将API拆分到单独的模块中
    var API = {
        doPost: function(url,req,successCallback,errorCallback){
            doPost(url,req,successCallback,errorCallback);
        },
        doGet: function(url,req,successCallback,errorCallback){
            doGet(url,req,successCallback,errorCallback);
        },
        

        getRolePageUrl:tool.getContext()+"/api/role/page.json",
        postRoleAddUrl:tool.getContext()+"/api/role/add.json",
        putRoleEditUrl:tool.getContext()+"/api/role/",
        deleteRoleDelUrl:tool.getContext()+"/api/role/",
        deleteBatchRoleUrl:tool.getContext()+"/api/role/deleteBatch.json",
        
        getMenuListUrl:tool.getContext()+"/api/menu/all.json",
        postMenuAddUrl:tool.getContext()+"/api/menu/add.json",
        putMenuEditUrl:tool.getContext()+"/api/menu/",
        deleteMenuDelUrl:tool.getContext()+"/api/menu/",
        
        getUserPageUrl:tool.getContext()+"/api/user/page.json",
        putUserEditUrl:tool.getContext()+"/api/user/",
        deleteUserDelUrl:tool.getContext()+"/api/user/",
        deleteBatchUserUrl:tool.getContext()+"/api/user/deleteBatch.json",
        
        
    };




    //输出扩展模块
    exports('api', API);
});