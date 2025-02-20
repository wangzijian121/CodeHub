## Arthas为你解决什么问题？
- 这个类从哪个 jar 包加载的？为什么会报各种类相关的 Exception？
- 我改的代码为什么没有执行到？难道是我没 commit？分支搞错了？
- 遇到问题无法在线上 debug，难道只能通过加日志再重新发布吗？
- 线上遇到某个用户的数据处理有问题，但线上同样无法 debug，线下无法重现！
- 是否有一个全局视角来查看系统的运行状况？
- 有什么办法可以监控到 JVM 的实时运行状态？
- 怎么快速定位应用的热点，生成火焰图？
- 怎样直接从 JVM 内查找某个类的实例？

## 启动方式
- arthas-boot(官方推荐)
- as.sh 一键安装启动(需要jdk环境变量)
- 基于java.lang.instrument[ˈɪnstrəmənt] 包的 java-agent方式
- websocket 连接到 Arthas
- 

## 快速Boot启动
```
./java -jar  arthas-boot.jar --select jvm.jar
```

```agsl
开启日志： options save-result true
path： {user.home}/logs/arthas-cache/result.log
```
- help         显示 Arthas 帮助
- auth         Authenticates the current session
- keymap       Display all the available keymap for the specified connection.
- sc           Search all the classes loaded by JVM
- sm           Search the method of classes loaded by JVM
- classloader Show classloader info
- jad          反编译类
- getstatic   Show the static field of a class
- monitor     Monitor method execution statistics, e.g. total/success/failure count, average rt, fail rate, etc.
- stack       Display the stack trace for the specified class and method
- thread      显示线程信息，线程堆栈
- trace       Trace the execution time of specified method invocation.
- watch       显示指定方法调用的输入输出参数、返回对象和抛出异常
- tt          Time Tunnel
- jvm         显示目标JVM信息
- memory      Display jvm memory info.
- perfcounter  Display the perf counterUsedAtomic information.
- ognl         xecute ognl expression.
- mc           Memory compiler, compiles java files into bytecode and class files in memory.
- redefine    Redefine classes. @see Instrumentation#redefineClasses(ClassDefinition...)
- retransform Retransform classes. @see Instrumentation#retransformClasses(Class...)
- dashboard   Overview of target jvm's thread, memory, gc, vm, tomcat info.
- dump         Dump class byte array from JVM
-  heapdump    Heap dump
-  options     View and change various Arthas options
-  cls          Clear the screen
- reset       Reset all the enhanced classes
- version     Display Arthas version
- session     Display current session information
- sysprop     Display and change the system properties.
- sysenv      Display the system env.
- vmoption    Display, and update the vm diagnostic options.
- logger      Print logger info, and update the logger level
- history     显示命令历史
- cat          Concatenate and print files
- base64       Encode and decode using Base64 representation
- echo         write arguments to the standard output
- pwd          Return working directory name
- mbean        Display the mbean information
- grep         grep command for pipes.
- tee          tee command for pipes.
- profiler     Async Profiler. https://github.com/jvm-profiling-tools/async-profiler
- vmtool       jvm tool
- stop         Stop/Shutdown Arthas server and exit the console.
- jfr          Java Flight Recorder Command

