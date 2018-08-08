package yhy.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yhy
 * @Date: 2018/8/6 14:09
 * @Version 1.0
 * 进行aspect的配置，添加@Aspect的注解
 * <!--进行开启事物-->
   <aop:aspectj-autoproxy/>
 */
@Aspect //进行开启aspect的事物
@Configuration
public class logService {

    /**
     * 定义一个方法，用来声明切入点的表达式，一般的，改方法中不再需要写入其他的代码
     * 后面的其他的方法直接饮用方法名来引用当前的切点表达式即可（如下方法演示:testPCut()）
     */
    @Pointcut("execution(* yhy.service..*(..))")
    public void testPoint() {
    }

    /**
     * 调用方法前进行通知的操作Before
     * @param joinpoint
     */
    @Before(value = "execution(* yhy.service..*(..))")
    public void testLog(JoinPoint joinpoint) {
        String name = joinpoint.getSignature().getName();
        System.out.println("----------I am coming-----------");
        System.out.println("----------logName--------" + name);
    }

    @After("testPoint()")
    public void testPCut() {
        System.out.println("<-------123-------->");
    }



}
