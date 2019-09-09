/**
 * 
 */
package com.cloudpaas.gateway.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年9月9日 上午9:31:56
 */
@Data
@Table(name="t_router")
public class Router implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	
	/**
	 * id:cpaas-admin
	 */
	@Column(name = "router_id")
	private String  routerId;
	/**
	 * uri:"lb://CPAAS-ADMIN"
	 */
	private String  uri;
	
	/**
	 * order:8000
	 */
	@Column(name = "router_order")
	private Integer routerOrder;
	
	//private String  predicates;
	
	//private String  filters;
	
	/**
	 * predicates:
	 * 	  - Path=/api/admin/**
	 */
	private String  path;
	

	/**
	 * //是否忽略前缀
	 * filters:
     *    - StripPrefix=2
	 */
	@Column(name = "strip_prefix")
	private String  stripPrefix;
	
	//是否启用
    private Integer enabled;
    
    //令牌桶流速
    @Column(name = "limiter_rate")
    private String limiterRate;
	
	//令牌桶容量
    @Column(name = "limiter_capacity")
    private String limiterCapacity;
	

    @Column(name = "create_time")
    private Date   createTime;
}
