/**
 * 
 */
package com.cloudpaas.admin.ui.base;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.constants.CommonConstants;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.PageResponse;

/**
 * @author 大鱼
 *
 * @date 2019年8月16日 下午2:42:19
 */
public class BaseController {
	
	protected Logger log = LoggerFactory.getLogger(getClass());

	public User getLogin(){
		Session session = SecurityUtils.getSubject().getSession();
		User user = (User) session.getAttribute(CommonConstants.USER_SESSION_ID);
		return user;
	}
	
	
}
