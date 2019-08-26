### cloudpaas-gateway
> 谓语配置不同的路由

```
routes:
 - id: cpaas-admin-path
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - Path=/apiadmin/**  ## 请求地址携带apiadmin的,则转发
 - id: cpaas-admin-host
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - Host=**.csdn.** # 请求域名携带csdn的,则转发
 - id: cpaas-admin-query
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - Query=username, zzz* # 请求参数含有username,且值满足zzz开头的,则转发(对值的匹配可以省略)
 - id: cpaas-admin-header
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - Header=X-Request-Id, \d+ # 如果请求头含有X-Request-Id,且为数字,则转发
 - id: cpaas-admin-cookie
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - Cookie=name, zzzgd # 如果携带cookie,参数名为name,值为zzzgd,则转发
 - id: cpaas-admin-service
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - Path=/apiadmin/** # 如果请求地址满足/apiadmin/**,则转发到 CPAAS-ADMIN 服务
   filters:
     - StripPrefix=1 # 去除请求地址中的apiadmin 
 - id: cpaas-admin-after
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - After=2019-01-01T17:42:47.789-07:00[America/Denver] # 如果请求时间大于该时间,则转发

     
     
```

```
- id: cpaas-admin-service
   uri: lb://CPAAS-ADMIN
   order: 8000
   predicates:
     - Path=/apiadmin/** # 如果请求地址满足/apiadmin/**,则转发到 CPAAS-ADMIN 服务
   filters:
     - StripPrefix=1 # 去除请求地址中的apiadmin 
```

这里需要注意的一点,如果不加上filters.- StripPrefix=1,那么则无法请求到hi这个接口。因为这个apiadmin相当于是服务名，只是为了网关的路由加上去的，对于服务提供者CPAAS-ADMIN来说，不需要这段地址，所以需要去掉

<https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.1.0.RC3/single/spring-cloud-gateway.html#gateway-request-predicates-factories>



