package com.cloudpaas.common.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MenuTreeVo extends TreeVo<MenuTreeVo> {
	
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