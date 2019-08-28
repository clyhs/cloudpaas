/**
 * 
 */
package com.cloudpaas.admin.vo;

import com.cloudpaas.common.vo.TreeVo;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年8月13日 下午2:57:26
 */
@Data
public class AdminMenuTreeVo extends TreeVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String  icon;
	
    private String  title;
    
    private String  url;

    private Integer sort;

    private String  remark;
    
    private Integer level;
    
    private Integer type;
    
    private Integer isShow;

}
