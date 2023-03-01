# rpc-learning

来源：

https://gitcode.net/binghe001/bhrpc-learning

## 第四章

类扫描器的时候，使用线程上下文加载器获取包名的资源文件，这里破坏了双亲委派模型，

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

