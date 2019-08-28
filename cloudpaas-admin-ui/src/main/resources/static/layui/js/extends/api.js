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
        

        getRolePageUrl:tool.getResUrl()+"/role/page.json",
        postRoleAddUrl:tool.getResUrl()+"/role/add.json",
        putRoleEditUrl:tool.getResUrl()+"/role/update.json",
        deleteRoleDelUrl:tool.getResUrl()+"/role/delete.json",
        deleteBatchRoleUrl:tool.getResUrl()+"/role/deleteBatch.json",
        
        getMenuListUrl:tool.getResUrl()+"/menu/all.json",
        postMenuAddUrl:tool.getResUrl()+"/menu/add.json",
        putMenuEditUrl:tool.getResUrl()+"/menu/update.json",
        deleteMenuDelUrl:tool.getResUrl()+"/menu/delete.json",
        
        getUserPageUrl:tool.getResUrl()+"/user/page.json",
        postUserAddUrl:tool.getResUrl()+"/user/add.json",
        putUserEditUrl:tool.getResUrl()+"/user/update.json",
        deleteUserDelUrl:tool.getResUrl()+"/user/delete.json",
        deleteBatchUserUrl:tool.getResUrl()+"/user/deleteBatch.json",
        
        getGryjPageUrl:tool.getResUrl()+"/gryj/page.json",
        postGryjAddUrl:tool.getResUrl()+"/gryj/add.json",
        putGryjEditUrl:tool.getResUrl()+"/gryj/update.json",
        deleteGryjDelUrl:tool.getResUrl()+"/gryj/delete.json",
        deleteBatchGryjUrl:tool.getResUrl()+"/gryj/deleteBatch.json",
        
        
    };




    //输出扩展模块
    exports('api', API);
});