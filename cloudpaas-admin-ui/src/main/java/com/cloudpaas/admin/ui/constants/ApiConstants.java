/**
 * 
 */
package com.cloudpaas.admin.ui.constants;

/**
 * @author 大鱼
 *
 * @date 2019年8月15日 下午1:41:20
 */
public class ApiConstants {

	public static final String API_HOST = "http://localhost:8200";
	/*根据id获取单个角色，后面必须+id,如：API_ROLE_GET_URL+id*/
	public static final String API_ROLE_GET_URL = API_HOST+"/api/role/";
	
	public static final String API_USER_SELECT_URL = API_HOST+"/api/user/selectOne.json";
	
	/*根据id获取单个菜单，后面必须+id,如：API_MENU_GET_URL+id*/
	public static final String API_MENU_GET_URL = API_HOST+"/api/menu/";
	/*获全部树形菜单，包括显示与不显示，要自己过滤*/
	public static final String API_MENU_GET_TREES_URL = API_HOST+"/api/menu/tree.json";
	
	public static final String API_MENU_GET_LIST_URL = API_HOST+"/api/menu/all.json";
}
