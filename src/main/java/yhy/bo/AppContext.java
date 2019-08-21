package yhy.bo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: yhy
 * @Date: 2019/5/21 10:51
 * @Version 1.0
 */
public class AppContext implements ApplicationContextAware {

    private static ApplicationContext _applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        _applicationContext = applicationContext;
    }



    public static <T> T getBean(String name) {
        return (T) _applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> tClass) {
        return _applicationContext.getBean(tClass);
    }
}
