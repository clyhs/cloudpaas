/**
 * 
 */
package com.cloudpaas.common.table;

import java.io.Serializable;

import lombok.Data;

/**
 * layui 表格中的列类
 * 
 * @author 大鱼
 *
 * @date 2019年9月5日 上午9:05:10
 */
@Data
public class FieldInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  field;
	
	private String  title;
	
	private String  width;
	
	private String  minWidth;
	/**
	 * 可选值有
	 * normal（常规列，无需设定）
	 * checkbox（复选框列）
	 * radio（单选框列）
	 * numbers（序号列）
	 * space（空列）
	 */
	private String  type;
	
	/**
	 * 是否全选状态（默认：false）
	 */
	private Boolean layChecked;
	
	/**
	 * left（固定在左）、right（固定在右）
	 */
	private String  fixed;
	
	private Boolean hide;
	
	private Boolean totalRow;
	
	private String  totalRowText;
	
	private Boolean sort;
	
	/**
	 * 是否禁用拖拽列宽（默认：false）。默认情况下会根据列类型（type）来决定是否禁用，
	 * 如复选框列，会自动禁用。而其它普通列，
	 * 默认允许拖拽列宽，
	 * 当然你也可以设置 true 来禁用该功能
	 */
	private Boolean unresize;
	/**
	 * 单元格编辑类型（默认不开启）目前只支持：text（输入框）
	 */
	private String  edit;
	
	/**
	 * 自定义单元格点击事件名，以便在 tool 事件中完成对该单元格的业务处理
	 */
	private String  event;
	
	private String  style;
	/**
	 * 单元格排列方式。可选值有：left（默认）、center（居中）、right（居右）
	 */
	private String  align;
	/**
	 * 单元格所占列数（默认：1）。一般用于多级表头
	 */
	private Integer colspan;
	
	/**
	 * 单元格所占行数（默认：1）。一般用于多级表头
	 */
	private Integer rowspan;
	/**
	 * 自定义列模板，模板遵循 laytpl 语法
	 */
	private String  templet;
	
	private String  toolbar;
	
	private Integer fieldsort;

}
