/**
 * 
 */
package com.cloudpaas.common.table;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * layui 表格类
 * 
 * @author 大鱼
 *
 * @date 2019年9月5日 上午9:20:58
 */
@Data
public class TableInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  elem;
	
	private List<FieldInfo> cols;
	
	private UrlInfo  url;
	
	private List<String> defaultToolbar;
	
	private String  width;
	
	private String  height;
	
	private Integer cellMinWidth;
	/**
	 * Function
	 * 数据渲染完的回调。你可以借此做一些其它的操作
	 */
	private String  done;
	
	/**
	 * 直接赋值数据
	 */
	private List<String> data;
	
	private Boolean totalRow;
	
	private Boolean page;
	
	/**
	 * 每页显示的条数（默认：10）
	 */
	private Integer limit;

	/**
	 * 默认：[10,20,30,40,50,60,70,80,90]。
	 */
	private List<Integer> limits;
	/**
	 * 是否显示加载条（默认：true）
	 */
	private Boolean loading;
	
	private String  title;
	/**
	 * 自定义文本，如空数据时的异常提示等
	 */
	private String  text;
	
	/**
	 * 默认 true，即直接由 table 组件在前端自动处理排序。
	 */
	private Boolean autoSort;
	/**
	 * 初始排序状态。用于在数据表格渲染完毕时，默认按某个字段排序。
	 */
	private String  initSort;
	/**
	 * 设定容器唯一 id。id 是对表格的数据操作方法上是必要的传递条件
	 * 该参数也可以自动从 <table id="test"></table> 中的 id 参数中获取。
	 */
	private String  id;
}
