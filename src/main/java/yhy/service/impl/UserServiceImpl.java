package yhy.service.impl;

import org.slf4j.LoggerFactory;
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

    @Override
    public User findUserByName(String username) throws ServiceException {
        User user = userDao.findOneUser(username);
        if(user == null){
            throw new ServiceException("9999","该用户不存在的");
        }
        return user;
    }
}
