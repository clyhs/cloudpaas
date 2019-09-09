/**
 * 
 */
package com.cloudpaas.gateway.callback;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudpaas.common.result.PageResponse;
import com.cloudpaas.gateway.feign.IRouterService;
import com.cloudpaas.gateway.model.Router;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 下午3:06:14
 */
@Service
@Slf4j
public class RouterServiceFallBack implements IRouterService {/* (non-Javadoc)
	 * @see com.cloudpaas.gateway.feign.IRouterService#getAll()
	 */
	@Override
	public PageResponse<Router> getAll() {
		// TODO Auto-generated method stub
		log.debug("router:异常");
		return null;
	}

}
