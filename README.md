
![AI图 ](doc/welcome.png)

# 索引
一些简单的案例与demo的代码库，希望帮助到你


## [data-structure](data-structure)
数据结构
- 数组
- 链表
- 栈
- 队列
- 哈希表
- 二叉树
- AVL树
- 红黑树
- B树
- B+树
- 堆
- 图

##  [algorithm](algorithm)
算法
- 查找
- 排序
- 分治
- 回溯
- 动态规划

## [javase](javase)
### [jvm](javase/jvm)
jvm 模块大部分原来来自于《深入理解Java虚拟机 第3版》 中的案例, 我自己做了部分实现。
- 环境:Open-JDK1.8.341
- 开发工具:IDEA 2022
- 虚拟机:OpenJDK 1.8 自带Hotspot
- 部分监控软件:Arthas、VisualVM 2.1.5，没有使用自带的，自带的版本比较旧。
- 前后端优化
- 垃圾回收期
- 运行时栈帧结构
- 运行时数据区域
- class文件
- 类加载器等
- arthas

### [java](javase/java)
- io
- java常用类
- lambda表达式
- socket
- utils:一些工具类
- 内部类
- 反射
- 多线程（Synchronized、JUC、线程池等）
- 异常
- 数据类型
- 注解
- 集合
- 自动拆箱、装箱
- 消灭战舰游戏V1、V2


### [jdk8](javase/jdk8)
对jdk 源码进行阅读，并做部分实现验证等，不支持编译。
- 环境:Open-JDK1.8.341
- 开发工具:IDEA 2022

## [design-patterns](design-patterns)
设计模式的部分代码： 环境:Open-JDK1.8.341

- 策略模式 
- 饿汉式单例模式
- 懒汉式单例模式
- 多线程懒汉式单例模式
- 枚举单例模式
- 静态内部类单例模式
- DCL双检锁式单例模式
- 两阶段终止模式
- 保护性暂停模式
- 顺序控制模式
- Balking犹豫模式
- 生产者-消费者模式

## [hadoop](hadoop)
### [hadoop-rpc](hadoop-rpc)
- 使用hadoop-RPC中的server与Client通信

### [hdfs-client](hdfs-client)
- hdfs客户端
- sequenceFile生成器

### [map-reduce](map-reduce)
- wordcount
- 自定义分区、排序、合并、输出规范等
- 使用MR的2表join实现
- 使用MR多表join实现
- ToolRunner
- 数据生成器看

### [avro](hadoop/avro)

### [parquet](parquet) 
- Parquet文件生成

## [j2ee](j2ee)
- 使用Servlet 实现的登录
- Filter
- Listener

## [mybatis](mybatis)
- 查询测试
- 动态SQL
- L1缓存
- L2缓存
- 分页

## [mysql-read-write-splitting](mysql-read-write-splitting)
- mysql基于Apache-Sharding的读写分离

> 里面[shardingsphere-test-util-5.5.0.jar](mysql-read-write-splitting/mysql-read-write-splitting-base/lib/shardingsphere-test-util-5.5.0.jar) 需要自己编译

## [mysql-sharding](mysql-sharding) 
- mysql基于Apache-Sharding数据分片
- 分片算法
- 分片策略

## [spring](spring)
### [spring-ioc](spring-ioc)
- IOC容器
- 配置类
- bean生命周期、实例化
- 依赖注入方式
- 基于XML的spring
- 基于注解的spring
- 自定义BeanFactory
- 自定义Bean后处理器（BeanPostProcessor）
- 自定义Bean工厂后处理器（BeanfactoryPostProcessor）
- 模板方法

### [spring-aop](spring-aop)
- AOP原理-jdk_cglib代理
- AOP表达式
- AOP事务控制
- AOP日志记录

### [spring-mvc](spring-mvc)
- mvc使用案例
- 分层
- 注解的使用
- 监听器Listener
- 拦截器Interceptor

## [springboot](springboot)
###  [springboot-base](springboot-base)
- springboot base,用来快速开始
### [springboot-admin](springboot-admin)
- 测试spring-admin
### [springboot-cache](springboot-cache)
- 使用springCache 接管缓存
### [springboot-custom-starter](springboot-custom-starter)
- 自定义spring-starter
### [springboot-mybatis](springboot-mybatis)
- spring-boot集成Mybatis-Plus
- 代码生成器

## [zookeeper](zookeeper)
- zookeeper分布式锁
- zookeeper故障转移
- 分布式锁框架Curator
- zookeeper监听器


## [spark](spark)
- sprak-wordcount

## [hive](hive)
- hive、自定义UDF函数