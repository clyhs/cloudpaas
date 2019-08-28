/**
 * 
 */
package com.cloudpaas.common.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年8月28日 下午2:40:31
 */
@Data
public class Corp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String  corpname;
	
	private String  corpcode;
	
	private String  corpdbcode;
	
	

}
