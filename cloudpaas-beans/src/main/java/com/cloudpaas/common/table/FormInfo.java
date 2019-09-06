/**
 * 
 */
package com.cloudpaas.common.table;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 提交表单类
 * 
 * @author 大鱼
 *
 * @date 2019年9月6日 下午2:28:08
 */
@Data
public class FormInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  id;
	
	private UrlInfo url;
	
	/**
	 * edit,add
	 */
	private String  type;
	
	private List<FormFieldInfo> fields;
	
	/**
	 * post,put,delete...gt
	 */
	private String  method;
	

}
