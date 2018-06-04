package yhy.service;

import yhy.exception.ServiceException;
import yhy.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User findUserByName(String username) throws ServiceException;
}
