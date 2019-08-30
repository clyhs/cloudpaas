# cloudpaas

以springcloud搭建paas平台。

### 软件结构

* cloudpaas-regcenter 注册中心
* cloudpaas-common  公共模块
* cloudpaas-bean 公共实体模块
* cloudpaas-monitor 监控治理模块
* cloudpaas-trace 链路监控模块
* cloudpaas-admin 基础鉴权模块
* cloudpaas-service-pas 服务模块
* cloudpaas-admin-ui 前端-基础管理平台

*端口：8888，打包mvn package -DskipTests docker:build*

* cloudpaas-all 后端服务打成单机包

*端口：8200，打包mvn package docker:build*

### 启动

> 单机模式

启动顺序 cloudpaas-all --> cloudpaas-admin-ui

通过docker打包这两包

下面以docker 启动

```
#cloudpaas-all启动
docker run -d --name cpaas-all -p 8200:8080 cloudpaas-all/cloudpaas-all
#cloudpaas-admin-ui
docker run -d --name cpaas-admin-ui -e GATETYPE=header -e GATEIP=http://192.168.0.16 -e GATEPORT=8200 -p 8888:8080 cloudpaas-admin-ui/cloudpaas-admin-ui
```

*GATETYPE=header或path，GATEIP=127.0.0.1，GATEPORT=8200。参数可以调整接入的后端服务地址*



> 分布式集群模式

启动顺序 cloudpaas-regcenter --> cloudpaas-admin --> cloudpaas-service-pas --> cloudpaas-gateway --> cloudpaas-admin-ui

```
#cloudpaas-regcenter
docker run -d --name cpaas-regcenter -p 8761:8761 cloudpaas-regcenter/cloudpaas-regcenter
#cloudpaas-admin
docker run -d --name cpaas-admin -e EUREKA_SERVICE_URL=http://192.168.0.16:8761 -e MONITOR_URL=http://192.168.0.16:8901 -p 8101:8101 cloudpaas-admin/cloudpaas-admin
#cloudpaas-service-pas
docker run -d --name cpaas-service-pas -e EUREKA_SERVICE_URL=http://192.168.0.16:8761 -e MONITOR_URL=http://192.168.0.16:8901 -p 8102:8102 cloudpaas-service-pas/cloudpaas-service-pas
#cloudpaas-gateway(默认以path做为路由)
docker run -d --name cpaas-gateway -e EUREKA_SERVICE_URL=http://192.168.0.16:8761 -e MONITOR_URL=http://192.168.0.16:8901 -p 8100:8100 cloudpaas-gateway/cloudpaas-gateway
#cloudpaas-monitor(可以不启动)
docker run -d --name cpaas-monitor -e EUREKA_SERVICE_URL=http://192.168.0.16:8761 -p 8901:8901 cloudpaas-monitor/cloudpaas-monitor
#cloudpaas-admin-ui
docker run -d --name cpaas-admin-ui -e GATETYPE=path -e GATEIP=http://192.168.0.16 -e GATEPORT=8100 -p 8888:8080 cloudpaas-admin-ui/cloudpaas-admin-ui
```



### 访问界面

http://localhost:8888/adminui/login.html