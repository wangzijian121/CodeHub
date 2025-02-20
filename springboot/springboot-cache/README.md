[![Quality gate](http://zjyun.cc:6008/api/project_badges/quality_gate?project=springboot-quick-start&token=sqb_d4e045e166323b1175cf5c0a6dd6083af8fb32dc)](http://zjyun.cc:6008/dashboard?id=springboot-quick-start)

[![警报](http://zjyun.cc:6008/api/project_badges/measure?project=springboot-quick-start&metric=alert_status&token=sqb_d4e045e166323b1175cf5c0a6dd6083af8fb32dc)](http://zjyun.cc:6008/dashboard?id=springboot-quick-start)
[![可维护性评级](http://zjyun.cc:6008/api/project_badges/measure?project=springboot-quick-start&metric=sqale_rating&token=sqb_d4e045e166323b1175cf5c0a6dd6083af8fb32dc)](http://zjyun.cc:6008/dashboard?id=springboot-quick-start)
[![可靠性等级](http://zjyun.cc:6008/api/project_badges/measure?project=springboot-quick-start&metric=reliability_rating&token=sqb_d4e045e166323b1175cf5c0a6dd6083af8fb32dc)](http://zjyun.cc:6008/dashboard?id=springboot-quick-start)
[![安全等级](http://zjyun.cc:6008/api/project_badges/measure?project=springboot-quick-start&metric=security_rating&token=sqb_d4e045e166323b1175cf5c0a6dd6083af8fb32dc)](http://zjyun.cc:6008/dashboard?id=springboot-quick-start)
[![代码行数](http://zjyun.cc:6008/api/project_badges/measure?project=springboot-quick-start&metric=ncloc&token=sqb_d4e045e166323b1175cf5c0a6dd6083af8fb32dc)](http://zjyun.cc:6008/dashboard?id=springboot-quick-start)


# springboot快速脚手架🚀
快速的springboot脚手架，帮您快速投入开发，使用前可以自行更新`模块`、`版本`、`打包方式`，本项目具有以下功能：
- springboot-web✅
- lombok✅
- mybatis-plus✅
- mybatis-plus代码生成器✅
- mysql8✅
- logback✅
- 日志拆分✅
- profiles.active分环境组✅
- springbootAdmin-Client✅
- versions-maven-plugin版本管理插件✅
- assembly 多模块打包✅

# 版本
- `java`: Open Jdk 1.8
- `springboot` ：2.6.12 
- `内置Spring版本`：5.3.23
- `mybatis-plus`:3.5.7
- `mysql驱动`：8.0.23
- `lombok`:1.18.34


# 使用
- `mybatis-plus代码生成器`：运行 com.zjyun.springboot.CodeGenerator
- `logback配置`：base/src/main/resources/application-prod.yml 
- `日志拆分`：默认按大小+日期分割
-  `分组、分环境`：默认有三种环境组`dev`、`prod`、`test`,指定环境：`-Dspring.profiles.active=dev|prod|test`
- `springbootClient`：可以自行搭配springbootAdmin做状态监控，配置`base/src/main/resources/application-prod.yml`默认关闭`spring.boot.admin.client.enabled=false`
- `versions-maven-plugin`：命令比较多，可以参考官网：https://www.mojohaus.org/versions/versions-maven-plugin/index.html
常用命令：`mvn versions:set|commit|revert` 可导航到maven-插件-versions快速修改版本，模块多的谨慎操作，多备份。
- `assembly`：默认打为tar包 和目录的形式，其中包括bin、conf、libs、db、docs。可自行配置`assembly/assembly/assembly.xml`指定打包方式。
# 运行
- `默认运行方式`:`sh bin/start.sh`
- `默认停止方式`:`sh bin/stop.sh`

# 代码质量校验
可使用`Jacoco`+`SonarQube`做代码质量管理。
相关指令：`mvn clean verify sonar:sonar   -Dsonar.projectKey=springboot-quick-start   -Dsonar.projectName='springboot-quick-start'   -Dsonar.host.url=<SonarQubeServer：port>   -Dsonar.token=sqp_xxxxxx`
