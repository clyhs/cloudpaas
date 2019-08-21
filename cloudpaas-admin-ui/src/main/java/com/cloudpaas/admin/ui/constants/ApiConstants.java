/**
 * 
 */
package com.cloudpaas.admin.ui.constants;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年8月15日 下午1:41:20
 */
public class ApiConstants {

	public static final String API_HOST = "http://localhost:8200";
	/*根据id获取单个角色，后面必须+id,如：API_ROLE_GET_URL+id*/
	public static final String API_ROLE_SINGLE_URL = API_HOST+"/api/role/";
	public static final String API_ROLE_SELECTONE_URL = API_HOST+"/api/role/selectOne.json";
	public static final String API_ROLE_DELBATCH_URL =  API_HOST+"/api/role/deleteBatch.json";
	public static final String API_ROLE_PAGE_URL = API_HOST+"/api/role/page.json";
	public static final String API_ROLE_ALL_URL = API_HOST+"/api/role/all.json";
	public static final String API_ROLE_ADD_URL = API_HOST+"/api/role/add.json";
	
	
	public static final String API_MENU_SINGLE_URL = API_HOST+"/api/menu/";
	public static final String API_MENU_SELECTONE_URL = API_HOST+"/api/menu/selectOne.json";
	public static final String API_MENU_DELBATCH_URL =  API_HOST+"/api/menu/deleteBatch.json";
	public static final String API_MENU_PAGE_URL = API_HOST+"/api/menu/page.json";
	public static final String API_MENU_ALL_URL = API_HOST+"/api/menu/all.json";
	public static final String API_MENU_ADD_URL = API_HOST+"/api/menu/add.json";
	public static final String API_MENU_TREE_URL = API_HOST+"/api/menu/tree.json";
	
	
	public static final String API_USER_SINGLE_URL = API_HOST+"/api/user/";
	public static final String API_USER_SELECTONE_URL = API_HOST+"/api/user/selectOne.json";
	public static final String API_USER_DELBATCH_URL =  API_HOST+"/api/user/deleteBatch.json";
	public static final String API_USER_PAGE_URL = API_HOST+"/api/user/page.json";
	public static final String API_USER_ALL_URL = API_HOST+"/api/user/all.json";
	public static final String API_USER_ADD_URL = API_HOST+"/api/user/add.json";
	public static final String API_USER_TREE_URL = API_HOST+"/api/user/tree.json";
	
	
	
	

	
	
}
