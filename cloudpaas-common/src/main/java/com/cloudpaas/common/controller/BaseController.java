/**
 * 
 */
package com.cloudpaas.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.common.base.dao.BaseDao;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.common.result.TableResultResponse;
import com.cloudpaas.common.utils.Query;

/**
 * @author 大鱼
 *
 * @date 2019年8月8日 下午6:40:59
 */
public abstract class BaseController<Dao extends BaseDao,T> {
	
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Dao baseDao;
    
    @RequestMapping(value = "/all.json",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<T> all(@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        List<T> list = baseDao.selectListAll(db);
    	TableResultResponse<T> result = new TableResultResponse();
    	result.setData(list);
    	return result;
    }
    
    @RequestMapping(value = "/page.json",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<T> list(@RequestParam(name="page" ,required=false,defaultValue="1") Integer page,
    		@RequestParam(name="limit" ,required=false,defaultValue="20") Integer size,
    		@RequestParam(value = "db", defaultValue = CommonConstants.DEFAULT_DATASOURCE_KEY, required = false) String db){
        //查询列表数据
        Query query = new Query(page,size);
        return baseDao.selectByQuery(query,db);
    }
    

}
