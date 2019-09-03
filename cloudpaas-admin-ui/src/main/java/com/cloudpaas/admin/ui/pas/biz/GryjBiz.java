/**
 * 
 */
package com.cloudpaas.admin.ui.pas.biz;

import org.springframework.stereotype.Service;

import com.cloudpaas.admin.ui.base.BaseBiz;
import com.cloudpaas.common.constants.CommonConstants;
import com.cloudpaas.pas.model.PasGryj;

/**
 * @author 大鱼
 *
 * @date 2019年8月28日 上午11:15:44
 */
@Service
public class GryjBiz extends BaseBiz<PasGryj>{

	/* (non-Javadoc)
	 * @see com.cloudpaas.admin.ui.base.AbstractBaseBiz#getSID()
	 */
	@Override
	public String getSID() {
		// TODO Auto-generated method stub
		return CommonConstants.PAS_SERVICE_ID;
	}

}
