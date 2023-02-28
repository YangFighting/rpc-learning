# rpc-learning

来源：

https://gitcode.net/binghe001/bhrpc-learning

类扫描器的时候，使用线程上下文加载器获取包名的资源文件，这里破坏了双亲委派模型，

1. 类扫描器和spring类扫描器的区别
2. JDBC中的SPI中打破双亲委派模型有什么联系



rpcService注解的 interfaceClass().getName() 和interfaceClassName()有什么区别

参考来源https://blog.csdn.net/xx897115293/article/details/112599402

interfaceClass 记录的是类，格式是xxx.class

interfaceClassName 记录的类名，就是一个字符串

 更推荐interfaceClass(简单，不易错) ， interfaceClassName 容易写错名称