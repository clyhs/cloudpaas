/**
 * 
 */
package com.cloudpaas.common.model;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 大鱼
 *
 * @date 2019年8月4日 上午12:01:07
 */
@Table(name = "t_role")
public class Role {
	
	@Id
	private Integer id;
	
	private String  name;
	
	private String  desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
