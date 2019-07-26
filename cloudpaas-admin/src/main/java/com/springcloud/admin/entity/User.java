package com.springcloud.admin.entity;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author 大鱼
 *
 * @date 2019年7月26日 上午10:12:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private LocalDate birth;

}