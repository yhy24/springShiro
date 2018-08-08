package yhy.service.impl;

import org.springframework.stereotype.Service;
import yhy.dao.UserDao;
import yhy.exception.ServiceException;
import yhy.pojo.User;
import yhy.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   // private static final Logger LOGGER = LoggerFactory.getServiceLog(UserServiceImpl.class);

    @Resource
    UserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.findUser();
    }

    /*事物的传播行为*/
    /*
    <!--配置事物：数据源的是用注解  @Transactional-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <tx:annotation-driven transaction-manager="transactionManager"/>
     *(1.已经开启事物)当前的方法调用其他的方法(其他的方法已经有事物是的开启)
     * propagation指定事物的传播行为，既当前事物方法被另外一个事物方法调用的时候，如何使用事物
     * REQUIRED:既使用调用方法的事物
     * REQUIRES_NEW:表示该方法必须启动一个行的事物，被调用事物的方法在运行就应该先挂起
     *2.isolation：指定事物的隔离级别，最常用的值为READ_COMMITTED
     * 默认情况下spring的声明事物对所有的运行时异常进行回滚，也可以通过对应的属性进行设置
     * 3.readOnly:指定的事物的只读属性。ture 表示这个方法只读取数据，不进行更新的操作，这样可以帮助数据库引优化事物
     * 4.timeOut:指定强制回滚之前事物可以占用多长时间。
     */
//    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED,readOnly = false,timeout = 1)
    @Override
    public User findUserByName(String username) throws ServiceException {
        User user = userDao.findOneUser(username);
        if(user == null){
            throw new ServiceException("9999","该用户不存在的");
        }
        return user;
    }
}
