/**
 * 
 */
package com.cloudpaas.plugin.mybatis.rest;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.result.ObjectResponse;
import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.plugin.mybatis.base.ABaseBiz;
import com.cloudpaas.plugin.mybatis.utils.Query;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author 大鱼
 *
 * @date 2019年8月8日 下午6:40:59
 */
public abstract class BaseController<Dao extends ABaseBiz,T> {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Dao baseDao;
    
    @ApiOperation(value="添加接口",notes="")
	@RequestMapping(value = "/add.json",method = RequestMethod.POST)
    @ResponseBody
    public ObjectResponse<T> add(
    		@ApiParam(value = "实体", required = true) @RequestBody T entity,
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        baseDao.insertSelective(entity,db);
        return new ObjectResponse<T>();
    }
    
    @ApiOperation(value="获取全部数据接口",notes="")
    @RequestMapping(value = "/all.json",method = RequestMethod.GET)
    @ResponseBody
    public PageResponse<T> all(
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        List<T> list = baseDao.selectListAll(db);
    	PageResponse<T> result = new PageResponse();
    	result.setData(list);
    	return result;
    }
    
    @ApiOperation(value="分页接口",notes="参数全部自动放到map里面,默认10条数据")
    @RequestMapping(value = "/page.json",method = RequestMethod.GET)
    @ResponseBody
    public PageResponse<T> list(
    		/*
    		@RequestParam(name="page" ,required=false,defaultValue="1") Integer page,
    		@RequestParam(name="limit" ,required=false,defaultValue="20") Integer size,*/
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db,
    		@ApiParam(value = "动态参数", required = false) @RequestParam Map<String, Object> params){
        //查询列表数据
    	log.info("---------------params:{}--------------",JSON.toJSONString(params));
        Query query = new Query(params);
        return baseDao.selectByQuery(query,db);
    }
    
    @ApiOperation(value="更新接口",notes="")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectResponse<T> update(
    		@ApiParam(value = "实体数据", required = true) @RequestBody T entity,
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        baseDao.updateSelectiveById(entity,db);
        return new ObjectResponse<T>();
    }
    
    @ApiOperation(value="删除接口",notes="")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResponse<T> remove(
    		@ApiParam(value = "ID", required = true) @PathVariable int id,
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        baseDao.deleteById(id,db);
        return new ObjectResponse<T>();
    }
    
    @ApiOperation(value="批量删除接口",notes="实体包含ID主健")
    @RequestMapping(value = "/deleteBatch.json",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectResponse<T> deleteBatch(
    		@ApiParam(value = "lists", required = true) @RequestBody List<T> lists,
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){    
        if(lists == null || lists.size()==0){
            return new ObjectResponse<T>();
        }
        for (T entity : lists){
        	baseDao.delete(entity, db);
        }
        return new ObjectResponse<T>();
    }
    
    @ApiOperation(value="获取单实体接口",notes="实体包含ID主健")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse<T> get(
    		@ApiParam(value = "id", required = true) @PathVariable Integer id,
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        ObjectResponse<T> entityObjectRestResponse = new ObjectResponse<>();
        Object o = baseDao.selectById(id,db);
        entityObjectRestResponse.data((T)o);
        return entityObjectRestResponse;
    }
    
    @ApiOperation(value="获取单实体接口",notes="实体包含ID主健")
    @RequestMapping(value = "/selectOne.json",method = RequestMethod.GET)
    @ResponseBody
    public ObjectResponse<T> get(
    		@ApiParam(value = "entity", required = true) @RequestBody T entity,
    		@ApiParam(value = "节点代号", required = false) @RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        
    	ObjectResponse<T> entityObjectRestResponse = new ObjectResponse<>();
        Object o = baseDao.selectOne(entity, db);
        entityObjectRestResponse.data((T)o);
        return entityObjectRestResponse;
    }

}
