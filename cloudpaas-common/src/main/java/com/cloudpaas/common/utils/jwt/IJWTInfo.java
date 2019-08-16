package com.cloudpaas.common.utils.jwt;


public interface IJWTInfo {
    /**
     * 获取用户名
     * @return
     */
    String getUsername();

    /**
     * 获取用户ID
     * @return
     */
    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();
}
