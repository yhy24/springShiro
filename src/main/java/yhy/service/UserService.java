package yhy.service;

import com.github.pagehelper.PageInfo;
import yhy.exception.ServiceException;
import yhy.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
    public User findUserByName(String username) throws ServiceException;

    public PageInfo<User> findUserByPage(int page,int size);

    public int saveBatch(List<User> lists);
}
