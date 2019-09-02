package com.cloudpaas.plugin.mybatis.utils;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 下午2:35:03
 */

public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);
        //分页参数
        if(params.get("page")!=null) {
            this.page = Integer.parseInt(params.get("page").toString());
        }
        if(params.get("limit")!=null) {
            this.limit = Integer.parseInt(params.get("limit").toString());
        }
        if(params.get("db")!=null){
        	this.remove("db");
        }
        this.remove("page");
        this.remove("limit");
    }
    
    public Query(Integer page,Integer limit,Map<String, Object> params){
    	this.putAll(params);
    	this.put("page", page);
    	this.put("limit", limit);
    	
        //分页参数
        if(page!=null) {
            this.page = page;
        }
        if(limit!=null) {
            this.limit = limit;
        }
        this.remove("page");
        this.remove("limit");
    }
    
    public Query(Integer page,Integer limit){
    	
    	this.put("page", page);
    	this.put("limit", limit);
    	
        //分页参数
        if(page!=null) {
            this.page = page;
        }
        if(limit!=null) {
            this.limit = limit;
        }
        this.remove("page");
        this.remove("limit");
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
