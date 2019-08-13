/**
 * 
 */
package com.cloudpaas.common.vo;

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
public class TreeVo {

	protected Integer id;
	
	protected Integer pId;
	
	protected List<TreeVo> childrens = new ArrayList<TreeVo>();
	
	public void add(TreeVo node){
        childrens.add(node);
    }
}
