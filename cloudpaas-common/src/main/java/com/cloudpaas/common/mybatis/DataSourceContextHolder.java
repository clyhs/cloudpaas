/**
 * 
 */
package com.cloudpaas.common.mybatis;

import java.util.Set;

/**
 * @author 大鱼
 *
 * @date 2019年8月2日 上午11:44:13
 * 
 * 手动切换数据源工具类
 */
public class DataSourceContextHolder {

	//线程本地环境
    private static final ThreadLocal<String> dataSources = new ThreadLocal<String>();
  	
  	
    //设置数据源
    public static void setDataSource(String customerType) {
        dataSources.set(customerType);
    }
    //获取数据源
    public static String getDataSource() {
        return (String) dataSources.get();
    }
    //清除数据源
    public static void clearDataSource() {
        dataSources.remove();
    }
}
