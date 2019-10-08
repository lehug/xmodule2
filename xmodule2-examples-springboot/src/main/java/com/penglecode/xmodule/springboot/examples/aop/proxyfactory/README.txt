https://www.php.cn/manual/view/21809.html

Spring实现Aop功能有两种方式，

1. ProxyFactoryBean方式： 这种方式是通过配置实现

2. ProxyFactory方式：这种方式是通过编程实现

Spring中的代理的实现其实是JDK Proxy和CGLIB Proxy两种实现方式

Advice、Advisor、Pointcut

（1）切入点  Pointcut

    在介绍Pointcut之前，有必要先介绍  Join  Point（连接点）概念。
	连接点：程序运行中的某个阶段点，比如方法的调用、异常的抛出等。比如方法doSome();
	Pointcut是JoinPoint的集合，它是程序中需要注入Advice 的位置的集合，指明Advice要在什么样的条件下才能被触发。
	org.springframework.aop.Pointcut接口用来指定到特定的类和方法。

（2）通知Advice

 	它是某个连接点所采用的处理逻辑，也就是向连接点注入的代码。例如：输出的日志信息   就是一个Advice

（3）Advisor

	Advisor是Pointcut和Advice的配置器，它包括Pointcut和Advice，是将Advice注入程序中Pointcut位置的代码