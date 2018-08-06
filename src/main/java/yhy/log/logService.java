package yhy.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yhy
 * @Date: 2018/8/6 14:09
 * @Version 1.0
 * 进行aspect的配置，添加@Aspect的注解
 */
@Aspect //进行开启aspect的事物
@Configuration
public class logService {

    @Before(value = "execution(* yhy.service.impl..*(..))")
    public void testLog(JoinPoint joinpoint) {
        String name = joinpoint.getSignature().getName();
        System.out.println("----------I am coming-----------");
        System.out.println("----------logName--------" + name);
    }

}
