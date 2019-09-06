/**
 * 
 */
package com.cloudpaas.common.table;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年9月6日 上午11:05:41
 */
@Data
public class ButtonInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  name;
	
	/**
	 * 1:search area,2:table top toolbar,3,table right toolbar
	 */
	private Integer area;
	
	/**
	 * add,edit,delete,search,import,export...
	 */
	private String  event;
	
	private String  css;
	
	private Integer sort;
	
	
}
