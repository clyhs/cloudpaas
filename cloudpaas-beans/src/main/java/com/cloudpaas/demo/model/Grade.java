/**
 * 
 */
package com.cloudpaas.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * @author 大鱼
 *
 * @date 2019年9月3日 上午11:33:28
 */
@Data
@Document(collection = "grade")
public class Grade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private Long id;

    private String gradeName;
	


}
