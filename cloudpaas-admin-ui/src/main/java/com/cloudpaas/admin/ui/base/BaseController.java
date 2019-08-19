/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudpaas.admin.ui.constants.CommonConstants;
import com.cloudpaas.common.model.User;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午2:42:19
 */
public abstract class BaseController {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	public User getLogin(){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User) session.getAttribute(CommonConstants.USER_SESSION_ID);
		return user;
	}
}
