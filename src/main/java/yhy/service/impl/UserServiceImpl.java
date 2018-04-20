package yhy.service.impl;

import org.springframework.stereotype.Service;
import yhy.dao.UserDao;
import yhy.pojo.User;
import yhy.service.UserService;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.findUser();
    }
}
