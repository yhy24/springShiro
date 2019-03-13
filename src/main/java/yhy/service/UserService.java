package yhy.service;

import com.github.pagehelper.PageInfo;
import yhy.exception.ServiceException;
import yhy.pojo.Department;
import yhy.pojo.PageBean;
import yhy.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers();

    public User findUserByName(String username) throws ServiceException;

    public PageInfo<User> findUserByPage(int page, int size);

    public int saveBatch(List<User> lists);

    public int insertOne(User user);

    public User getUserById(Integer id);

    public User getUserByEId(Integer id);

    public User getUserByIdStep(Integer id);

    public Department getDepartmentByIdPlus(Integer id);

    public Department getDepartmentByIdStep(Integer id);

    public PageInfo<User> getUsers(int start, int pages, Department department);
    public void deleteIds(User user);
    public List<String> getByFor(List<Long> ids,String gender);
    public void insertInto(List<User> users);
    public PageInfo<User> getUserList(PageBean pageBean);
    public User testUser(Integer id);
    public List<User> testDate(User user);
}
