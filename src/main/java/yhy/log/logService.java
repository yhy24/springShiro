package yhy.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
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
    @Pointcut("execution(* yhy.controller..*(..))")
    public void testPoint() {
    }

    /**
     * 调用方法前进行通知的操作Before
     * @param joinpoint
     */
    @Before(value = "execution(* yhy.service..*(..))")
    public void testLog(JoinPoint joinpoint) {
        String name = joinpoint.getSignature().getName();
        System.out.println("----------I am coming-------Aspect----");
        System.out.println("----------logName--------" + name);
    }

    @After("testPoint()")
    public void testPCut(JoinPoint joinPoint) {
        String string = joinPoint.getSignature().toString();
        System.out.println("<-------增强的after-无论异常和正常------->"+string);
    }

    @AfterReturning("testPoint()")
    public void testAfterRreturn() {
        System.out.println("只有正常的正常返回的after");
    }

    @AfterThrowing("testPoint()")
    public void testThrowbal() {
        System.out.println("AfterThrowing--------------");
    }


 /*   @Around("testPoint()")
    public void testDoAround(ProceedingJoinPoint joinPoint){
        String str = "test";//环绕执行
        System.out.println("执行方法前"+str);
        long start = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        String string = joinPoint.getSignature().toLongString();
        System.out.println("方法执行后的处理"+""+string+(System.currentTimeMillis()-start));

    }*/

/*    *//**
     * @annotation
     *//*
 @Pointcut("@annotation(yhy.aop.SwaggerAop)")
 public void testAop() {

 }

    *//**
     *
     * @param joinPoint
     * @return
     *//*
 @Around("testAop()")
 public Object testAnnotation(ProceedingJoinPoint joinPoint) {

     return new Object();
 }*/



}
