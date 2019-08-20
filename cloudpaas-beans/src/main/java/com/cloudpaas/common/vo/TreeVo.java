/**
 * 
 */
package com.cloudpaas.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 树父类
 * 
 * @author 大鱼
 *
 * @date 2019年8月13日 下午2:54:44
 */
@Data
public class TreeVo<T extends TreeVo> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Integer id;
	
	protected Integer pId;
	
	protected List<T> childrens = new ArrayList<T>();
	
	public void add(T node){
        childrens.add(node);
    }
	
	public void clear(){
		if(childrens.size()>0){
			childrens.clear();
		}
	}
}
