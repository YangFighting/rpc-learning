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

