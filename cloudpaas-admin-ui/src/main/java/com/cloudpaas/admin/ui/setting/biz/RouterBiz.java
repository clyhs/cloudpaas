/**
 * 
 */
package com.cloudpaas.admin.ui.setting.biz;

import org.springframework.stereotype.Service;

import com.cloudpaas.admin.ui.base.BaseBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.gateway.model.Router;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午11:45:03
 */
@Service
public class RouterBiz extends BaseBiz<Router> {

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.AbstractBaseBiz#getSID()
	 */
	@Override
	public String getSID() {
		// TODO Auto-generated method stub
		return CommonConstants.ADMIN_SERVICE_ID;
	}

}
