package com.cloudpaas.common.result;

import java.io.Serializable;

import javax.persistence.Entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.NoArgsConstructor;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 下午2:41:19
 */

public class ObjectResponse<T> extends BaseResponse {

    T data;
    boolean rel;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }


    public ObjectResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectResponse data(T data) {
        this.setData(data);
        return this;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
