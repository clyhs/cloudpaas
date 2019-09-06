/**
 * 
 */
package com.cloudpaas.common.table;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年9月6日 下午2:58:46
 */
@Data
public class FormFieldInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  name;
	
	private String  label;
	/**
	 * 表单输入框类型
	 * input,select,
	 */
	private String  type;
	
	/**
	 * 是否必填
	 */
	private Boolean required;
	
	/**
	 * 校验规则
	 * 如number,tel,email等
	 * 
	 */
	private String  verify;
	/**
	 * 一般复杂控件需要
	 * 如：sexType
	 */
	private String  templet;
	
	private Integer sort;
	
	private Map<String,String> values;

}
