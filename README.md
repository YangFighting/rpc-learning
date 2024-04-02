# rpc-learning

来源：

https://gitcode.net/binghe001/bhrpc-learning

## 第四章

类扫描器的时候，使用线程上下文加载器获取包名的资源文件，这里破坏了双亲委派模型，

1. Q: 线程上下文加载器如何破坏双亲委派模型

1. 类扫描器和spring类扫描器的区别
2. JDBC中的SPI中打破双亲委派模型有什么联系



Q: rpcService注解的 interfaceClass().getName() 和interfaceClassName()有什么区别

A: 参考来源https://blog.csdn.net/xx897115293/article/details/112599402

interfaceClass 记录的是类，格式是xxx.class

interfaceClassName 记录的类名，就是一个字符串

 更推荐interfaceClass(简单，不易错) ， interfaceClassName 容易写错名称

## 第五章

核心类如下，RpcSingleServer类作为启动rpc服务的启动类，在构造方法里会指定netty的启动端口，同时指定需要扫描包的路径，放射带有@RpcService注解的类实例，并记录起来

![](https://article-images.zsxq.com/FmTBCKBTzQ1wWEZcJGRv_IjeZqbB)

Q: netty执行后，如果客户端没有输入，服务端会不断打印 �� 

## 第六章

核心类如下，其中

RpcProtocol类的 消息头header通过RpcHeaderFactory类构建出请求消息头或者响应消息头，消息体body为泛型对象

响应类RpcResponse 或者 请求类RpcRequest通过消息体body传入到RpcProtocol类中

![](https://article-images.zsxq.com/FjeIs6dRol1WtTT4GrVpvpJPsdlu)

Q：自定义网络协议中魔数的作用

A：用于快速识别TCP数据包，如果魔数不一致，则直接拒绝并关闭连接



## 第七章

在编解码（RpcEncoder/RpcEncoder）的时候，借用的netty， 编码的时候消，按照消息头约定的格式，先发送魔数，再发送协议类型，最后发送消息长度和消息数据

## 第8章：模拟服务消费者与服务提供者之间的数据交互

Q: 为什么要自定义网络传输协议和数据编解码

A: 通用的网络传输协议冗余字段太多，会造成网络通信不够高效,需要精简，传输的数据越少越好；通用的网络传输协议难以结合具体场景实现自定义的传输格式与传输位标识；

网络传输协议自定义后，编解码需要根据网络传输协议来发送接收数据





##  第9章：服务提供者调用真实方法的实现 

本章主要的修改在 RPC服务提供者收到请求数据，具体如下

- 将处理请求构造响应的过程放入线程池中异步处理，
- 在构造响应时，根据请求的类名，版本等信息，在handlerMap 中获取服务提供者的类名，通过反射去类名中的方法。完成服务提供者调用真实方法的实现





## 第10章：测试服务提供者调用真实方法

## 第11章：服务提供者扩展支持CGLib调用真实方法

本章只修改了服务提供者侧的动态代理扩展，不是序列化，不需要没有修改服务消费者侧

CGLib代理和 jdk代理有什么不同？？

## 第12章：实现服务消费者与服务提供者直接通信  

封装消费者 运用**netty**，需要对netty 熟练掌握

## 第13章：服务消费者异步转同步直接获取返回结果

转同步的方式，将请求头id在发送的时候记录下内存中，接收返回数据的时候，根据请求头id来获取响应数据



## 第14章：服务消费者异步转同步的自定义Future与AQS实现 

异步转同步的核心逻辑 

- 服务消费者生成请求数据 


- 服务消费者向服务提供者发送请求，得到RPC的异步任务 


- RPC的异步任务获取服务返回响应 
  - 服务返回响应通过netty去更新RPC的异步任务，异步任务会释放同步锁
  - 使用 AQS 来获取同步锁，同步获取服务返回的响应

这里的异步转同步，是指多个服务消费者去请求数据时，会通过同步锁来同步返回的响应



有没有可能同步获取服务返回的响应时，响应返回null？

服务返回响应netty应该会阻塞，直到响应返回



## 第15章：服务消费者同步、异步、单向调用的实现 

Q: 这章与14章的异步转同步的关系是什么？

- 14章发送Rpc请求是用同步的方式

- 15章的发送Rpc请求增加异步和单向调用两种方式，在发送请求时根据请求协议的参数来选择不同的方式



Q: InheritableThreadLocal 可继承线程局部变量的作用？

​	`ThreadLocal` 是一个线程局部变量，它提供了一种在每个线程中存储和访问数据的方式。每个线程都可以在 `ThreadLocal` 中存储自己的数据，并且这些数据对于该线程是唯一可见的，其他线程无法直接访问。

​	`InheritableThreadLocal` 的作用可以继承继承父线程的上下文信息

​	但是InheritableThreadLocal 只能记录一个变量，即只能记录一个发送Rpc请求任务



Q: 异步发送Rpc请求，何时通过上下文来获取Rpc请求任务？

- 根据调用者（用户）的习惯，来从上下文中获取Rpc请求任务，以及获取Rpc请求任务返回的结果

- 同步调用中，可以不立刻获取请求任务Future返回，是不是也是一种异步？？

  

Q: 同步、异步、单向调用的区别?

- 同步：在发送Rpc请求后，返回Rpc请求任务Future，获取请求任务Future的返回时，会阻塞
- 异步：将Rpc请求任务放到RpcContext上下文中，根据调用者（用户）来获取Rpc请求任务
- 单向：发送请求后 不需要关注返回





## 第16章：服务消费者回调方法的实现

Q: Rpc任务RPCFuture为什么要再加ReentrantLock，ReentrantLock与Sync（AQS）分别承担什么作用？



## 第17章：服务消费者实现动态代理功能屏蔽构建请求协议对象的细节

对象代理类 ObjectProxy 与 Rpc请求RpcRequest 参数差不多，为什么不直接合并？

Q: InvocationHandler接口的作用？

1. 定义代理对象如何创建对象
2. ObjectProxy 和 JdkProxyFactory 分开是为了扩展代理功能
3. 这里应该参考设计模式的 代理模式



Q: 对象代理类 ObjectProxy的主要功能

1. 针对 Object 类中的 equals(), hashCode(), toString() 方法的处理
2. 根据代理的参数 转成 RpcProtocol协议
3. 发送Rpc请求
4. 返回请求任务，异步请求需要从上下文中获取，这里要怎么处理？

## 第18章：服务消费者整合动态代理实现直接调用接口返回结果数据

Q: RpcClient类和JdkProxyFactory 类是否有冲突？

Q: RpcClient类调用create方法需要传入服务类，服务类怎么在服务消费者 和 服务提供者中同时存在？



## 第19章：服务消费者动态代理实现异步调用

动态代理为什么要写getClassType 方法

Q: createAsync 与 create 的区别是什么？

- create 暴露传入服务类，createAsync 则隐藏
- 

## 第20章：服务消费者动态代理扩展优化

这里的扩展优化没有明白是什么意义？



## 第21章：注册中心基础服务功能的实现

zk常用命令

连接zookeeper server

```bash
sh zkCli.sh -server 127.0.0.1:2181
```

查看所有节点

```bash
ls /
```

查看节点数据

```bash
ls /path
```

删除节点

```bash
delete /path
```

获取指定路径下的数据

```bash
get /zookeeper/config
```



destroy 和 unRegister 的区别

**Destroy** 通常指销毁或释放资源，通过销毁操作可以释放其占用的资源，以便系统可以回收这些资源并用于其他用途

**Unregister**：通常指取消注册，例如从注册表或服务注册中心中删除一个条目



## 第22章：服务提供者整合注册中心实现服务注册

本章主要功能在服务提供者启动后，将RPC服务注册到注册中心，服务元数据主要有服务名称，服务版本号，服务地址，服务端口，服务分组

## 第23章：服务消费者整合注册中心实现服务发现

本章主要功能：服务消费者实现服务发现的功能，

- 根据注册地址，生成注册服务(这里留了SPI的扩展)，初始化注册服务
- 在动态代理生成服务请求任务的过程中
  - 发送请求任务时，根据服务名称，服务版本号，服务分组得到服务唯一标志，注册服务根据服务唯一标志发现服务元数据
  - 服务消费者根据元数据的服务地址和服务端口获取本地缓存中的消费者处理器
  - 消费者处理器发送请求数据



## 第24章：服务消费者实现基于随机算法的负载均衡策略

## 第25章：对标Dubbo实现SPI扩展机制的基础功能

通过 目录`META-INF/yang/external`下的 配置，调用 `newInstance()` 方法来实例化对象



## 第26章：基于SPI扩展JDK序列化与反序列化机制

没理解这种扩展的意义？

序列化的实现类是根据RpcClient类的参数来选择的。但是为啥JdkSerialization 要单独成一个模块，不应该跟其他序列化实现类在一起，然后根据在同一文件`io.yang.rpc.serialization.api.Serialization`指定不同的实现类地址
