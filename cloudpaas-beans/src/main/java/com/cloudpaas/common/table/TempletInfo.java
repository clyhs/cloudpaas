/**
 * 
 */
package com.cloudpaas.common.table;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * layui 表格列的templet模板
 * 
 * @author 大鱼
 *
 * @date 2019年9月6日 下午12:38:22
 */
@Data
public class TempletInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String field;
	
	private Map<String,String> values ;

}
