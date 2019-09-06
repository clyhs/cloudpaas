/**
 * 
 */
package com.cloudpaas.common.table;

import java.io.Serializable;

import lombok.Data;

/**
 * layui 表格中url请求详细信息类
 * 
 * @author 大鱼
 *
 * @date 2019年9月5日 上午9:39:06
 */
@Data
public class UrlInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 默认会自动传递两个参数：?page=1&limit=30
	 */
	private String  url;
	
	/**
	 * 接口http请求类型，默认：get
	 */
	private String  method;
	
	private String  where;
	/**
	 * 发送到服务端的内容编码类型。如果你要发送 json 内容，可以设置：contentType: 'application/json'
	 */
	private String  contentType;
	/**
	 * 接口的请求头。如：headers: {token: 'sasasas'}
	 * 	接口的请求头。如：headers: {token: 'sasasas'}
	 */
	private String  headers;
	
	private String  parseData;
	
	/**
	 * 用于对分页请求的参数：page、limit重新设定名称，如：
	 * request:{
	 * 	pageName: 'pageNo', //页码的参数名称，默认：page
	 * 	limitName: 'pageSize' //每页数据量的参数名，默认：limit
	 * }
	 */
	private String  request;
	
	/**
	 * 如果你想重新规定返回的数据格式，那么可以借助 response 参数
	 * response:{
	 * 	statusName: 'status' //规定数据状态的字段名称，默认：code
	 * ,statusCode: 200 //规定成功的状态码，默认：0
	 * ,msgName: 'hint' //规定状态信息的字段名称，默认：msg
	 * ,countName: 'total' //规定数据总数的字段名称，默认：count
	 * ,dataName: 'rows' //规定数据列表的字段名称，默认：data
	 * }
	 */
	private String  response;
	
	

}
