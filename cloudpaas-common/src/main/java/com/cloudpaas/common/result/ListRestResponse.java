package com.cloudpaas.common.result;

/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 下午2:38:33
 */
public class ListRestResponse<T>extends BaseResponse {
    
    T data;
    int count;
    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ListRestResponse count(int count) {
        this.setCount(count);
        return this;
    }

    public ListRestResponse count(Long count) {
        this.setCount(count.intValue());
        return this;
    }

  

}
